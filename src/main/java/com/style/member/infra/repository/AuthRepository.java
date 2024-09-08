package com.style.member.infra.repository;

import com.style.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByEmail(String email);

}
