package me.project.cardmonkeyrefactor.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.dto.AuthDTO;
import me.project.cardmonkeyrefactor.dto.ChangeBenefitReqDTO;
import me.project.cardmonkeyrefactor.dto.PasswordReqDTO;
import me.project.cardmonkeyrefactor.exception.member.DuplicateInputPasswordException;
import me.project.cardmonkeyrefactor.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = {"회원 서비스"}, description = "회원 서비스를 담당합니다.")
public class MemberController {

    private final MemberService memberService;

    /**
     * 비밀번호 변경
     */
    @PatchMapping("/info/changePassword")
    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 확인 후 변경합니다.")
    public ResponseEntity<String> changePassword(Authentication authentication, @RequestBody @Valid PasswordReqDTO req) {
        if (req.getCurrentPassword().equals(req.getNewPassword())) {
            throw new DuplicateInputPasswordException();
        }
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        return ResponseEntity.ok(memberService.updatePassword(userId, req));
    }

    /**
     * 혜택 변경
     */
    @PatchMapping("/info/changeBenefit")
    @ApiOperation(value = "혜택 변경", notes = "회원가입시 선택했던 3가지의 혜택을 수정합니다.")
    public ResponseEntity<String> changeBenefit(Authentication authentication, @RequestBody ChangeBenefitReqDTO req) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        return ResponseEntity.ok(memberService.changeBenefit(userId, req));
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/info/deleteAccount")
    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴합니다.")
    public ResponseEntity<String> deleteAccount(Authentication authentication) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        return ResponseEntity.ok(memberService.deleteAccount(userId));
    }
}
