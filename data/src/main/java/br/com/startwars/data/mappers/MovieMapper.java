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
        movie.setName(t.getName());
        movie.setSavedBitmapLocal(t.isSavedLocalBitmap());
        return movie;
    }

    public MovieEntity transform(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setPosterPath(movie.getPosterPath());
        movieEntity.setName(movie.getName());
        movieEntity.setSavedLocalBitmap(movie.isSavedBitmapLocal());
        return movieEntity;
    }

}
