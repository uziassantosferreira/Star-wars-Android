package br.com.starwars.domain.interactor.listcharacters;

import java.util.List;

import br.com.starwars.domain.executor.ThreadExecutor;
import br.com.starwars.domain.interactor.UseCase;
import br.com.starwars.domain.models.Character;
import br.com.starwars.domain.repositories.CharacterRepository;
import io.reactivex.Single;

/**
 * Created by Uzias on 18/01/17.
 */

public class GetListCharactersUseCase extends UseCase {

    private final CharacterRepository characterRepository;

    public GetListCharactersUseCase(ThreadExecutor subscriberOn, ThreadExecutor observerOn, CharacterRepository characterRepository) {
        super(subscriberOn, observerOn);
        this.characterRepository = characterRepository;
    }

    public Single<List<Character>> getListCharacters() {
        return characterRepository.getListCharacters();
    }
}
