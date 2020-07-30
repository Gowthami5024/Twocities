package com.sample.cities;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * 
 * @author Gowthami V
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CommuteControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void restConnectedIT() {

        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "Philadelphia");

        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assert.assertEquals("Yes", body);
    }

    @Test
    public void restNotConnectedIT() {

        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "Albany");

        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assert.assertEquals("No", body);
    }



}
