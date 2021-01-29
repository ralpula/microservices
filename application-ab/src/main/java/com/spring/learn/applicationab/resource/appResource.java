package com.spring.learn.applicationab.resource;

import com.spring.learn.applicationab.models.CatelogItem;
import com.spring.learn.applicationab.models.Movie;
import com.spring.learn.applicationab.models.Rating;
import com.spring.learn.applicationab.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class appResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatelogItem> getCatelog(String userId){

        //1. get all rated movie IDs from service A
        UserRating ratings = restTemplate.getForObject("http://application-a/ratingsdata/users/"+userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            //2. For each movie ID, call movie Info service B and get the details.
            Movie movie = restTemplate.getForObject("http://application-b/movies/"+rating.getMovieId(), Movie.class);
            //Put them all together
            return new CatelogItem(movie.getName(),"Test desc",rating.getRating());
        }).collect(Collectors.toList());




    }
}
