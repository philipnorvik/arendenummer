package se.rsv.arende.arendeinformationspring.service.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Logger för att se vad som händer via serverloggen.
 */
public class ArendenummertjanstenLogger {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void skapaLogg(){
        LOGGER.log(Level.INFO, "Loggen påbörjas");
    }
}
