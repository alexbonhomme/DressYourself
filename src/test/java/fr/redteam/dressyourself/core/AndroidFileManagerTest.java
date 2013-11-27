package fr.redteam.dressyourself.core;

import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
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
