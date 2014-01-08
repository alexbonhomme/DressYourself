package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import fr.redteam.dressyourself.common.filemanager.FileManager;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ClothesManager {

  private final FileManager manager;

  public ClothesManager(FileManager manager) {
    this.manager = manager;
  }

  /**
   * 
   * @param imageRelativePath
   */
  public void storeClotheImage(String imageRelativePath, InputStream imageStream) {
    manager.writeFileToStorage(imageRelativePath, imageStream);
  }

  /**
   * 
   * @param imageRelativePath
   */
  public File loadClotheImage(String imageRelativePath) {
    return manager.loadFileFromStorage(imageRelativePath);
  }

  /**
   * 
   * @param imageRelativePath
   */
  public void removeClotheImage(String imageRelativePath) {
    manager.deleteFileFromStorage(imageRelativePath);
  }

  /**
   * 
   * @author burillon
   * @author boens
   * @return a Bitmap object representing the clothe's image
   */
  public Bitmap getClotheBitmapImage(Clothe clothe) {
    File imageFile = this.loadClotheImage(clothe.getImageRelativePath());
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
  }
}
