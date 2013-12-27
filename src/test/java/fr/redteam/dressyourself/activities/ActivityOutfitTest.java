package fr.redteam.dressyourself.activities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ActivityOutfitTest {

  ActivityOutfit activityOutfit;

  @Before
  public void setUp() {
    activityOutfit = Robolectric.buildActivity(ActivityOutfit.class).create().visible().get();
  }

  @Test
  public void testButtonGenerate() {

  }
}