package com.makinus.multisecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by abuabdul on 2020-04-23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class WebSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIfLandingPageIsSecured() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("landing"));
    }

    @Test
    public void testIfRegularHomePageIsSecured() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/regular/home"));
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/regular/login"));
    }

    @Test
    public void testIfSpecialHomePageIsSecured() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/special/home"));
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/special/login"));
    }

    @Test
    @WithMockUser
    public void testIfLoggedUserHasAccessToRegularHomePage() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/regular/home"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("regular/home"));
    }

    @Test
    @WithMockUser
    public void testIfLoggedUserHasAccessToSpecialHomePage() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/special/home"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("special/home"));
    }

}
