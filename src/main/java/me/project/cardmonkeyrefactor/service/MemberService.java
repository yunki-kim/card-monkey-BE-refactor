package me.project.cardmonkeyrefactor.service;

import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.dto.*;
import me.project.cardmonkeyrefactor.entity.Benefit;
import me.project.cardmonkeyrefactor.entity.Member;
import me.project.cardmonkeyrefactor.exception.member.AlreadyUseIdException;
import me.project.cardmonkeyrefactor.exception.member.MismatchCurrentPasswordException;
import me.project.cardmonkeyrefactor.exception.member.NoSuchMemberException;
import me.project.cardmonkeyrefactor.jwt.JwtProvider;
import me.project.cardmonkeyrefactor.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    public void join(SignupReqDTO req) {
        checkInUseUserId(req.getUserId());

        req.setPassword(encodingPassword(req.getPassword()));
        if (req.getRole() == null || req.getRole().equals("")) {
            req.setRole("ROLE_USER");
        }
        Member member = req.toEntity(req.getBenefit());

        memberRepository.save(member);
    }

    /**
     * 아이디 중복체크 (회원가입 시)
     */
    @Transactional(readOnly = true)
    public void userIdValidation(ValidationDTO req) {
        checkInUseUserId(req.getUserId());
    }

    /**
     * 로그인
     */
    @Transactional(readOnly = true)
    public LoginResDTO login(LoginReqDTO req) {
        Member findMember = memberRepository.findByUserId(req.getUserId()).orElseThrow(
                NoSuchMemberException::new);

        if (!checkPassword(req.getPassword(), findMember.getPassword())) {
            return new LoginResDTO("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        String createdToken = jwtProvider.makeToken(findMember);
        return LoginResDTO.builder()
                .userId(findMember.getUserId())
                .name(findMember.getName())
                .role(findMember.getRole())
                .token(createdToken)
                .loginStatus("로그인 완료")
                .build();
    }

    /**
     * 비밀번호 변경
     */
    public void updatePassword(String userId, PasswordReqDTO req) {
        Member findMember = memberRepository.findByUserId(userId).orElseThrow(
                NoSuchMemberException::new);

        if (!checkPassword(req.getCurrentPassword(), findMember.getPassword())) {
            throw new MismatchCurrentPasswordException();
        }
        findMember.updatePassword(encodingPassword(req.getNewPassword()));
    }

    /**
     * 혜택 변경
     */
    public void changeBenefit(String userId, ChangeBenefitReqDTO req) {
        Member findMember = memberRepository.findByUserId(userId).orElseThrow(
                NoSuchMemberException::new);

        findMember.updateBenefit(new Benefit(req.getBenefit()));
    }

    /**
     * 회원 탈퇴
     */
    public void deleteAccount(String userId) {
        memberRepository.deleteByUserId(userId);
    }

    /**
     * 아이디 등록여부 확인
     */
    private void checkInUseUserId(String userId) {
        if (memberRepository.existsByUserId(userId)) {
           throw new AlreadyUseIdException();
        }
    }

    /**
     * 비밀번호 인코딩
     */
    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 비밀번호 일치여부 확인
     */
    private boolean checkPassword(String inputPassword, String originPassword) {
        return passwordEncoder.matches(inputPassword, originPassword);
    }
}
