package br.com.startwars.data.repositories;

import java.util.List;
import java.util.Observable;

import br.com.startwars.data.api.ApiClient;
import br.com.startwars.data.entity.PeopleEntity;
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
    public Single<Character> getCharacterByUrl(String url) {
        return peopleCache.getByUrl(url)
                .onErrorResumeNext(ApiClient.getPeople(url, peopleCache))
                .flatMap(peopleEntity -> {
                    if (peopleEntity == null){
                        return ApiClient.getPeople(url, peopleCache);
                    }
                    return Single.just(peopleEntity);
                })
                .map(characterMapper::transform);
    }

    @Override
    public Single<List<Character>> getListCharacters() {
        return peopleCache.getList().map(characterMapper::transform);
    }
}
