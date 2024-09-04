package com.style.member.infra.encrypt;

public interface PasswordEncoder {

    String encode(String password);

    boolean verifyPassword(String requestPassword, String memberPassword);

}
