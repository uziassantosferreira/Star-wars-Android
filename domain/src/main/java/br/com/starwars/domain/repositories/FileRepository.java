package br.com.starwars.domain.repositories;

import java.io.File;

import io.reactivex.Single;

/**
 * Created by Uzias on 22/01/17.
 */

public interface FileRepository {
    Single<File> saveImage(byte[] byteArray);
}
