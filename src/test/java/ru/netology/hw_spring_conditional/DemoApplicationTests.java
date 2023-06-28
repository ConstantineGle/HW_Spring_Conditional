package ru.netology.hw_spring_conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate testRestTemplate;
    public static GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    public static GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    public void contextLoadsDevapp() {
        final String expected = "Current profile is dev";
        String url = String.format("http://localhost:%d/profile", devapp.getMappedPort(8080));
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(expected, responseEntity.getBody());
    }

    @Test
    public void contextLoadsProdapp() {
        final String expected = "Current profile is production";
        String url = String.format("http://localhost:%d/profile", prodapp.getMappedPort(8081));
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(expected, responseEntity.getBody());
    }
}
