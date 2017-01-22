package br.com.startwars.data.mappers;

import br.com.startwars.data.entity.MovieEntity;
import br.com.starwars.domain.models.Movie;

/**
 * Created by Uzias on 22/01/17.
 */

public class MovieMapper implements Mapper<MovieEntity, Movie>  {

    @Override
    public Movie transform(MovieEntity t) {
        Movie movie = new Movie();
        movie.setPosterPath(t.getPosterPath());
        return movie;
    }

}
