package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.os.Environment;
import android.util.AndroidException;


/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class AndroidFileManager {

  protected static void writeFileToExternalStorage(Context context, String imagePath, InputStream imageStream)
      throws AndroidException {
    String state = Environment.getExternalStorageState();

    if (!Environment.MEDIA_MOUNTED.equals(state)) {
      throw new AndroidException("External storage need to be in Read & Write acces");
    }

    // writing image to external storage (local to us application)
    File file = new File(context.getExternalFilesDir(null), imagePath);
    try {
      OutputStream outStream = new FileOutputStream(file);
      byte[] data = new byte[imageStream.available()];

      imageStream.read(data);
      outStream.write(data);

      imageStream.close();
      outStream.close();
    } catch (IOException e) {
      throw new AndroidException(e);
    }
  }
}

