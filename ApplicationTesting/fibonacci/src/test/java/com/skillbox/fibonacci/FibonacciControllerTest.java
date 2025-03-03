package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test Get Number: Index is equal or larger than 1")
    public void testGetNumberWhenLargerThanOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fibonacci/7"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("value")
                        .value(13));

    }

    @Test
    @DisplayName("Test Get Number: Index is lower than 1")
    public void testGetNumberWhenLowerThanOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fibonacci/-3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Index should be greater or equal to 1"));
    }

}
