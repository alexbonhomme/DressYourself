package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
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
public class ActivityMailTest {
	private ActivityMail activityMail;
	private Clothe clothe;
	private static final int REQUEST_CODE_MAILINTENT = 1234;

	@Before
	public void setUp() throws Exception {
		this.clothe();
		Intent intent = this.createIntent();
		this.activityMail = Robolectric.buildActivity(ActivityMail.class)
				.withIntent(intent).create().visible().get();
	}

	/*
	 * Made a the clothe
	 */
	public void clothe() throws FileNotFoundException {

		List<String> weather = new ArrayList<String>();
		weather.add("Rainy");
		Clothe myClothe = new Clothe("the pull of your life");
		myClothe.setBodies("top");
		myClothe.setWeather(weather);
		myClothe.setColor("Black");
		myClothe.setBrand("the Brand");
		myClothe.setType("pull");
		this.clothe = myClothe;
	}

	public Outfit generateOutfit() throws FileNotFoundException {
		Clothe clothe = new Clothe("the pull of your life");
		Clothe clothe2 = new Clothe("the t-shirt of your life");
		Outfit myOutfit = new Outfit();
		myOutfit.addClothe(clothe2);
		myOutfit.addClothe(clothe);
		return myOutfit;
	}

	/*
	 * Create an intent with a clothe
	 */
	public Intent createIntent() throws FileNotFoundException {
		Intent intent = new Intent(Robolectric.getShadowApplication()
				.getApplicationContext(), ActivityMail.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("clothe", this.clothe);
		intent.putExtras(bundle);
		return intent;
	}

	/*
	 * Create an intent with an outfit
	 */
	public Intent createIntentWithOutfit() throws FileNotFoundException {
		Intent intent = new Intent(Robolectric.getShadowApplication()
				.getApplicationContext(), ActivityMail.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("outfit", this.generateOutfit());
		intent.putExtras(bundle);
		return intent;
	}

	/* check if the clothe's brand value is properly loaded into brand EditText */
	@Test
	public void testReceveirInitialValue() {
		EditText textReceveir = (EditText) activityMail
				.findViewById(R.id.editReceiver);
		assertTrue(textReceveir.getText().equals(""));
	}

	/* check if the modifications on model have been saved */
	@Test
	public void testReceveirChangeValue() {
		EditText textReceveir = (EditText) activityMail
				.findViewById(R.id.editReceiver);
		textReceveir.setText("tom@gmail.com");
		assertTrue(textReceveir.getText().equals("tom@gmail.com"));
	}

	/* check if the clothe's brand value is properly loaded into brand EditText */
	@Test
	public void testContenueInitialValue() {
		EditText textBody = (EditText) activityMail.findViewById(R.id.editMail);
		assertTrue(textBody.getText().equals(""));
	}

	/* check if the modifications on model have been saved */
	@Test
	public void testContenuChangeValue() {
		EditText textBody = (EditText) activityMail.findViewById(R.id.editMail);
		textBody.setText("content");
		assertTrue(textBody.getText().equals("content"));
	}

	/*
	 * Test onActivityResult with the bad requestcode
	 * 
	 * @Test public void testOnActivityResultBad() {
	 * this.activityMail.onActivityResult(123455, 1, null);
	 * this.activityMail.creationMail();
	 * assertTrue(this.activityMail.isFinishing() == false); }
	 */

	/*
	 * Test onActivityResult with the good requestcode
	 */
	@Test
	public void testOnActivityResultGood() {
		this.activityMail.onActivityResult(REQUEST_CODE_MAILINTENT, 1, null);
		assertTrue(this.activityMail.isDestroyed());
	}

	/*
	 * Test creationMail with a clothe
	 */
	@Test
	public void testCreationMailClothe() throws IllegalArgumentException,
			IllegalAccessException {
		this.activityMail.creationMail();
		Class<?> secretClass = this.activityMail.getClass();
		Field fields[] = secretClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("mail")) {
				field.setAccessible(true);
				assertTrue(field
						.get(this.activityMail)
						.toString()
						.startsWith(
								"fr.redteam.dressyourself.plugins.mail.MailClothePlugin"));
			}
		}
	}

	/*
	 * Test onActivityResult with the bad requestcode
	 */
	@Test
	public void testCreationMailOutfit() throws FileNotFoundException,
			IllegalArgumentException, IllegalAccessException {
		Intent intent = this.createIntentWithOutfit();
		this.activityMail = Robolectric.buildActivity(ActivityMail.class)
				.withIntent(intent).create().visible().get();
		this.activityMail.creationMail();
		Class<?> secretClass = this.activityMail.getClass();
		Field fields[] = secretClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("mail")) {
				field.setAccessible(true);
				assertTrue(field
						.get(this.activityMail)
						.toString()
						.startsWith(
								"fr.redteam.dressyourself.plugins.mail.MailOutfitPlugin"));
			}
		}
	}
}
