package me.project.cardmonkeyrefactor.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.dto.LoginReqDTO;
import me.project.cardmonkeyrefactor.dto.LoginResDTO;
import me.project.cardmonkeyrefactor.dto.SignupReqDTO;
import me.project.cardmonkeyrefactor.dto.ValidationDTO;
import me.project.cardmonkeyrefactor.entity.Token;
import me.project.cardmonkeyrefactor.repository.TokenRepository;
import me.project.cardmonkeyrefactor.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {"인증 서비스"}, description = "인증 서비스를 담당합니다.")
public class AuthController {

    private final MemberService memberService;
    private final TokenRepository tokenRepository;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "회원가입을 수행합니다.")
    public String signUp(@RequestBody SignupReqDTO req) {
        if (req.getUserId() == null || req.getPassword() == null || req.getName() == null) {
            return "모든 값을 입력해주세요";
        }
        return memberService.join(req);
    }

    /**
     * 아이디 중복체크 (회원가입 시)
     */
    @PostMapping("/userIdValidation")
    @ApiOperation(value = "아이디 중복체크", notes = "회원가입시 아이디 중복체크를 수행합니다.")
    public String userIdValidation(@RequestBody ValidationDTO req) {
        if (req.getUserId() == null) {
            return "아이디를 입력해주세요";
        }
        return memberService.userIdValidation(req);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인을 수행합니다.")
    public LoginResDTO signIn(@RequestBody LoginReqDTO req) {
        if (req.getUserId() == null || req.getPassword() == null) {
            return new LoginResDTO("아이디와 비밀번호 모두 입력해주세요");
        }
        return memberService.login(req);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "로그아웃을 수행합니다.")
    public String signOut(@RequestHeader(name="Authorization") String header) {
        tokenRepository.save(Token.builder().token(header).build());
        return "로그아웃 완료";
    }
}
