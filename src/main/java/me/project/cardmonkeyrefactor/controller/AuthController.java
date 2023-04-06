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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<String> signUp(@RequestBody @Valid SignupReqDTO req) {
        memberService.join(req);

        return ResponseEntity.ok("회원가입 완료");
    }

    /**
     * 아이디 중복체크 (회원가입 시)
     */
    @PostMapping("/userIdValidation")
    @ApiOperation(value = "아이디 중복체크", notes = "회원가입시 아이디 중복체크를 수행합니다.")
    public ResponseEntity<String> userIdValidation(@RequestBody @Valid ValidationDTO req) {
        memberService.userIdValidation(req);

        return ResponseEntity.ok("사용가능한 아이디 입니다.");
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인을 수행합니다.")
    public ResponseEntity<LoginResDTO> signIn(@RequestBody @Valid LoginReqDTO req) {
        return ResponseEntity.ok(memberService.login(req));
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "로그아웃을 수행합니다.")
    public ResponseEntity<String> signOut(@RequestHeader(name="Authorization") String header) {
        tokenRepository.save(Token.builder().token(header).build());

        return ResponseEntity.ok("로그아웃 완료");
    }
}
