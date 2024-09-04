package com.style.member.presentation;

import com.style.common.exception.member.MemberExceptionCode;
import com.style.common.utils.JsonUtil;
import com.style.fixture.MemberFixture;
import com.style.member.presentation.request.SignUpRequest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SignUpTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원 가입 시나리오 테스트")
    void signUpTest() throws Exception {
        signUpSuccessTest();
        existsEmailTest();
    }

    void signUpSuccessTest() throws Exception {
        SignUpRequest signUpRequest = MemberFixture.createSignUpRequest("greg123@naver.com", "greg", "qwer1234!!", false);
        String request = JsonUtil.objectToJson(signUpRequest);

        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    void existsEmailTest() throws Exception {
        SignUpRequest signUpRequest = MemberFixture.createSignUpRequest("greg123@naver.com", "greggreg", "qwer1234!!", false);
        String request = JsonUtil.objectToJson(signUpRequest);

        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(MemberExceptionCode.EXISTS_MEMBER_EMAIL.getCode()))
                .andExpect(jsonPath("$.message").value(MemberExceptionCode.EXISTS_MEMBER_EMAIL.getMessage()))
                .andDo(print());
    }

}