package br.com.startwars.data.mappers;

import br.com.startwars.data.entity.FilmApiEntity;
import br.com.startwars.data.entity.FilmEntity;
/**
 * Created by Uzias on 18/01/17.
 */

public class FilmEntityMapper implements Mapper<FilmApiEntity, FilmEntity>  {

    @Override
    public FilmEntity transform(FilmApiEntity filmApiEntity) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setUrl(filmApiEntity.getUrl());
        filmEntity.setDirector(filmApiEntity.getDirector());
        filmEntity.setOpeningCrawl(filmApiEntity.getOpeningCrawl());
        filmEntity.setProducer(filmApiEntity.getProducer());
        filmEntity.setTitle(filmApiEntity.getTitle());

        return filmEntity;
    }


}
