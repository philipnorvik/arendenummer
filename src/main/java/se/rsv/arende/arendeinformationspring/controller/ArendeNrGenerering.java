package se.rsv.arende.arendeinformationspring.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ArendeNrGenerering {
	
	Properties prop = new Properties();
	   //final String PROP_FILE="myndighet.properties";

	   InputStream input;
	    {
	        try {
	            input = new FileInputStream("classpath:main/resources/myndighet.properties");
	            prop.load(input);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }



	   static String getNummerString(int n){

	        String NummerString = "0123456789";
	        StringBuilder sb = new StringBuilder(n);

	        for (int i = 0; i < n; i++) {

	            // generate
	            int index
	                    = (int)(NummerString.length()
	                    * Math.random());

	            //add
	            sb.append(NummerString
	                    .charAt(index));
	        }

	        return sb.toString();
	   }

	    private String prefix = prop.getProperty("");
	    private String delare = prop.getProperty("");
	    private String antalSiffror = prop.getProperty("");

	    public String arendenummerString (String prefix, String delare, String antalSiffror) throws IOException {
	        ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
	        aLogger.skapaLogg();


	        int siffrorIArende = Integer.parseInt(antalSiffror);;
	        String arendePrefix = prop.getProperty(prefix);
	        String arendedelare = prop.getProperty(delare);

	        //String year = ;

	        //arendePrefix.name + arendePrefix.int + arendePrefix.delare + arendePrefix.int
	        String skapatArendenummer = arendePrefix + getNummerString(siffrorIArende)
	                + arendedelare + getNummerString(siffrorIArende);

	        return skapatArendenummer;
	    }

	    public String genereraArendenummer() throws IOException {
	        ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
	        aLogger.skapaLogg();


	        int siffrorIArende = Integer.parseInt(antalSiffror);

	        String arendePrefix = prop.getProperty(prefix);
	        String arendedelare = prop.getProperty(delare);


	       String arendeNummer = arendePrefix + getNummerString(siffrorIArende)
	                + arendedelare + getNummerString(siffrorIArende);

	        return arendeNummer;

	    }

}
