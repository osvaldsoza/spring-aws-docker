package com.osvald.soza.beerstore.resource;

import com.osvald.soza.beerstore.model.Beer;
import com.osvald.soza.beerstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<Beer> all() {
        return beerService.listBeers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer){
        return  beerService.save(beer);
    }
}
