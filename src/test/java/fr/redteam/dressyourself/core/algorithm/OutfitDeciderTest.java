package fr.redteam.dressyourself.core.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.redteam.dressyourself.core.clothes.Clothe;

public class OutfitDeciderTest {
  private OutfitDecider outfitDecider;

  @Before
  public void setUp() throws Exception {
    outfitDecider = new OutfitDecider();
  }

  @Test
  public void buildListWeather() {
    List<Clothe> listClothe = new ArrayList<Clothe>();
    List<Clothe> listClotheWeather = new ArrayList<Clothe>();

    Clothe c1 = new Clothe();
    c1.setModel("vetementEte");
    c1.setWeather(new ArrayList<String>((Arrays.asList("HOT"))));
    listClothe.add(c1);

    Clothe c2 = new Clothe();
    c2.setModel("vetementHiver");
    c2.setWeather(new ArrayList<String>((Arrays.asList("COLD"))));
    listClothe.add(c2);

    Clothe c3 = new Clothe();
    c3.setModel("vetementPrintemps");
    c3.setWeather(new ArrayList<String>((Arrays.asList("HOT", "COLD"))));
    listClothe.add(c3);

    outfitDecider.setWeather("HOT");
    listClotheWeather = outfitDecider.buildListWeather(listClothe);
    assertEquals("vetementEte", listClotheWeather.get(0).getModel());
    assertEquals("vetementPrintemps", listClotheWeather.get(1).getModel());
    assertEquals(2, listClotheWeather.size());
  }

}
