package com.auth_am.AM.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserByUsername() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/v1/getUserByUsername")
                .param("username", "Vivek"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Vivek"));
    }
}
