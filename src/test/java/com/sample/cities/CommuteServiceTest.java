package com.sample.cities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.cities.config.CitiesConfig;
import com.sample.cities.model.City;
import com.sample.cities.service.CommuteService;
/**
 * 
 * @author Gowthami V
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CommuteServiceTest {
	
	@Autowired
	CitiesConfig citiesConfig;
	
	@Autowired
	CommuteService commuteService;

	@Test
    public void fileLoad() {
        Assert.assertFalse("File load failed", citiesConfig.getCityMap().isEmpty());
    }

    @Test
    public void sameCity() {
        City city = City.build("Boston");
        Assert.assertEquals("Yes", commuteService.determineCommute(city, city));
    }

    @Test
    public void neighbours() {
        City cityA = citiesConfig.getCity("BOSTON");
        City cityB = citiesConfig.getCity("NEWARK");

        Assert.assertNotNull("Invalid test data. City not found: ", cityA);
        Assert.assertNotNull("Invalid test data. City not found: ", cityB);

        Assert.assertEquals("Yes", commuteService.determineCommute(cityA, cityB));
    }

    @Test
    public void distantConnected() {
        City cityA = citiesConfig.getCity("BOSTON");
        City cityB = citiesConfig.getCity("PHILADELPHIA");

        Assert.assertNotNull("Invalid test data. City not found: ", cityA);
        Assert.assertNotNull("Invalid test data. City not found: ", cityB);

        Assert.assertEquals("Yes", commuteService.determineCommute(cityA, cityB));
    }


}
