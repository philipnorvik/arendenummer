package se.rsv.arende.arendeinformationspring.service.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;


/*
 * "intruktioner" för hur ett ärendenummer ska se ut om den tillhör SRN.
 * Dvs. vilken prefix och hur många siffror som den ska ha.
 */
public class ArendenummerGenereringSRN extends ArendeNrGenerering {


	private String prefix;
	private String delare ;
	private int antalSiffror;

	public ArendenummerGenereringSRN() {
		 super();
		 prefix = prop.getProperty("SRN.prefix");
		 delare = prop.getProperty("SRN.delare");
		 antalSiffror = Integer.parseInt(prop.getProperty("SRN.antalSiffror"));
	}


	@Override
	public String skapaArendeNr(String myndighet) throws IOException {
		ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
		aLogger.skapaLogg();
		String aNummer = "";

		if (myndighet.equalsIgnoreCase("SRN")) {

			// skapatArendenummer;
			// arendenummerString;
			aNummer = arendenummerString(prefix, delare, antalSiffror);
		}

		return aNummer;
	}
}
