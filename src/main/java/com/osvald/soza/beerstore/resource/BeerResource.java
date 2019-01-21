package com.osvald.soza.beerstore.resource;

import com.osvald.soza.beerstore.model.Beer;
import com.osvald.soza.beerstore.repository.BeersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private BeersRepository beersRepository;

    @GetMapping
    public List<Beer> all() {
        return beersRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer){
        return  beersRepository.save(beer);
    }
}
