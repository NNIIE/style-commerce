package com.style.member.presentation.response;

import com.style.member.domain.Address;

import java.util.List;

public record MemberProfile(
        String nickname,
        String email,
        Boolean isAdmin,
        List<Address> addresses
) {
}
