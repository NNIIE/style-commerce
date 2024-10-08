package com.style.member.presentation;

import com.style.common.domain.SessionMember;
import com.style.member.application.AuthService;
import com.style.common.annotation.CurrentMember;
import com.style.member.presentation.request.SignInRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.style.common.utils.consts.KeyConstants.SESSION_MEMBER_KEY;

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
        final SessionMember member = authService.signIn(request);
        session.setAttribute(SESSION_MEMBER_KEY, member);
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그아웃")
    public void signOut(final HttpSession session, @CurrentMember SessionMember member) {
        session.invalidate();
    }

}
