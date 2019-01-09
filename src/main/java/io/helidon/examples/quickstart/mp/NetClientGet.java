package io.helidon.examples.quickstart.mp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetClientGet {

public static String sonarGet(String args) {

	StringBuffer result = new StringBuffer();

	  try {

		URL url = new URL("https://private-850cff-helidonri.apiary-mock.com/sonar-techdebt");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

        String output;
        

		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
            result.append(output);
            //System.out.println(output);
        }

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

      }
      return result.toString();
    }

}