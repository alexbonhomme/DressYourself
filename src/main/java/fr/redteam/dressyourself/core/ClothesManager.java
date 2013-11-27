package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.InputStream;

import android.content.Context;

public class ClothesManager extends AndroidFileManager {
  public final static String STORAGE_PATH = "";

  /**
   * 
   * @param imageRelativePath
   */
  public static void storeClotheImage(Context context, String imageRelativePath,
      InputStream imageStream) {
      writeFileToExternalStorage(context, imageRelativePath, imageStream);

  }

  /**
   * 
   * @param imageRelativePath
   */
  public static File loadClotheImage(Context context, String imageRelativePath) {
    return loadFileFromExternalStorage(context, imageRelativePath);
  }

  /**
   * 
   * @param imageRelativePath
   */
  public static void removeClotheImage(Context context, String imageRelativePath) {
    // TODO implement
  }
}
