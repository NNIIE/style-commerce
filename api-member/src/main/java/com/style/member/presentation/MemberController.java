package com.style.member.presentation;

import com.style.common.domain.CurrentMember;
import com.style.common.domain.entity.Member;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.application.MemberService;
import com.style.member.infra.MemberRepository;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;
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
    private final MemberRepository memberRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입")
    public void signUp(@RequestBody @Valid final SignUpRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberExceptionCode.EXISTS_USER_EMAIL);
        }

        if (memberRepository.existsByNickname(request.getNickname())) {
            throw new MemberException(MemberExceptionCode.EXISTS_USER_NICKNAME);
        }

        memberService.signUp(request);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원탈퇴")
    public void signOff(
            @RequestBody @Valid final SignOffRequest request,
            @CurrentMember Member member,
            final HttpSession session
    ) {
        memberService.signOff(member, request);
        session.invalidate();
    }

}
