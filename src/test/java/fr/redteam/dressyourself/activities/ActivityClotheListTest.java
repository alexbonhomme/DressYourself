package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.adapters.AdapterClothes;
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class ActivityClotheListTest {

	private ActivityClotheList activityClotheList;
	private DBHelper dbHelp;
	private List<Clothe> clotheListTest;
	private AdapterClothes adapter;

	@Before
	public void setUp() throws Exception {

		this.createClothes();
		this.activityClotheList = Robolectric.buildActivity(ActivityClotheList.class).create().visible().get();
		this.dbHelp = new DBHelper(Robolectric.getShadowApplication().getApplicationContext(), null);
		this.dbHelp.open();
		
	}

	public void createClothes() throws FileNotFoundException {

		this.clotheListTest = new ArrayList<Clothe>();
		List<String> weather = new ArrayList<String>();
		weather.add("Cloudy");
		weather.add("Rainy");
		Clothe myClotheTest = new Clothe("Clothe test");
		myClotheTest.setWeather(weather);
		myClotheTest.setBrand("Zara");
		myClotheTest.setColor("RED");
		myClotheTest.setType("pull");
		myClotheTest.setBodies("body");
		this.clotheListTest.add(myClotheTest);
		myClotheTest = new Clothe("Clothe test2");
		myClotheTest.setWeather(weather);
		myClotheTest.setBrand("Zaraa");
		myClotheTest.setColor("BLUE");
		myClotheTest.setType("jean");
		myClotheTest.setBodies("bottom");
		this.clotheListTest.add(myClotheTest);
		
		
	}
	
	@Test
	public void TestListIsGoodSize(){
		if (this.dbHelp.getListClothes().size() != 0){
			assertEquals(this.dbHelp.getListClothes().size(), this.activityClotheList.getList().size());
		}else{
			assertEquals(1, this.activityClotheList.getList().size());
		}
		
	}
	
	

	
}
