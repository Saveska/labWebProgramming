package mk.ukim.finki.wp.lab;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetCourses() throws Exception {

        MockHttpServletRequestBuilder coursesRequest = MockMvcRequestBuilders.get("/courses");

        this.mockMvc.perform(coursesRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));
    }

    //dali login praj redirect na courses

    @Test
    public void testLoginRedirect() throws Exception {
        this.mockMvc.perform(post("/login")
                .param("username", "admin")
                .param("password","admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));

    }


    //dali ako ne e uspesen praj bad credentials
    @Test
    public void testLoginRedirectBadCredentials() throws Exception {

        this.mockMvc.perform(post("/login")
                        .param("username", "invalidUser")
                        .param("password","invalidPassword")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());


    }


}
