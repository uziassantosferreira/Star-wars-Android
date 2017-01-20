package br.com.startwars.data.repositories;

import java.util.List;

import br.com.startwars.data.api.ApiClient;
import br.com.startwars.data.mappers.CharacterMapper;
import br.com.startwars.data.mappers.FilmMapper;
import br.com.startwars.data.store.FilmCache;
import br.com.startwars.data.store.PeopleCache;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.models.Film;
import br.com.starwars.domain.repositories.CharacterRepository;
import io.reactivex.Single;
/**
 * Created by Uzias on 18/01/17.
 */

public class CharacterDataRepository implements CharacterRepository {

    private final PeopleCache peopleCache;
    private final FilmCache filmCache;
    private CharacterMapper characterMapper;
    private FilmMapper filmMapper;

    public CharacterDataRepository(PeopleCache peopleCache, CharacterMapper characterMapper
            , FilmCache filmCache, FilmMapper filmMapper) {
        this.peopleCache = peopleCache;
        this.filmCache = filmCache;
        this.characterMapper = characterMapper;
        this.filmMapper = filmMapper;
    }


    @Override
    public Single<Character> getCharacterByUrl(String url) {
        return peopleCache.getByUrl(url)
                .onErrorResumeNext(ApiClient.getPeople(url, peopleCache))
                .map(characterMapper::transform);
    }

    @Override
    public Single<Film> getFilmByUrl(String url) {
        return filmCache.getByUrl(url)
                .onErrorResumeNext(ApiClient.getFilm(url, filmCache))
                .map(filmMapper::transform);
    }

    @Override
    public Single<List<Character>> getListCharacters() {
        return peopleCache.getList().map(characterMapper::transform);
    }
}
