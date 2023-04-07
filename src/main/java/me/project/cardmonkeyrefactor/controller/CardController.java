package me.project.cardmonkeyrefactor.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.project.cardmonkeyrefactor.dto.*;
import me.project.cardmonkeyrefactor.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"카드 서비스"}, description = "카드 서비스를 담당합니다.")
public class CardController {

    private final CardService cardService;

    /**
     * 몽키차트 TOP 5 카드 (찜)
     */
    @GetMapping("/card/rank")
    @ApiOperation(value = "몽키차트 TOP 5 카드", notes = "찜 횟수가 많은 TOP 5 카드를 조회합니다.")
    public ResponseEntity<List<CardByFavorResDTO>> Top5FavorRankCardList() {
        return ResponseEntity.ok(cardService.selectCardByFavorRank());
    }

    /**
     * 관심혜택 맞춤 카드
     */
    @GetMapping("/card/recommend")
    @ApiOperation(value = "관심 혜택 맞춤 카드", notes = "회원가입 시 선택한 3가지의 혜택으로 카드를 추천합니다.")
    public ResponseEntity<List<CardByBenefitResDTO>> ChooseBenefitCardList(Authentication authentication) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        return ResponseEntity.ok(cardService.selectCardByChooseBenefit(userId));
    }

    /**
     * 카드 검색 (카드명)
     */
    @GetMapping("/card/name")
    @ApiOperation(value = "카드명 검색", notes = "카드명으로 검색합니다.")
    public ResponseEntity<Page<CardResDTO>> searchByNameCardList(@RequestParam(name = "search") String name,
                                                 @PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(cardService.selectCardByName(name, pageable));
    }

    /**
     * 카드 검색 (카드사)
     */
    @GetMapping("/card/company")
    @ApiOperation(value = "카드사 검색", notes = "카드사로 검색합니다.")
    public ResponseEntity<Page<CardResDTO>> searchByCompanyCardList(@RequestParam(name = "search") String company,
                                                    @PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(cardService.selectCardByCompany(company, pageable));
    }

    /**
     * 카드 검색 (혜택)
     */
    @GetMapping("/card/benefit")
    @ApiOperation(value = "카드 혜택 검색", notes = "카드 혜택으로 검색합니다.")
    public ResponseEntity<CardCountResDTO> searchByBenefitCardList(@RequestParam(name = "search") String benefit,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "0") int offset,
                                                   @RequestParam(defaultValue = "100") int limit) {
        return ResponseEntity.ok(cardService.selectCardByBenefit(benefit, page, offset, limit));
    }

    /**
     * 전체 카드 조회
     */
    @GetMapping("/card")
    @ApiOperation(value = "전체 카드 조회", notes = "전체 카드를 조회합니다.")
    public ResponseEntity<Page<CardResDTO>> allCardList(@PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(cardService.selectAllCard(pageable));
    }

    /**
     * 카드 상세정보 조회
     */
    @GetMapping("/card/{cardId}")
    @ApiOperation(value = "카드 상세정보 조회", notes = "카드 상세정보를 조회합니다.")
    public ResponseEntity<CardDetailResDTO> cardDetails(@PathVariable Long cardId) {
        return ResponseEntity.ok(cardService.selectCardDetail(cardId));
    }

    /**
     * 카드 신청 내역
     */
    @GetMapping("/info/apply")
    @ApiOperation(value = "신청한 카드 내역", notes = "신청한 카드 내역을 조회합니다.")
    public ResponseEntity<List<CardResDTO>> PaidCardList(Authentication authentication) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        return ResponseEntity.ok(cardService.selectPaidList(userId));
    }

    /**
     * 나의 관심(찜) 카드 내역
     */
    @GetMapping("/info/favor")
    @ApiOperation(value = "찜한 카드 내역", notes = "찜한 카드 내역을 조회합니다.")
    public ResponseEntity<List<CardResDTO>> FavorCardList(Authentication authentication) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        return ResponseEntity.ok(cardService.selectFavorList(userId));
    }

    /**
     * 카드 신청
     */
    @PostMapping("/card/apply/{cardId}")
    @ApiOperation(value = "카드 신청", notes = "카드를 신청합니다.")
    public ResponseEntity<String> paidCardAdd(@PathVariable Long cardId, Authentication authentication) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        cardService.savePaid(userId, cardId);

        return ResponseEntity.ok("카드신청 완료");
    }

    /**
     * 카드 신청 취소
     */
    @DeleteMapping("/card/apply/{cardId}")
    @ApiOperation(value = "신청한 카드 취소", notes = "신청한 카드를 취소합니다.")
    public ResponseEntity<String> paidCardRemove(@PathVariable Long cardId, Authentication authentication) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        cardService.cancelPaid(userId, cardId);

        return ResponseEntity.ok("카드신청 취소 완료");
    }

    /**
     * 카드 찜하기
     */
    @PostMapping("/card/favor/{cardId}")
    @ApiOperation(value = "카드 찜하기", notes = "카드를 찜 할 수 있습니다.")
    public ResponseEntity<String> favorCardAdd(Authentication authentication, @PathVariable Long cardId) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        cardService.saveFavor(userId, cardId);

        return ResponseEntity.ok("찜하기 완료");
    }

    /**
     * 카드 찜하기 취소
     */
    @DeleteMapping("/card/favor/{cardId}")
    @ApiOperation(value = "카드 찜하기 취소", notes = "찜한 카드를 취소합니다.")
    public ResponseEntity<String> favorCardRemove(Authentication authentication, @PathVariable Long cardId) {
        AuthDTO authDTO = (AuthDTO) authentication.getPrincipal();
        String userId = authDTO.getUserId();

        cardService.cancelFavor(userId, cardId);

        return ResponseEntity.ok("찜하기 취소 완료");
    }
}
