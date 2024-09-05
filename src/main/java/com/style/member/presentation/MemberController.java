package com.style.member.presentation;

import com.style.common.domain.CurrentMember;
import com.style.common.domain.entity.Member;
import com.style.member.application.MemberService;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;
import com.style.member.presentation.request.UpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
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
    public void profileUpdate(@RequestBody @Valid final UpdateRequest request, @CurrentMember final Member member) {
        memberService.profileUpdate(member.getId(), request);
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

}
