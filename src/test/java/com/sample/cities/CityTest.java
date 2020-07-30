package com.sample.cities;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.sample.cities.model.City;

public class CityTest {

    @Test
    public void build() {
        City city = City.build("Boston");
        Assert.assertEquals("BOSTON", city.getName());
    }

    @Test
    public void buildWithNeighbours() {
        City city = City.build("Boston");
        city.addNearby(City.build("New York"))
                .addNearby(City.build("Newark"));

        Set<City> nearby = city.getNearby();
        Assert.assertEquals(2, nearby.size());
        Assert.assertTrue(nearby.contains(City.build("Newark")));
    }


    @Test
    public void addNearby() {
        City city = City.build("Boston");
        city.addNearby(City.build("Newark"))
                .addNearby(City.build("New York"));

        Assert.assertEquals(2, city.getNearby().size());
    }

    @Test
    public void addNearbyDuplicates() {
        City city = City.build("Boston");
        city.addNearby(City.build("Newark"))
                .addNearby(City.build("NEWARK"))
                .addNearby(City.build("  Newark"));

        Assert.assertEquals(1, city.getNearby().size());
    }


}