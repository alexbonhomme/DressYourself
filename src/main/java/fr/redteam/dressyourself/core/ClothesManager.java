package fr.redteam.dressyourself.core;

import java.io.File;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ClothesManager {

  public final static String STORAGE_PATH = "";

  /**
   * 
   * @param imageRelativePath
   */
  public static void storeClotheImage(String imageRelativePath) {
    // TODO : to implement
  }

  /**
   * 
   * @param imageRelativePath
   */
  public static File loadClotheImage(String imageRelativePath) {
    // TODO : to verify
    return new File(STORAGE_PATH + imageRelativePath);
  }
}

