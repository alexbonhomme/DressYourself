package main.java.fr.redteam.dressyourselftest.activitesTest;

import main.java.fr.redteam.dressyourself.activites.ActivityClotheDetail;
import android.test.ActivityInstrumentationTestCase2;

public class ActivityClotheDetailTest extends ActivityInstrumentationTestCase2<ActivityClotheDetail> {
	
	  private ActivityClotheDetail activityClotheDetail;

	  
	  public ActivityClotheDetailTest(Class<ActivityClotheDetail> activityClass)
	  {
	    super(activityClass);

	  }

	  public ActivityClotheDetailTest() {
	    super(ActivityClotheDetail.class);
	  }

	  protected void setUp() throws Exception 
	  {
		    super.setUp();

		    setActivityInitialTouchMode(false);
		    activityClotheDetail = getActivity();

	  }


}