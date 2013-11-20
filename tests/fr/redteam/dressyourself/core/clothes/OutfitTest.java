package fr.redteam.dressyourself.core.clothes;

import static org.junit.Assert.assertEquals;

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

}
