package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.InputStream;

import android.content.Context;
import fr.redteam.dressyourself.common.AndroidFileManager;
import fr.redteam.dressyourself.common.FileManager;

public class ClothesManager {

  private ClothesManager() {}

  /**
   * 
   * @param imageRelativePath
   */
  public static void storeClotheImage(Context context, String imageRelativePath,
      InputStream imageStream) {
    FileManager manager = new AndroidFileManager(context);
    manager.writeFileToStorage(imageRelativePath, imageStream);
  }

  /**
   * 
   * @param imageRelativePath
   */
  public static File loadClotheImage(Context context, String imageRelativePath) {
    FileManager manager = new AndroidFileManager(context);

    return manager.loadFileFromStorage(imageRelativePath);
  }

  /**
   * 
   * @param imageRelativePath
   */
  public static void removeClotheImage(Context context, String imageRelativePath) {
    FileManager manager = new AndroidFileManager(context);
    manager.deleteFileFromStorage(imageRelativePath);
  }
}
