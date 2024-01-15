package ro.amait.resilience;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
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
public class RetryTest {


    @RegisterExtension
    static WireMockExtension EXTERNAL_SERVICE = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(9090))
            .build();
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testRetry() {
        EXTERNAL_SERVICE.stubFor(WireMock.get("/warehouse/unstable/external-foo-retry").willReturn(ok()));

        ResponseEntity<String> response1 = restTemplate.getForEntity("/retry", String.class);
        EXTERNAL_SERVICE.verify(1, getRequestedFor(urlEqualTo("/warehouse/unstable/external-foo-retry")));

        EXTERNAL_SERVICE.resetRequests();

        EXTERNAL_SERVICE.stubFor(WireMock.get("/external-foo-retry").willReturn(serverError()));

        ResponseEntity<String> response2 = restTemplate.getForEntity("/retry", String.class);

       // assertThat(response2.getBody()).isEqualTo("all retries have exhausted");
        EXTERNAL_SERVICE.verify(3, getRequestedFor(urlEqualTo("/warehouse/unstable/external-foo-retry")));
    }

}