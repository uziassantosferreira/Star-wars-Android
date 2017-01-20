package br.com.startwars.data.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.startwars.data.entity.PeopleEntity;
import br.com.starwars.domain.models.Character;

/**
 * Created by Uzias on 18/01/17.
 */

public class CharacterMapper implements Mapper<PeopleEntity, Character>  {

    @Override
    public Character transform(PeopleEntity peopleEntity) {
        Character character = new Character();
        character.setUrl(peopleEntity.getUrl());
        character.setName(peopleEntity.getName());
        character.setBirthYear(peopleEntity.getBirthYear());
        character.setEyeColor(peopleEntity.getEyeColor());
        character.setSkinColor(peopleEntity.getSkinColor());
        character.setGender(peopleEntity.getGender());
        character.setHeight(peopleEntity.getHeight());
        character.setHairColor(peopleEntity.getHairColor());
        character.setMass(peopleEntity.getMass());
        return character;
    }

    public List<Character> transform(List<PeopleEntity> peopleEntities) {
        List<Character> characters = new ArrayList<>();
        if (peopleEntities != null && !peopleEntities.isEmpty()){
            for (PeopleEntity peopleEntity: peopleEntities){
                characters.add(transform(peopleEntity));
            }
        }

        return characters;
    }
}
