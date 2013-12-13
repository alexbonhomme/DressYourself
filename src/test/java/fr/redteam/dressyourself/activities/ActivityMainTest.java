package fr.redteam.dressyourself.activities;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

public class ActivityMainTest {

  private ActivityMain activity;

  @Before
  public void setUp() throws Exception {
    activity = Robolectric.buildActivity(ActivityMain.class).create().get();
  }

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
