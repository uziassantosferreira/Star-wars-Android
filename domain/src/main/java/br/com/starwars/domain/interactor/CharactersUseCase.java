package br.com.starwars.domain.interactor;

import java.util.List;

import br.com.starwars.domain.executor.ThreadExecutor;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.models.Film;
import br.com.starwars.domain.models.Movie;
import br.com.starwars.domain.repositories.CharacterRepository;
import io.reactivex.Single;

/**
 * Created by Uzias on 20/01/17.
 */

public class CharactersUseCase extends UseCase {
    private final CharacterRepository characterRepository;

    public CharactersUseCase(ThreadExecutor subscriberOn, ThreadExecutor observerOn, CharacterRepository characterRepository) {
        super(subscriberOn, observerOn);
        this.characterRepository = characterRepository;
    }

    public Single<List<Character>> getListCharacters() {
        return characterRepository.getListCharacters();
    }

    public Single<Character> getCharacterByUrl(String url) {
        return characterRepository.getCharacterByUrl(url);
    }

    public Single<Film> getFilmByUrl(String url) {
        return characterRepository.getFilmByUrl(url);
    }

    public Single<Movie> getMovieByName(String name) {
        return characterRepository.getPosterByNameFilm(name);
    }
}
