package com.style.auth.presentation;

import com.style.fixture.MemberFixture;
import com.style.auth.presentation.request.SignInRequest;
import com.style.common.utils.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignInTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("로그인 성공 테스트")
    void signInSuccessTest() throws Exception {
        SignInRequest userSignIn = MemberFixture.createSignInRequest("admin@naver.com", "qwer1234!!");
        String request = JsonUtil.objectToJson(userSignIn);

        mockMvc.perform(post("/member/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 비밀번호 틀림")
    void signInFailTest() throws Exception {
        SignInRequest userSignIn = MemberFixture.createSignInRequest("admin@naver.com", "qwer1234!!@@");
        String request = JsonUtil.objectToJson(userSignIn);

        mockMvc.perform(post("/member/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
