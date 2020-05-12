package se.rsv.arende.arendeinformationspring.service.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


public abstract class ArendeNrGenerering {

	final String PROP_FILE="myndighet.properties";

	private String prefix;
	private String delare;
	private int antalSiffror;
	InputStream input;

	Properties prop = new Properties();

	public void loadProperties(){
		{
			try {
				input = getClass().getClassLoader().getResourceAsStream(PROP_FILE);
				prop.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public ArendeNrGenerering (){
		loadProperties();
	}
/*/
 * Metod för att skapa en String av slumpade nummer.
 */

	static String getNummerString(int n) {

		String NummerString = "0123456789";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate
			int index = (int) (NummerString.length() * Math.random());

			// add
			sb.append(NummerString.charAt(index));
		}

		return sb.toString();
	}
	

	
	public String arendenummerString(String prefix, String delare, int antalSiffror) {
		ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
		aLogger.skapaLogg();

		int siffrorIArende = antalSiffror;
		String arendePrefix = prefix;
		String arendedelare = delare;

		// String year = ;

		// arendePrefix.name + arendePrefix.int + arendePrefix.delare + arendePrefix.int
		String skapatArendenummer = arendePrefix + getNummerString(siffrorIArende) + arendedelare
				+ getNummerString(siffrorIArende);

		return skapatArendenummer;
	}

	/*
	 * Metod som sätter ihop prefix antal siffror och 'delare' 
	 */
	
	public String genereraArendenummer() {
		ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
		aLogger.skapaLogg();

		int siffrorIArende = antalSiffror;

		String arendePrefix = prefix;
		String arendedelare = delare;

		String arendeNummer = arendePrefix + getNummerString(siffrorIArende) + arendedelare
				+ getNummerString(siffrorIArende);

		return arendeNummer;

	}

	
	public String skapaArendeNr(String myndighet) throws IOException {
		// TODO Auto-generated method stub
		return genereraArendenummer();
	}

}
