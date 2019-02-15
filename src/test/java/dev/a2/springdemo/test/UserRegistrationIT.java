package dev.a2.springdemo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("it")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationAccountExists() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", "test@test.dev")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationPasswordNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", "test@test.dev")
                                .param("password", "password")
                                .param("confirmPassword", "invalid")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationEmailNotValid() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", "test@test..test.dev")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("email", "newemail@test.dev")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                )
                .andExpect(redirectedUrl("/login"))
                .andExpect(status().is3xxRedirection());
    }

}
