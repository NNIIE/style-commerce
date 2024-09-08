package com.style.member.infra.repository;

import com.style.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}
