package se.rsv.arende.arendeinformationspring.service.util;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
/*
 * "intruktioner" för hur ett ärendenummer ska se ut om den tillhör SKV.
 * Dvs. vilken prefix och hur många siffror som den ska ha.
 */
public class ArendenummerGenereringSKV extends ArendeNrGenerering {

	@Value("${SKV.prefix}")
	String prefix;

	@Value("${SKV.divider}")
	String delare;

	@Value("${SKV.int}")
	int antalSiffror;

	public ArendenummerGenereringSKV() {

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
