package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.mockito.Mockito.*;

import android.content.Context;
import fr.redteam.dressyourself.adapters.AdapterClothes;
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class ActivityClotheListTest {

	private ActivityClotheList activityClotheList;
	private DBHelper dbHelp;
	private List<Clothe> clotheListTest;
	private AdapterClothes adapter;
	private Context context;

	@Before
	public void setUp() throws Exception {

		this.createClothes();
		this.activityClotheList = Robolectric.buildActivity(ActivityClotheList.class).create().visible().get();
		context = Robolectric.getShadowApplication().getApplicationContext();
	    this.dbHelp = mock(DBHelper.class);
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
		myClotheTest = new Clothe("Clothe test3");
		myClotheTest.setWeather(weather);
		myClotheTest.setBrand("Zaraaa");
		myClotheTest.setColor("BLUE");
		myClotheTest.setType("jean");
		myClotheTest.setBodies("bottom");
		this.clotheListTest.add(myClotheTest);
		
		
	}
	
	@Test
	public void TestListIsGoodSize(){
		
		when(this.dbHelp.getListClothes()).thenReturn(clotheListTest);
		this.activityClotheList.setClotheList(this.dbHelp);
		
		assertEquals(this.dbHelp.getListClothes().size(), this.activityClotheList.getList().size());
	}
	
	@Test
	public void TestListIsNull(){
		when(this.dbHelp.getListClothes()).thenReturn(new ArrayList<Clothe>());
		assertEquals(1, this.activityClotheList.getList().size());
	}

	
}
