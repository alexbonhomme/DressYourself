package fr.redteam.dressyourself.common.filemanager;

import java.io.File;
import java.io.InputStream;

/**
 * Implementation available :
 * 
 * AndroidFileManager(Context context)
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface FileManager {

  /**
   * Write an image file from the <code>imageStream</code> into the <code>imagePath</code>
   */
  void writeFileToStorage(String imagePath, InputStream imageStream);

  /**
   * Return a {@link File File} object from the <code>imagePath</code>
   */
  File loadFileFromStorage(String imagePath);

  /**
   * Removes the file which are in the <code>imagePath</code>
   */
  void deleteFileFromStorage(String imagePath);
}
