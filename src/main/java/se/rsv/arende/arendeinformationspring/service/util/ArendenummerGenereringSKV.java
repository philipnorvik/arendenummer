package se.rsv.arende.arendeinformationspring.service.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/*
 * "intruktioner" för hur ett ärendenummer ska se ut om den tillhör SKV.
 * Dvs. vilken prefix och hur många siffror som den ska ha.
 */
public class ArendenummerGenereringSKV extends ArendeNrGenerering {


	private String prefix="";
	private String delare = "";
	private int antalSiffror;



	public ArendenummerGenereringSKV() {
		super();
		prefix = prop.getProperty("SKV.prefix");
		delare = prop.getProperty("SKV.delare");
		antalSiffror = Integer.parseInt(prop.getProperty("SKV.antalSiffror"));
	}

	@Override
	public String skapaArendeNr(String myndighet) throws IOException {
		ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
		aLogger.skapaLogg();
		String aNummer = "";

		if (myndighet.equalsIgnoreCase("SKV")) {
			aNummer = arendenummerString(prefix, delare, antalSiffror);
		}

		return aNummer;
	}
}
