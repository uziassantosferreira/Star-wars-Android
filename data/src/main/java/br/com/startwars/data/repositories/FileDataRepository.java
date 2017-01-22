package br.com.startwars.data.repositories;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.startwars.data.Utils;
import br.com.starwars.domain.repositories.FileRepository;
import io.reactivex.Single;

/**
 * Created by Uzias on 22/01/17.
 */

public class FileDataRepository implements FileRepository {

    private final Context context;
    private static final String IMAGE_EXTENSION = "jpg";

    public FileDataRepository(Context applicationContext) {
        this.context = applicationContext;
    }


    @Override
    public Single<File> saveImage(byte[] byteArray) {
        return Single.create(emitter -> {
            File file = new File(context.getFilesDir(), String.format("%s.%s", Utils.getRandomFileName(), IMAGE_EXTENSION));
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(file);
                output.write(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onError(e);
            } finally {
                if (null != output) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        emitter.onError(e);
                    }
                }
            }
            emitter.onSuccess(file);
        });
    }
}
