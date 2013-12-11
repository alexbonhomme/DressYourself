package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertTrue;

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
import android.widget.EditText;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;

@RunWith(RobolectricTestRunner.class)
public class ActivityOutfitMailTest {
  private ActivityOutfitMail activityOutfitMail;
  private Outfit outfit;

  @Before
  public void setUp() throws Exception {

    this.createOutfit();
    Intent intent = this.getIntent();
    this.activityOutfitMail =
        Robolectric.buildActivity(ActivityOutfitMail.class).withIntent(intent).create().visible()
            .get();
  }

  /*
   * Made a the clothe
   */
  public void createOutfit() throws FileNotFoundException {

    List<String> myWeather = new ArrayList<String>();
    myWeather.add("Cloudy");
    Clothe part1 = new Clothe("Vet1");
    part1.setBrand("brice");
    part1.setWeather(myWeather);
    part1.setColor("blue");
    part1.setBodies("top");
    part1.setType("sweat");

    Clothe part2 = new Clothe("Vet2");
    part2.setWeather(myWeather);
    part2.setType("jeans");
    part2.setBrand("brice");
    part2.setBodies("bottom");
    part2.setColor("Yellow");
    Outfit myOutfit = new Outfit();
    myOutfit.addClothe(part1);
    myOutfit.addClothe(part2);
    this.outfit = myOutfit;
  }

  public Intent getIntent() throws FileNotFoundException {
    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityOutfitMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("outfit", this.outfit);
    intent.putExtras(bundle);
    return intent;
  }

  /* check if the clothe's brand value is properly loaded into brand EditText */
  @Test
  public void testDestinataireInitialValue() {
    EditText textDestinataire =
        (EditText) activityOutfitMail.findViewById(R.id.editDestinataireOutfit);
    assertTrue(textDestinataire.getText().equals(""));
  }

  /* check if the modifications on model have been saved */
  @Test
  public void testDestinataireChangeValue() {
    EditText textDestinataire =
        (EditText) activityOutfitMail.findViewById(R.id.editDestinataireOutfit);
    textDestinataire.setText("toto@free.fr");
    assertTrue(textDestinataire.getText().equals("toto@free.fr"));
  }

  /* check if the clothe's brand value is properly loaded into brand EditText */
  @Test
  public void testContenueInitialValue() {
    EditText textContenu = (EditText) activityOutfitMail.findViewById(R.id.editMailOutfit);
    assertTrue(textContenu.getText().equals(""));
  }

  /* check if the modifications on model have been saved */
  @Test
  public void testContenuChangeValue() {
    EditText textContenu = (EditText) activityOutfitMail.findViewById(R.id.editMailOutfit);
    textContenu.setText("Blabla");
    assertTrue(textContenu.getText().equals("Blabla"));
  }
}
