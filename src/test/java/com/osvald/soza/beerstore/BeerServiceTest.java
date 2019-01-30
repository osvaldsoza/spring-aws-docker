package com.osvald.soza.beerstore;

import com.osvald.soza.beerstore.model.Beer;
import com.osvald.soza.beerstore.model.BeerType;
import com.osvald.soza.beerstore.repository.BeersRepository;
import com.osvald.soza.beerstore.service.BeerService;
import com.osvald.soza.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private BeersRepository beersRepositoryMocked;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beersRepositoryMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void shoul_deny_creation_of_beer_that_exixts() {
        Beer beerInDataBase = new Beer();
        beerInDataBase.setId(10l);
        beerInDataBase.setName("Heineken");
        beerInDataBase.setType(BeerType.LAGER);
        beerInDataBase.setVolume(new BigDecimal("355"));

        Mockito.when(beersRepositoryMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDataBase));

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));
        beerService.save(newBeer);
    }

    @Test
    public void should_create_new_beer() {
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDatabase = new Beer();
        newBeerInDatabase.setId(10l);
        newBeerInDatabase.setName("Heineken");
        newBeerInDatabase.setType(BeerType.LAGER);
        newBeerInDatabase.setVolume(new BigDecimal("355"));

        Mockito.when(beersRepositoryMocked.save(newBeer)).thenReturn(newBeerInDatabase);

        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }

    @Test
    public void should_verify_exist_beer(){
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDatabase = new Beer();
        newBeerInDatabase.setId(10l);
        newBeerInDatabase.setName("Heineken");
        newBeerInDatabase.setType(BeerType.LAGER);
        newBeerInDatabase.setVolume(new BigDecimal("355"));

        Mockito.when(beersRepositoryMocked.findByNameAndType(newBeer.getName(), newBeer.getType())).thenReturn(Optional.ofNullable(newBeer));

       assertThat(newBeer.getName(), equalTo(newBeerInDatabase.getName()));
       assertThat(newBeer.getType(), equalTo(newBeerInDatabase.getType()));
    }

//    @Test
//    public void should_delete_beer(){
//        Beer beerInDatabase = new Beer();
//        beerInDatabase.setId(10l);
//        beerInDatabase.setName("Heineken");
//        beerInDatabase.setType(BeerType.LAGER);
//        beerInDatabase.setVolume(new BigDecimal("355"));
//
//        Mockito.when(beersRepositoryMocked.deleteById(9L)).then(beerInDatabase);
//    }

    @Test
    public void should_find_beers() {
        List<Beer> findBeerInDatabase = new ArrayList<>();

        Beer beer1 = new Beer();
        beer1.setName("Heineken");
        beer1.setType(BeerType.LAGER);
        beer1.setVolume(new BigDecimal("355"));
        findBeerInDatabase.add(beer1);

        Beer beer2 = new Beer();
        beer2.setName("Privovar Samson");
        beer2.setType(BeerType.BOCK);
        beer2.setVolume(new BigDecimal("355"));
        findBeerInDatabase.add(beer2);

        Mockito.when(beersRepositoryMocked.findAll()).thenReturn(findBeerInDatabase);

        assertThat(findBeerInDatabase.size(), is(2));
    }
}
