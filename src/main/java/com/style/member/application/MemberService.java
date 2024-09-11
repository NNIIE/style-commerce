package com.style.member.application;

import com.style.member.domain.entity.Address;
import com.style.member.domain.entity.Member;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.infra.encrypt.PasswordEncoder;
import com.style.member.infra.repository.MemberRepository;
import com.style.member.presentation.request.CreateAddressRequest;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;
import com.style.member.presentation.request.UpdateMemberRequest;
import com.style.member.presentation.response.MemberProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberProfileResponse getProfile(final UUID memberId) {
        final Member member = getMemberWithAddresses(memberId);

        return new MemberProfileResponse(
                member.getNickname(),
                member.getEmail(),
                member.getRole(),
                member.getAddresses(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

    @Transactional
    public void signUp(final SignUpRequest request) {
        existsByEmail(request.getEmail());
        existsByNickname(request.getNickname());

        final Member member = Member.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public void profileUpdate(final UUID memberId, final UpdateMemberRequest request) {
        final Member member = getMember(memberId);

        if (request.isNicknameUpdate()) {
            existsByNickname(request.getNickname());
            member.setNickname(request.getNickname());
        }

        if (request.isPasswordUpdate()) {
            member.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    @Transactional
    public void signOff(final UUID memberId, final SignOffRequest request) {
        final Member member = getMember(memberId);
        verifyPassword(request.getPassword(), member.getPassword());

        memberRepository.delete(member);
    }

    @Transactional
    public void createAddress(final UUID memberId, final CreateAddressRequest request) {
        final Member member = getMemberWithAddresses(memberId);
        final Address address = Address.builder()
                .member(member)
                .province(request.getProvince())
                .city(request.getCity())
                .district(request.getDistrict())
                .build();

        member.registerAddress(address);
    }

    @Transactional
    public void deleteAddress(final UUID memberId, final Long addressId) {
        final Member member = getMemberWithAddresses(memberId);
        member.deleteAddress(addressId);
    }

    public Member getMember(final UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionCode.MEMBER_NOT_FOUNT));
    }

    public Member getMemberWithAddresses(final UUID memberId) {
        return memberRepository.findMemberWithAddressesById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionCode.MEMBER_NOT_FOUNT));
    }

    public Member getMemberWithBrandsAndProducts(final UUID memberId) {
        return memberRepository.findMemberWithBrandsAndProductsById(memberId)
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
