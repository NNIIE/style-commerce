package com.style.member.application;

import com.style.member.domain.Address;
import com.style.member.domain.Member;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.infra.encrypt.PasswordEncoder;
import com.style.member.infra.repository.MemberRepository;
import com.style.member.presentation.request.CreateAddressRequest;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;
import com.style.member.presentation.request.UpdateRequest;
import com.style.member.presentation.response.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(final SignUpRequest request) {
        existsByEmail(request.getEmail());
        existsByNickname(request.getNickname());

        final Member member = Member.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(request.getIsAdmin())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public void profileUpdate(final UUID memberId, final UpdateRequest request) {
        final Member member = getMember(memberId);

        if (request.isNicknameUpdate()) {
            existsByNickname(request.getNickname());
            member.setNickname(request.getNickname());
        }

        if (request.isPasswordUpdate()) {
            member.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    @Transactional(readOnly = true)
    public MemberProfile getProfile(final UUID memberId) {
        final Member member = getMember(memberId);

        return new MemberProfile(
                member.getNickname(),
                member.getEmail(),
                member.getIsAdmin(),
                member.getAddresses()
        );
    }

    @Transactional
    public void signOff(final Member member, final SignOffRequest request) {
        verifyPassword(request.getPassword(), member.getPassword());
        memberRepository.delete(member);
    }

    @Transactional
    public void createAddress(final UUID memberId, final CreateAddressRequest request) {
        final Member member = getMember(memberId);
        final Address address = Address.builder()
                .member(member)
                .province(request.getProvince())
                .city(request.getCity())
                .district(request.getDistrict())
                .build();

        member.addAddress(address);
        memberRepository.save(member);
    }

    @Transactional
    public void deleteAddress(final UUID memberId, final Long addressId) {
        final Member member = getMember(memberId);
        member.deleteAddress(addressId);
        memberRepository.save(member);
    }

    private Member getMember(final UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionCode.MEMBER_NOT_FOUNT));
    }

    private void existsByEmail(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(MemberExceptionCode.EXISTS_MEMBER_EMAIL);
        }
    }

    private void existsByNickname(final String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new MemberException(MemberExceptionCode.EXISTS_MEMBER_NICKNAME);
        }
    }

    private void verifyPassword(final String requestPassword, final String memberPassword) {
        if (!passwordEncoder.verifyPassword(requestPassword, memberPassword)) {
            throw new MemberException(MemberExceptionCode.INVALID_PASSWORD);
        }
    }

}
