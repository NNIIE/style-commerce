package com.style.common.domain;

import com.style.member.domain.MemberRole;

import java.io.Serializable;
import java.util.UUID;

public record SessionMember(UUID id, MemberRole role) implements Serializable {
}
