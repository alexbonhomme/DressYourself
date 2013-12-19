package fr.redteam.dressyourself.core.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.common.database.IntDBHelper;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class OutfitDeciderTest {
  private OutfitDecider outfitDecider;
  private List<Clothe> listClothe;
  private List<Clothe> listClotheWeather;
  private List<Clothe> listTop = new ArrayList<Clothe>();
  private List<Clothe> listBottom = new ArrayList<Clothe>();
  private List<Clothe> listFeet = new ArrayList<Clothe>();
  private IntDBHelper db;

  @Before
  public void setUp() throws Exception {
    listClothe = new ArrayList<Clothe>();
    listClotheWeather = new ArrayList<Clothe>();

    Clothe c1 = new Clothe();
    c1.setModel("TopSummer");
    c1.setWeather(new ArrayList<String>((Arrays.asList("HOT"))));
    listClothe.add(c1);
    listTop.add(c1);

    Clothe c2 = new Clothe();
    c2.setModel("BotWinter");
    c2.setWeather(new ArrayList<String>((Arrays.asList("COLD"))));
    listClothe.add(c2);
    listBottom.add(c2);

    Clothe c3 = new Clothe();
    c3.setModel("TopSpring");
    c3.setWeather(new ArrayList<String>((Arrays.asList("HOT", "COLD"))));
    listClothe.add(c3);
    listTop.add(c3);

    Clothe c4 = new Clothe();
    c4.setModel("Bot1");
    c4.setWeather(new ArrayList<String>());
    listClothe.add(c4);
    listBottom.add(c4);

    Clothe c5 = new Clothe();
    c5.setModel("Feet1");
    c5.setWeather(new ArrayList<String>());
    listClothe.add(c5);
    listFeet.add(c5);

    Clothe c6 = new Clothe();
    c6.setModel("Feet2");
    c6.setWeather(new ArrayList<String>());
    listClothe.add(c6);
    listFeet.add(c6);

    db = mock(DBHelper.class);
    when(db.getListTop()).thenReturn(listTop);
    when(db.getListBottom()).thenReturn(listBottom);
    when(db.getListFeet()).thenReturn(listFeet);
    outfitDecider = new OutfitDecider(db);
  }

  @Test
  public void testDecideTop() {
    Clothe oldTop = outfitDecider.decide(Bodypart.TOP);
    Clothe newTop = outfitDecider.decide(Bodypart.TOP);
    assertTrue(!oldTop.getModel().equals(newTop.getModel()));
  }

  @Test
  public void testDecideBottom() {
    Clothe oldBottom = outfitDecider.decide(Bodypart.BOTTOM);
    Clothe newBottom = outfitDecider.decide(Bodypart.BOTTOM);
    assertTrue(!oldBottom.getModel().equals(newBottom.getModel()));
  }

  @Test
  public void testDecideFeet() {
    Clothe oldFeet = outfitDecider.decide(Bodypart.SHOES);
    Clothe newFeet = outfitDecider.decide(Bodypart.SHOES);
    assertTrue(!oldFeet.getModel().equals(newFeet.getModel()));
  }

  @Test
  public void testBuildListWeather() {
    outfitDecider.setWeather("HOT");
    listClotheWeather = outfitDecider.buildListWeather(listClothe);
    assertEquals("TopSummer", listClotheWeather.get(0).getModel());
    assertEquals("TopSpring", listClotheWeather.get(1).getModel());
    assertEquals(2, listClotheWeather.size());
  }


}
