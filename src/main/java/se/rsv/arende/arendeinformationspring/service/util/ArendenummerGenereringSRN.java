package se.rsv.arende.arendeinformationspring.controller;

import java.io.IOException;

public class ArendenummerGenereringSRN extends ArendeNrGenerering implements IArendeNrGenerering {

    public ArendenummerGenereringSRN() {

    }

    private String arendeNummer;



    @Override
    public String skapaArendeNr(String myndighet) throws IOException {
        ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
        aLogger.skapaLogg();
        String aNummer = "";

        if (myndighet.equalsIgnoreCase("SRN")){

            //skapatArendenummer;
            //arendenummerString;

            String prefix = prop.getProperty("SRN.prefix");
            String delare = prop.getProperty("SRN.divider");
            String antalSiffror = prop.getProperty("SRN.int");

            aNummer = arendenummerString(prefix,delare,antalSiffror);
        }


        return aNummer;
    }
}
