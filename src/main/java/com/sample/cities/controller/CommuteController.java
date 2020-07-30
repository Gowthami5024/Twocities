package com.sample.cities.controller;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.cities.config.CitiesConfig;
import com.sample.cities.model.City;
import com.sample.cities.service.CommuteService;

/**
 * 
 * @author Gowthami V
 *
 */
@RestController
public class CommuteController {
	private static final Log LOG = LogFactory.getLog(CommuteController.class);
	
	@Autowired
	CitiesConfig citiesConfig;
	
	@Autowired
	CommuteService commuteService;
	
	
	@GetMapping(value = "/connected", produces = "text/plain")
    public String isConnected(
            @RequestParam(name = "origin", required = true) String origin,
            @RequestParam(name = "destination", required = true) String destination) {

        City originCity = citiesConfig.getCity(origin.toUpperCase());
        City destinationCity = citiesConfig.getCity(destination.toUpperCase());

        Objects.requireNonNull(originCity);
        Objects.requireNonNull(destinationCity);

        return commuteService.determineCommute(originCity, destinationCity);
    }

}
