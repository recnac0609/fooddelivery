package be.ucll.backend2.springsec.integration;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Gebruik het testprofiel
public class KlantControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRegistrerenKlant() {

        String klantJson = """
            {
                "voornaam": "Loic",
                "achternaam": "Wens",
                "email": "loic.wens@student.ucll.be",
                "adres": {
                    "straat": "holsbeeksesteenweg",
                    "nummer": "135",
                    "postcode": "3010",
                    "gemeente": "Kessel-Lo"
                },
                "telefoonnummer": "0499734013"
            }
        """;

    }
    @Test
    void testGetAlleKlanten() {
        webTestClient.get()
                .uri("/klanten")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> {
                    String json = new String(response.getResponseBody());
                    List<?> klanten = JsonPath.parse(json).read("$");
                    assertThat(klanten.size()).isGreaterThan(0); // Assert met AssertJ
                });
    }

    @Test
    void testUpdateKlant() {
        String updatedKlantJson = """
        {
            "voornaam": "Loic",
                "achternaam": "Wens",
                "email": "loic.wens@student.ucll.be",
                "adres": {
                    "straat": "holsbeeksesteenweg",
                    "nummer": "135",
                    "postcode": "3010",
                    "gemeente": "Kessel-Lo"
                },
                "telefoonnummer": "0499734013"
        }
    """;

        webTestClient.put()
                .uri("/klanten/1")
                .bodyValue(updatedKlantJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.email").isEqualTo("jan.jansen@update.com");
    }

    @Test
    void testVerwijderKlant() {
        webTestClient.delete()
                .uri("/klanten/1") // Verander dit naar het juiste endpoint
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testVoegGerechtToeAanBestelling() {
        String gerechtJson = """
        {
            "naam": "Pizza Margherita",
            "aantal": 2
        }
    """;

        webTestClient.post()
                .uri("/bestellingen/1/gerechten")
                .bodyValue(gerechtJson)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.naam").isEqualTo("Pizza Margherita")
                .jsonPath("$.aantal").isEqualTo(2);
    }



    }
