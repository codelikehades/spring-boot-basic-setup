package com.codelikehades.springbootblueprint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StarterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextShouldLoad() {
    }

    @Test
    void actuatorEndpointsShouldBeUp() throws Exception {
        hitActuatorEndpoint("health").andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));
        hitActuatorEndpoint("beans").andExpect(status().isOk());
        hitActuatorEndpoint("metrics").andExpect(status().isOk());
    }

    private ResultActions hitActuatorEndpoint(String endpoint) throws Exception {
        return mockMvc.perform(get("/mgmt/{endpoint}", endpoint));
    }


}