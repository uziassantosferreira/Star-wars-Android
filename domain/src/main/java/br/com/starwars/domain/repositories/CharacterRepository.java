package br.com.starwars.domain.repositories;

import java.util.List;

import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.models.Film;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public interface CharacterRepository {

    Single<Character> getCharacterByUrl(String url);

    Single<Film> getFilmByUrl(String url);

    Single<List<Character>> getListCharacters();
}
