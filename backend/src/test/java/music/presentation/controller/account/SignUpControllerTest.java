package music.presentation.controller.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import music.SecurityConfig;
import music.application.dto.account.RegisterMemberAccountDto;
import music.application.usecase.account.RegisterMemberAccountUseCase;

@WebMvcTest(SignUpController.class)
@Import(SecurityConfig.class)
public class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterMemberAccountUseCase useCase;

    @Test
    void signUp() throws Exception {
        RegisterMemberAccountDto dto = new RegisterMemberAccountDto(
                "test_id",
                "test_name",
                "TestPass0");
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/account/signup").contentType(MediaType.APPLICATION_JSON).content(body).with(csrf()))
                .andExpect(status().isOk());
    }

}
