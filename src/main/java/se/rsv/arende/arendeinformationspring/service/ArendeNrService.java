package se.rsv.arende.arendeinformationspring.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rsv.arende.arendeinformationspring.ArendeRepository;
import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;
import se.rsv.arende.arendeinformationspring.model.Arende;
import se.rsv.arende.arendeinformationspring.service.util.ArendeNrGenerering;
import se.rsv.arende.arendeinformationspring.service.util.ArendenummerGenereringSKV;
import se.rsv.arende.arendeinformationspring.service.util.ArendenummerGenereringSRN;
import se.rsv.arende.arendeinformationspring.service.util.ArendenummertjanstenLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ArendeNrService implements IArendeNrService{
    //private static final String PERSISTENCE_UNIT_NAME = "arenden";
    //private static EntityManagerFactory factory;
    

    @Autowired
    protected EntityManager em;

    @Autowired
    ArendeRepository arendeRepository;

       String felmeddelande = "Felaktig inmatning";

    public ArendeNrService() {
    }

    /*
     * Här görs val över vilken typ av ärendegenerering som ska användas. Den för SKV eller SRN.
     */
    public String valAvMyndighet(String inparameterMyndighet) throws FelMyndighetException, IOException {
        String arendenummer = null;

        if(inparameterMyndighet.equalsIgnoreCase("SKV")){
        	ArendeNrGenerering arendenummerGenerering = new ArendenummerGenereringSKV();
            arendenummer = arendenummerGenerering.skapaArendeNr(inparameterMyndighet);

        }
        else if (inparameterMyndighet.equalsIgnoreCase("SRN")){
        	ArendeNrGenerering ArendenummerGenereringSRN = new ArendenummerGenereringSRN();
            arendenummer = ArendenummerGenereringSRN.skapaArendeNr(inparameterMyndighet);
        }
        else {
            throw new FelMyndighetException(new Exception(felmeddelande));
        }

        return arendenummer;
    }

    /*
     * Här skapas ärendet i DB.
     */
    public Arende createArende(String inparameterMyndighet) throws IOException, FelMyndighetException {
        // create new arende
        Arende arende = new Arende();
        arende.setArendenummer(valAvMyndighet(inparameterMyndighet));
        arende.setMyndighet(inparameterMyndighet);
        arende.setDatum(new Date());
        arendeRepository.save(arende);
        return arende;
    }

    public List<Arende> getArenden() {
        List<Arende> arenden = new ArrayList<>();
        arendeRepository.findAll().forEach(arenden::add);
        return arenden;
    }
    public Arende getArende(String arendenummer) {
        return arendeRepository.findByArendenummer(arendenummer);
    }

}
