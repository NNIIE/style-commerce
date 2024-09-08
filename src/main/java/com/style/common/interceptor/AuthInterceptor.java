package com.style.common.interceptor;

import com.style.common.domain.SessionMember;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.style.common.utils.Constants.SESSION_MEMBER_KEY;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if (excludePath(request)) {
            return true;
        }

        HttpSession session = request.getSession(false); // 세션이 없으면 새로 생성하지 않음
        if (session == null) {
            throw new MemberException(MemberExceptionCode.UNAUTHORIZED_MEMBER);
        }

        final SessionMember member = getMemberFromSession(request.getSession());
        request.setAttribute(SESSION_MEMBER_KEY, member);

        return true;
    }

    // 인터셉터 제외
    private boolean excludePath(HttpServletRequest request) {
        return request.getRequestURI().equals("/member") && request.getMethod().equals("POST");
    }

    private SessionMember getMemberFromSession(final HttpSession session) {
        final SessionMember member = (SessionMember) session.getAttribute(SESSION_MEMBER_KEY);

        if (member == null) {
            throw new MemberException(MemberExceptionCode.UNAUTHORIZED_MEMBER);
        }

        return member;
    }

}
