package com.style.member.infra.repository;

import com.style.member.domain.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @EntityGraph(attributePaths = {"addresses"})
    Optional<Member> findMemberWithAddressesById(UUID memberId);

}
