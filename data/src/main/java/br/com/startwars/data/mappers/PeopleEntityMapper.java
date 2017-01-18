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
        return peopleEntity;
    }
}
