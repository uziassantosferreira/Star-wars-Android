package br.com.startwars.data.mappers;

import br.com.startwars.data.entity.PeopleApiEntity;
import br.com.startwars.data.entity.PeopleEntity;

/**
 * Created by Uzias on 18/01/17.
 */

public class PeopleEntityMapper implements Mapper<PeopleApiEntity, PeopleEntity>  {

    @Override
    public PeopleEntity transform(PeopleApiEntity peopleApiEntity) {
        PeopleEntity peopleEntity = new PeopleEntity();
        peopleEntity.setUrl(peopleApiEntity.getUrl());
        peopleEntity.setName(peopleApiEntity.getName());
        peopleEntity.setHeight(peopleApiEntity.getHeight());
        peopleEntity.setMass(peopleApiEntity.getMass());
        peopleEntity.setSkinColor(peopleApiEntity.getSkinColor());
        peopleEntity.setHairColor(peopleApiEntity.getHairColor());
        peopleEntity.setGender(peopleApiEntity.getGender());
        peopleEntity.setBirthYear(peopleApiEntity.getBirthYear());
        peopleEntity.setEyeColor(peopleApiEntity.getEyeColor());
        peopleEntity.setFilms(peopleApiEntity.getFilms());
        return peopleEntity;
    }


}
