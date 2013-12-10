package fr.redteam.dressyourself.core;

import java.io.File;
import java.io.InputStream;

import fr.redteam.dressyourself.common.filemanager.FileManager;

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
}
