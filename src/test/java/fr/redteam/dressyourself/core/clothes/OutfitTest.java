package fr.redteam.dressyourself.core.clothes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OutfitTest {
  private Outfit outfit;

  @Before
  public void setUp() throws Exception {
    outfit = new Outfit();
  }

  @Test
  public void testListClotheEmpty() {
    assertEquals(outfit.nbClothes(), 0);

  }

  @Test
  public void testOutfitHasClothe() {
    outfit.addClothe(new Clothe("toto"));
    outfit.addClothe(new Clothe("jojo"));
    assertEquals(outfit.nbClothes(), 2);
  }

  @Test
  public void testContainsMyClothe() {
    outfit.addClothe(new Clothe("tintin"));
    assertEquals(outfit.getClothes().get(outfit.nbClothes() - 1).getModel(), "tintin");
  }

  @Test
  public void testHasAllClothes() {
    outfit.addClothe(new Clothe("toto"));
    outfit.addClothe(new Clothe("jojo"));
    outfit.addClothe(new Clothe("tintin"));
    List<Clothe> myListOfClothes = outfit.getClothes();
    assertEquals(myListOfClothes.get(0).getModel(), "toto");
    assertEquals(myListOfClothes.get(1).getModel(), "jojo");
    assertEquals(myListOfClothes.get(2).getModel(), "tintin");
  }
}
