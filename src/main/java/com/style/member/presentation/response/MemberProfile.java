package com.style.member.presentation.response;

import com.style.brand.domain.entity.Brand;
import com.style.member.domain.entity.Address;
import com.style.member.domain.MemberRole;

import java.time.LocalDateTime;
import java.util.List;

public record MemberProfile(
        String nickname,
        String email,
        MemberRole role,
        List<Address> addresses,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
