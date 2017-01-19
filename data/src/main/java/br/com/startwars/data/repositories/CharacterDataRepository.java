package br.com.startwars.data.repositories;

import java.util.List;

import br.com.startwars.data.mappers.CharacterMapper;
import br.com.startwars.data.store.PeopleCache;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.repositories.CharacterRepository;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public class CharacterDataRepository implements CharacterRepository {

    private final PeopleCache peopleCache;
    private CharacterMapper characterMapper;

    public CharacterDataRepository(PeopleCache peopleCache, CharacterMapper characterMapper) {
        this.peopleCache = peopleCache;
        this.characterMapper = characterMapper;
    }

    @Override
    public Single<Character> getCharacter() {
        return peopleCache.getPeopleEntity().map(characterMapper::transform);
    }

    @Override
    public Single<List<Character>> getListCharacters() {
        return peopleCache.getListPeople().map(characterMapper::transform);
    }
}
