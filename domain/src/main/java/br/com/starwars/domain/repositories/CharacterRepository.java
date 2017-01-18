package br.com.starwars.domain.repositories;

import java.util.List;

import br.com.starwars.domain.models.Character;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public interface CharacterRepository {

    Single<Character> getCharacter();

    Single<List<Character>> getListCharacters();
}
