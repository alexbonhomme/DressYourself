package main.java.fr.redteam.dressyourself.activities;

import main.java.fr.redteam.dressyourself.activities.ActivityMain;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

public class ActivityMainTest extends ActivityInstrumentationTestCase2<ActivityMain> {

  private ActivityMain mActivity;
  private ImageView imgView;

  public ActivityMainTest(Class<ActivityMain> activityClass) {
    super(activityClass);
  }

  public ActivityMainTest() {
    super(ActivityMain.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    setActivityInitialTouchMode(false);
    mActivity = getActivity();
    // Recup. du logo par exemple
    imgView =
        (ImageView) mActivity.findViewById(main.java.fr.redteam.dressyourself.R.id.imgLogoMain);

  }

  /* Exemple de pré-conditions à check concernant le setUp fait précédemment */
  public void testPreConditions() {
    // Vous mettez par exemple si c'est une list : list.size == taille_souhaitée
    assertTrue(imgView != null);
  }

  // Test 1 - Voir lien plus haut pour exemple de test
  public void testImgUI() {
    assertEquals(true, true);
  }
}
