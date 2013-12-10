package fr.redteam.dressyourself.common.filemanager;

import java.io.File;
import java.io.InputStream;

/**
 * XXX need to be commented !
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface FileManager {

  void writeFileToStorage(String imagePath, InputStream imageStream);

  File loadFileFromStorage(String imagePath);

  void deleteFileFromStorage(String imagePath);
}
