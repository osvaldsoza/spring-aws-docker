package com.osvald.soza.beerstore.repository;

import com.osvald.soza.beerstore.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeersRepository extends JpaRepository<Beer, Long> {
}
