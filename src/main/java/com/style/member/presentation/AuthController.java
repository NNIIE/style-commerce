package com.style.member.presentation;

import com.style.member.application.AuthService;
import com.style.member.domain.Address;
import com.style.member.domain.CurrentMember;
import com.style.member.domain.Member;
import com.style.member.presentation.request.SignInRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.style.member.utils.MemberConst.SESSION_MEMBER_KEY;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그인")
    public void signIn(@RequestBody @Valid final SignInRequest request, final HttpSession session) {
        final Member member = authService.signIn(request);
        session.setAttribute(SESSION_MEMBER_KEY, member);
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그아웃")
    public void signOut(final HttpSession session, @CurrentMember Member member) {
        session.invalidate();
    }

}
