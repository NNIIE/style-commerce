package com.auth.member.infra;

import com.core.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

}
