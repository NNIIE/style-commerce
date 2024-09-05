package com.style.common.resolver;

import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.common.domain.CurrentAdminMember;
import com.style.common.domain.CurrentMember;
import com.style.common.domain.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.style.common.utils.MemberConst.SESSION_MEMBER_KEY;

@Component
public class CurrentMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterAnnotation(CurrentAdminMember.class) != null
                || parameter.getParameterAnnotation(CurrentMember.class) != null)
                && parameter.getParameterType().equals(Member.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Member member = (Member) request.getAttribute(SESSION_MEMBER_KEY);

        if (parameter.getParameterAnnotation(CurrentAdminMember.class) != null) {
            checkAdmin(member);
        }

        return member;
    }

    private void checkAdmin(Member member) {
        if (!member.getIsAdmin()) {
            throw new MemberException(MemberExceptionCode.UNAUTHORIZED_MEMBER);
        }
    }

}
