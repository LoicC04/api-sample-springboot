package fr.carneyandco.sample.api.controllers;

import fr.carneyandco.sample.api.MyApplication;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
@AutoConfigureMockMvc
public class FinalControllerE2ETest {

    @Autowired
    private MockMvc mvc;

    @Ignore("No auth yet")
    @Test
    public void shouldBeUnauthorizedWhenHello() throws Exception {
        // GIVEN
        String uri = "/api/final";

        // WHEN
        mvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        // THEN
        // is ok
    }

    @Test
    public void shouldSuccessWhenHello() throws Exception {
        // GIVEN
        String uri = "/api/final";

        // WHEN
        mvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$.message", Matchers.is("Hello world !")));

        // THEN
        // is ok
    }

}
