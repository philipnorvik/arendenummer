package se.rsv.arende.arendeinformationspring.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;
import se.rsv.arende.arendeinformationspring.model.Arende;
import se.rsv.arende.arendeinformationspring.service.util.ArendeNrGenerering;
import se.rsv.arende.arendeinformationspring.service.util.ArendenummerGenereringSKV;
import se.rsv.arende.arendeinformationspring.service.util.ArendenummerGenereringSRN;
import se.rsv.arende.arendeinformationspring.service.util.ArendenummertjanstenLogger;

import java.io.IOException;
import java.util.List;

@Service
public abstract class ArendeNrService implements IArendeNrService{
    //private static final String PERSISTENCE_UNIT_NAME = "arenden";
    //private static EntityManagerFactory factory;
    

   protected EntityManager em;

   String arendenummer = null;
   String inparameterMyndighet = null;
   String felmeddelande = "Felaktig inmatning";

    public ArendeNrService() {
    }

  

    /*
     * Här görs val över vilken typ av ärendegenerering som ska användas. Den för SKV eller SRN.
     */
    public String valAvMyndighet(String inparameterMyndighet) throws FelMyndighetException, IOException {

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

        this.inparameterMyndighet = inparameterMyndighet;
        return arendenummer;
    }

    /*
     * Här skapas ärendet i DB.
     */
    public void createArende(String inparameterMyndighet){

        // create new arende
        ///em.getTransaction().begin();
        Arende arende = new Arende();
        arende.setArendenummer(arendenummer);
        arende.setMyndighet(inparameterMyndighet);
        em.persist(arende);
        Query q = em.createQuery("select a from Arende a");
        List<Arende> arendeList = q.getResultList();
        for (Arende arendeQ : arendeList) {
            System.out.println(arendeQ);
        }

    }
/*
    public void getArende() {
        Query q = em.createQuery("select a from Arende a");
        List<Arende> arendeList = q.getResultList();
        for (Arende arende : arendeList) {
            System.out.println(arende);
        }
        System.out.println("Size: " + arendeList.size());
    }
*/
   


}
