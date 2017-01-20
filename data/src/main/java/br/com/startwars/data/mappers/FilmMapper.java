package br.com.startwars.data.mappers;


import br.com.startwars.data.entity.FilmEntity;
import br.com.starwars.domain.models.Film;

/**
 * Created by Uzias on 18/01/17.
 */

public class FilmMapper implements Mapper<FilmEntity, Film>  {

    @Override
    public Film transform(FilmEntity filmEntity) {
        Film film = new Film();
        film.setTitle(filmEntity.getTitle());
        film.setUrl(filmEntity.getUrl());
        film.setProducer(filmEntity.getProducer());
        film.setOpeningCrawl(filmEntity.getOpeningCrawl());
        film.setDirector(filmEntity.getDirector());
        film.setReleaseDate(filmEntity.getReleaseDate());
        return film;
    }

}
