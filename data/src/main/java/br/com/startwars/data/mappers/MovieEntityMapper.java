package br.com.startwars.data.mappers;

import br.com.startwars.data.entity.MovieApiEntity;
import br.com.startwars.data.entity.MovieEntity;
import br.com.startwars.data.entity.ResponseMovieApiEntity;

/**
 * Created by Uzias on 18/01/17.
 */

public class MovieEntityMapper implements Mapper<ResponseMovieApiEntity, MovieEntity>  {

    private String baseUrlImage;

    public MovieEntityMapper(String baseUrlImage) {
        this.baseUrlImage = baseUrlImage;
    }

    @Override
    public MovieEntity transform(ResponseMovieApiEntity responseMovieApiEntity) {
        MovieEntity movieEntity = new MovieEntity();
        if (responseMovieApiEntity.getMovieApiEntities() != null &&
                !responseMovieApiEntity.getMovieApiEntities().isEmpty()){
            MovieApiEntity movieApiEntity = responseMovieApiEntity.getMovieApiEntities().get(0);
            movieEntity.setPosterPath(baseUrlImage + movieApiEntity.getPosterPath());
        }
        return movieEntity;
    }


}
