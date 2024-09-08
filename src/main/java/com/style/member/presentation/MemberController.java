package com.style.member.presentation;

import com.style.common.domain.SessionMember;
import com.style.member.domain.CurrentMember;
import com.style.member.domain.entity.Member;
import com.style.member.application.MemberService;
import com.style.member.presentation.request.CreateAddressRequest;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;
import com.style.member.presentation.request.UpdateMemberRequest;
import com.style.member.presentation.response.MemberProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Member")
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원 가입")
    public void signUp(@RequestBody @Valid final SignUpRequest request) {
        memberService.signUp(request);
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원 정보 변경")
    public void profileUpdate(@RequestBody @Valid final UpdateMemberRequest request, @CurrentMember final SessionMember member) {
        memberService.profileUpdate(member.id(), request);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원 정보 조회")
    public MemberProfile getProfile(@Parameter(hidden = true) @CurrentMember final SessionMember member) {
        return memberService.getProfile(member.id());
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원 탈퇴")
    public void signOff(
            @RequestBody @Valid final SignOffRequest request,
            @CurrentMember Member member,
            final HttpSession session
    ) {
        memberService.signOff(member, request);
        session.invalidate();
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "주소 등록")
    public void createAddress(@RequestBody @Valid final CreateAddressRequest request, @CurrentMember final SessionMember member) {
        memberService.createAddress(member.id(), request);
    }

    @DeleteMapping("/address/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "주소 삭제")
    public void deleteAddress(@PathVariable final Long id, @CurrentMember final SessionMember member) {
        memberService.deleteAddress(member.id(), id);
    }

}
