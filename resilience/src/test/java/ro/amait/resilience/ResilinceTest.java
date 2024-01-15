package ro.amait.resilience;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ResilinceTest {


    @RegisterExtension
    static WireMockExtension EXTERNAL_SERVICE = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(9090))
            .build();
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testFoo() throws Exception {
        EXTERNAL_SERVICE.stubFor(get("/warehouse/unstable/external-foo-circuit-breaker").willReturn(serverError()));

        for (int i = 0; i < 5; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker", String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (int i = 0; i < 5; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker", String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        }

        TimeUnit.SECONDS.sleep(10);

        for (int i = 0; i < 5; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker", String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (int i = 0; i < 1; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker", String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


    @Test
    public void testIsWorking() throws Exception{
      EXTERNAL_SERVICE.stubFor(get("/warehouse/unstable/external-foo-circuit-breaker").willReturn(new ResponseDefinitionBuilder().withStatus(200)));
        TimeUnit.SECONDS.sleep(10);
        for (int i = 0; i < 5; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker", String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

}