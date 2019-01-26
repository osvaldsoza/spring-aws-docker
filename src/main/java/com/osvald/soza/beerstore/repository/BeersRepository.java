package com.osvald.soza.beerstore.repository;

import com.osvald.soza.beerstore.model.Beer;
import com.osvald.soza.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeersRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);
}
