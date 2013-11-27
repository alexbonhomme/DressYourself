package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.InputStream;

import android.content.Context;
import android.util.AndroidException;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

public class ClothesManager extends AndroidFileManager {
  public final static String STORAGE_PATH = "";

  /**
   * 
   * @param imageRelativePath
   */
  public static void storeClotheImage(Context context, String imageRelativePath,
      InputStream imageStream) {
    try {
      writeFileToExternalStorage(context, imageRelativePath, imageStream);
    } catch (AndroidException e) {
      throw new DressyourselfRuntimeException(e);
    }
  }

  /**
   * 
   * @param imageRelativePath
   */
  public static File loadClotheImage(Context context, String imageRelativePath) {
    // TODO : to verify
    return new File(STORAGE_PATH + imageRelativePath);
  }

  /**
   * 
   * @param imageRelativePath
   */
  public static void removeClotheImage(Context context, String imageRelativePath) {
    // TODO implement
  }
}
