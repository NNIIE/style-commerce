package com.auth.common.interceptor;

import com.auth.common.exception.AuthException;
import com.auth.common.exception.AuthExceptionCode;
import com.core.entity.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.auth.utils.AuthConst.SESSION_MEMBER_KEY;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        HttpSession session = request.getSession(false); // 세션이 없으면 새로 생성하지 않음
        if (session == null) {
            throw new AuthException(AuthExceptionCode.UNAUTHORIZED_USER);
        }

        final Member member = getMemberFromSession(request.getSession());
        request.setAttribute(SESSION_MEMBER_KEY, member);

        return true;
    }

    private Member getMemberFromSession(final HttpSession session) {
        final Member member = (Member) session.getAttribute(SESSION_MEMBER_KEY);

        if (member == null) {
            throw new AuthException(AuthExceptionCode.UNAUTHORIZED_USER);
        }

        return member;
    }

}
