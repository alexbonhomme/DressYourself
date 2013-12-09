package fr.redteam.dressyourself.common;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowEnvironment;

import android.content.Context;
import android.os.Environment;
import fr.redteam.dressyourself.R;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
@RunWith(RobolectricTestRunner.class)
public class AndroidFileManagerTest {

  private Context context;
  private InputStream imageStream;

  @Before
  public void setUp() throws Exception {
    context = Robolectric.getShadowApplication().getApplicationContext();
    ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);

    imageStream = context.getResources().openRawResource(R.raw.twotone_wrap_around_jacket);
  }

  @Test
  public void testWriteFileToExternalStorage() {
    FileManager manager = new AndroidFileManager(context);
    manager.writeFileToStorage("test/image.png", imageStream);

    File newFile = new File(context.getExternalFilesDir(null), "test/image.png");
    assertTrue(newFile.exists());
    assertTrue(newFile.isFile());
  }

  @Test
  public void testLoadFileFromExternalStorage() {
    // WARN This is the simplest solution to test the loading, but if the following code failed the
    // test failed too... This isn't a durable solution
    FileManager manager = new AndroidFileManager(context);
    manager.writeFileToStorage("test/image.png", imageStream);

    File loadFile = manager.loadFileFromStorage("test/image.png");
    assertTrue(loadFile.exists());
    assertTrue(loadFile.isFile());
  }

  @Test
  public void testDeleteFileFromExternalStorage() {
    // WARN This is the simplest solution to test the loading, but if the following code failed the
    // test failed too... This isn't a durable solution
    FileManager manager = new AndroidFileManager(context);
    manager.writeFileToStorage("test/image.png", imageStream);

    manager.deleteFileFromStorage("test/image.png");

    File deleteFile = new File(context.getExternalFilesDir(null), "test/image.png");
    assertTrue(!deleteFile.exists());
  }

}
