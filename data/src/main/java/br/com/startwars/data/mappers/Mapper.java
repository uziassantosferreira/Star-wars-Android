package br.com.startwars.data.mappers;

/**
 * Created by Uzias on 18/01/17.
 */

public interface Mapper<I, O> {
    O transform(I t);
}
