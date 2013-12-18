package fr.redteam.dressyourself.common.filemanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;
import fr.redteam.dressyourself.exceptions.DressyourselfIOException;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;


/**
 * This class manage storage/reading/deleting of a file on an Android device
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class AndroidFileManager implements FileManager {

  private final Context context;

  public AndroidFileManager(Context context) {
    this.context = context;
  }

  /**
   * This method indicate if the we are (at least) allow to access to the memory in Read mode
   */
  private static boolean isExternalStorageReadAccess() {
    String state = Environment.getExternalStorageState();

    return Environment.MEDIA_MOUNTED.equals(state)
        || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
  }

  /**
   * This method indicate if the we are allow to access to the memory in Read AND Write mode
   */
  private static boolean isExternalStorageReadWriteAccess() {
    String state = Environment.getExternalStorageState();

    return Environment.MEDIA_MOUNTED.equals(state);
  }

  @Override
  public void writeFileToStorage(String imagePath, InputStream imageStream) {
    if (!isExternalStorageReadWriteAccess()) {
      throw new DressyourselfIOException("External storage need to be in READ/WRITE access");
    }

    if (imagePath.endsWith("/")) {
      throw new DressyourselfRuntimeException("imageRelitivePath should not be ended by a '/'");
    }

    writeFileToExternalStorage(context, imagePath, imageStream);
  }

  private void writeFileToExternalStorage(Context context, String imagePath, InputStream imageStream) {

    FileOutputStream outStream = null;
    try {
      // HACK: used a temporary File object to slip path and file name
      File fullPath = new File(imagePath);

      // relative directory from the root of the storage
      File dir = new File(context.getExternalFilesDir(null), fullPath.getParent());
      dir.mkdirs();

      File file = new File(dir, fullPath.getName());
      file.createNewFile();

      outStream = new FileOutputStream(file);
      byte[] data = new byte[imageStream.available()];

      imageStream.read(data);
      outStream.write(data);

    } catch (IOException e) {
      throw new DressyourselfIOException(e);
    } catch (NullPointerException e) {
      throw new DressyourselfRuntimeException(e);
    } finally {
      // always close stream
      try {
        imageStream.close();
        outStream.close();
      } catch (IOException e) {
        throw new DressyourselfIOException(e);
      } catch (NullPointerException e) {
        throw new DressyourselfRuntimeException(e);
      }
    }
  }

  @Override
  public File loadFileFromStorage(String imagePath) {
    if (!isExternalStorageReadAccess()) {
      throw new DressyourselfIOException("External storage need to be in READ (minimum) access");
    }

    return loadFileFromExternalStorage(context, imagePath);
  }

  private File loadFileFromExternalStorage(Context context, String imagePath) {

    File file = null;
    try {
      file = new File(context.getExternalFilesDir(null), imagePath);

      if (!file.exists()) {
        throw new DressyourselfIOException("File '"+ imagePath +"' not found.");
      }
    } catch(NullPointerException e) {
      throw new DressyourselfRuntimeException(e);
    }
    
    return file;
  }

  @Override
  public void deleteFileFromStorage(String imagePath) {
    if (!isExternalStorageReadWriteAccess()) {
      throw new DressyourselfIOException("External storage need to be in READ & WRITE access");
    }

    deleteFileFromExternalStorage(context, imagePath);
  }

  private void deleteFileFromExternalStorage(Context context, String imagePath) {

    try {
      File file = new File(context.getExternalFilesDir(null), imagePath);
      file.delete();
    } catch (NullPointerException e) {
      throw new DressyourselfRuntimeException(e);
    }
  }
}

