package se.rsv.arende.arendeinformationspring.controller;

import java.io.IOException;

public class ArendenummerGenereringSKV extends ArendeNrGenerering implements IArendeNrGenerering {

    public ArendenummerGenereringSKV() {

    }




    @Override
    public String skapaArendeNr(String myndighet) throws IOException {
        ArendenummertjanstenLogger aLogger = new ArendenummertjanstenLogger();
        aLogger.skapaLogg();
        String aNummer = "";

        if (myndighet.equalsIgnoreCase("SKV")){


            String prefix = prop.getProperty("SKV.prefix");
            String delare = prop.getProperty("SKV.divider");
            String antalSiffror = prop.getProperty("SKV.int");

            aNummer = arendenummerString(prefix,delare,antalSiffror);
        }


        return aNummer;
    }
}

