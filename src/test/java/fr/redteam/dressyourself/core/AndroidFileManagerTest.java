package fr.redteam.dressyourself.core;

import static org.junit.Assert.fail;

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
    AndroidFileManager.writeFileToExternalStorage(context, "test/image.png", imageStream);
  }

  @Test
  public void testLoadFileFromExternalStorage() {
    fail("Not yet implemented");
  }

  @Test
  public void testDeleteFileFromExternalStorage() {
    fail("Not yet implemented");
  }

}
