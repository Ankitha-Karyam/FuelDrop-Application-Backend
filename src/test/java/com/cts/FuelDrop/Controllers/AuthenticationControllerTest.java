package com.cts.FuelDrop.Controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.FuelDrop.Controller.AuthenticationController;
import com.cts.FuelDrop.Entity.User;
import com.cts.FuelDrop.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.asm.Advice.This;

public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        when(userService.addUser(user)).thenReturn(user);

        mockMvc.perform(post("/auth/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
    }

//    @Test
//    public void testLoginUser() throws Exception {
//        User user = new User();
//        String token = "sampleToken";
//        when(authController.generateToken(user.getPhoneno(), user.getPassword(), user.getUsertype())).thenReturn(token);
//
//        mockMvc.perform(post("/auth/v1/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(user)))
//                .andExpect(status().isOk());
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}