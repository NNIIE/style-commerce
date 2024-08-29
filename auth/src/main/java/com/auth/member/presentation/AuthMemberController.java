package com.auth.member.presentation;

import com.auth.member.application.AuthMemberService;
import com.auth.member.presentation.request.MemberSignInRequest;
import com.core.entity.member.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.auth.utils.AuthConst.SESSION_MEMBER_KEY;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth Member")
@RequestMapping("/auth/member")
public class AuthMemberController {

    private final AuthMemberService authMemberService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그인")
    public void signIn(@RequestBody @Valid final MemberSignInRequest request, final HttpSession session) {
        final Member member = authMemberService.signIn(request);
        session.setAttribute(SESSION_MEMBER_KEY, member);
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그아웃")
    public void signOut(final HttpSession session) {
        session.invalidate();
    }

}
