package com.style.member.infra;

import com.style.common.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}
