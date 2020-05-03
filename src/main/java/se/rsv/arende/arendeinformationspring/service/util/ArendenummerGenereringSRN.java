package se.rsv.arende.arendeinformationspring.service.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;


/*
 * "intruktioner" för hur ett ärendenummer ska se ut om den tillhör SRN.
 * Dvs. vilken prefix och hur många siffror som den ska ha.
 */
public class ArendenummerGenereringSRN extends ArendeNrGenerering {

	public ArendenummerGenereringSRN() {

	}

	@Value("${SRN.prefix}")
	String prefix;

	@Value("${SRN.divider}")
	String delare;

	@Value("${SRN.int}")
	int antalSiffror;

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
