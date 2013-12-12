package fr.redteam.dressyourself.core.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.redteam.dressyourself.core.clothes.Clothe;

public class OutfitDeciderTest {
  private OutfitDecider outfitDecider;
  private List<Clothe> listClothe;
  private List<Clothe> listClotheWeather;

  @Before
  public void setUp() throws Exception {
    outfitDecider = new OutfitDecider();

    listClothe = new ArrayList<Clothe>();
    listClotheWeather = new ArrayList<Clothe>();

    Clothe c1 = new Clothe();
    c1.setModel("TopSummer");
    c1.setWeather(new ArrayList<String>((Arrays.asList("HOT"))));
    c1.setBodies("TOP");
    listClothe.add(c1);

    Clothe c2 = new Clothe();
    c2.setModel("BotWinter");
    c2.setWeather(new ArrayList<String>((Arrays.asList("COLD"))));
    c2.setBodies("BOTTOM");
    listClothe.add(c2);

    Clothe c3 = new Clothe();
    c3.setModel("TopSping");
    c3.setWeather(new ArrayList<String>((Arrays.asList("HOT", "COLD"))));
    c3.setBodies("TOP");
    listClothe.add(c3);

    Clothe c4 = new Clothe();
    c4.setModel("Bot1");
    c4.setWeather(new ArrayList<String>());
    c4.setBodies("BOTTOM");
    listClothe.add(c4);

    Clothe c5 = new Clothe();
    c5.setModel("Feet1");
    c5.setWeather(new ArrayList<String>());
    c5.setBodies("FEET");
    listClothe.add(c5);

    Clothe c6 = new Clothe();
    c6.setModel("Feet2");
    c6.setWeather(new ArrayList<String>());
    c6.setBodies("FEET");
    listClothe.add(c6);
  }

  @Test
  public void testDecideTop() {
    Clothe oldTop = outfitDecider.decideTop(listClothe);
    Clothe newTop = outfitDecider.decideTop(listClothe);
    assertNotEquals(oldTop.getModel(), newTop.getModel());
  }

  @Test
  public void testDecideBottom() {
    Clothe oldBottom = outfitDecider.decideBottom(listClothe);
    Clothe newBottom = outfitDecider.decideBottom(listClothe);
    assertNotEquals(oldBottom.getModel(), newBottom.getModel());
  }

  @Test
  public void testDecideFeet() {
    Clothe oldFeet = outfitDecider.decideFeet(listClothe);
    Clothe newFeet = outfitDecider.decideFeet(listClothe);
    assertNotEquals(oldFeet.getModel(), newFeet.getModel());
  }

  @Test
  public void testBuildListWeather() {
    outfitDecider.setWeather("HOT");
    listClotheWeather = outfitDecider.buildListWeather(listClothe);
    assertEquals("TopSummer", listClotheWeather.get(0).getModel());
    assertEquals("TopSping", listClotheWeather.get(1).getModel());
    assertEquals(2, listClotheWeather.size());
  }

}
