package com.osvald.soza.beerstore.service;

import com.osvald.soza.beerstore.model.Beer;
import com.osvald.soza.beerstore.repository.BeersRepository;
import com.osvald.soza.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {


    private BeersRepository beersRepository;

    public BeerService(@Autowired BeersRepository beersRepository){
        this.beersRepository = beersRepository;
    }

    public List<Beer> listBeers(){
        return beersRepository.findAll();
    }

    public Beer save(final Beer beer) {
        Optional<Beer> beerByNameAndType = beersRepository.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent()) {
            throw new BeerAlreadyExistException();
        }

        return beersRepository.save(beer);
    }
}
