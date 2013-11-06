package fr.redteam.dressyourself.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class StreamTools {

	static public String readStream(InputStream in) {
		String out = "";
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				out += line + "\n";
			}

		} catch (IOException e) {
			Log.w("Stream IO", e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Log.w("Stream IO", e.getMessage());
				}
			}
		}
	
		return out;
	}
	
	/**
	 * Source : http://stackoverflow.com/a/5445161
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
