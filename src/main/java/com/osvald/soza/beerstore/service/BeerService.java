package com.osvald.soza.beerstore.service;

import com.osvald.soza.beerstore.model.Beer;
import com.osvald.soza.beerstore.repository.BeersRepository;
import com.osvald.soza.beerstore.service.exception.BeerAlreadyExistException;
import com.osvald.soza.beerstore.service.exception.BeerNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private BeersRepository beersRepository;

    public BeerService(@Autowired BeersRepository beersRepository) {
        this.beersRepository = beersRepository;
    }

    public List<Beer> listBeers() {
        return beersRepository.findAll();
    }

    public Beer save(final Beer beer) {
        verifyBeerExist(beer);
        return beersRepository.save(beer);
    }

    public void delete(Long id) {
        Optional<Beer> beerId = beersRepository.findById(id);
        if (beerId.isPresent()) {
            beersRepository.deleteById(id);
        }else{
            throw  new BeerNotExistException();
        }
    }

    private void verifyBeerExist(final Beer beer) {
        Optional<Beer> beerByNameAndType = beersRepository.findByNameAndType(beer.getName(), beer.getType());
        if (beerByNameAndType.isPresent() && (beer.isNew() || isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get().equals(beer);
    }
}
