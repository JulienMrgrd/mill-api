package fr.ippon.mill.farmer.infrastructure.primary;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class FarmerRessourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_create_new_farm() throws Exception {
        // Given
        String body = """
                {
                "email": "jean.dupont@fermier.fr",
                "firstName": "Jean",
                "lastName": "Dupont",
                "phoneNumber": "+33 123456789"
                }
                """;
        // When
        mockMvc.perform(post("/api/farmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(body))
                // Then
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.not(Matchers.blankOrNullString())));
    }
}