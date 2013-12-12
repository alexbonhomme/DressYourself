package fr.redteam.dressyourself.core;

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
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
@RunWith(RobolectricTestRunner.class)
public class ClothesManagerTest {

  private Context context;
  private InputStream imageStream;

  @Before
  public void setUp() throws Exception {
    context = Robolectric.getShadowApplication().getApplicationContext();
    ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);

    imageStream = context.getResources().openRawResource(R.raw.twotone_wrap_around_jacket);
  }

  @Test
  public void testStoreClotheImage() {
    ClothesManager manager = new ClothesManager(new AndroidFileManager(context));
    manager.storeClotheImage("test/image.png", imageStream);

    File newFile = new File(context.getExternalFilesDir(null), "test/image.png");
    assertTrue(newFile.exists());
    assertTrue(newFile.isFile());
  }

  @Test
  public void testLoadClotheImage() {
    // WARN This is the simplest solution to test the loading, but if the following code failed the
    // test failed too... This isn't a durable solution
    ClothesManager manager = new ClothesManager(new AndroidFileManager(context));
    manager.storeClotheImage("test/image.png", imageStream);

    File loadFile = manager.loadClotheImage("test/image.png");
    assertTrue(loadFile.exists());
    assertTrue(loadFile.isFile());
  }

  @Test
  public void testRemoveClotheImage() {
    // WARN This is the simplest solution to test the loading, but if the following code failed the
    // test failed too... This isn't a durable solution
    ClothesManager manager = new ClothesManager(new AndroidFileManager(context));
    manager.storeClotheImage("test/image.png", imageStream);

    manager.removeClotheImage("test/image.png");
    File deleteFile = new File(context.getExternalFilesDir(null), "test/image.png");
    assertTrue(!deleteFile.exists());
  }

}
