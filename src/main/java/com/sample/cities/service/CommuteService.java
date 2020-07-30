package com.sample.cities.service;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sample.cities.model.City;

/**
 * 
 * @author Gowthami V
 *
 */
@Service
public class CommuteService {
	private static final Log LOG = LogFactory.getLog(CommuteService.class);
	
	private CommuteService() { }
	
	/**
     * determinate destination city is reachable from origin city.
     * @param origin the origin
     * @param destination the destination
     * @return true if cities are connected
     */
    public String determineCommute(City origin, City destination) {

        LOG.info("Origin: " + origin.getName() + ", destination: " + destination.getName());

        if (origin.equals(destination)) 
        	return "Yes";

        if (origin.getNearby().contains(destination)) 
        	return "Yes";

        
        // The origin city was already visited since we have started from it
        Set<City> visited = new HashSet<>(Collections.singleton(origin));
      
        // Put all the neighboring cities into a bucket list
        Deque<City> neighbourList = new ArrayDeque<>(origin.getNearby());


        while (!neighbourList.isEmpty()) {

            City city = neighbourList.getLast();

            if (city.equals(destination)) 
            	return "Yes";

            // first time visit
            if (!visited.contains(city)) {
                visited.add(city);

                // add neighbour to list and remove already visited cities from the list
                neighbourList.addAll(city.getNearby());
                neighbourList.removeAll(visited);

                LOG.info("Visiting: ["
                        + city.getName()
                        + "] , neighbours: ["	
                        + (city.display())
                        + "], bucketlist: ["
                        + neighbourList.toString()
                        + "]");
            } else {
                // the city has been visited, so remove it from list
            	neighbourList.removeAll(Collections.singleton(city));
            }
        }

        return "No";
    }

}
