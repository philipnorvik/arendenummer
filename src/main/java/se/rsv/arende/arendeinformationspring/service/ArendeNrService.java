package se.rsv.arende.arendeinformationspring.controller;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.rsv.arende.arendeinformationspring.exception.FelMyndighetException;
import se.rsv.arende.arendeinformationspring.model.Arende;
import java.io.IOException;
import java.util.List;

@Stateless
public class ArendeNrService {
    //private static final String PERSISTENCE_UNIT_NAME = "arenden";
    //private static EntityManagerFactory factory;
    //ArendeNrTjansten

  @PersistenceContext(unitName = "ArendeNrTjansten") //, type = PersistenceContextType.EXTENDED)
   protected EntityManager em;

   String arendenummer = null;
   String inparameterMyndighet = null;
   String felmeddelande = "Felaktig inmatning";

    public ArendeNrService() {
    }

   //  @GenerateSKV
   // ArendeNrGenerering arendenummerGenerering


    //  @GenerateSRN
    // ArendeNrGenerering arendenummerGenerering


    public String valAvMyndighet(String inparameterMyndighet) throws FelMyndighetException, IOException {

        if(inparameterMyndighet.equalsIgnoreCase("SKV")){
            IArendeNrGenerering arendenummerGenerering = new ArendenummerGenereringSKV();
            arendenummer = arendenummerGenerering.skapaArendeNr(inparameterMyndighet);

        }
        else if (inparameterMyndighet.equalsIgnoreCase("SRN")){
            IArendeNrGenerering ArendenummerGenereringSRN = new ArendenummerGenereringSRN();
            arendenummer = ArendenummerGenereringSRN.skapaArendeNr(inparameterMyndighet);
        }
        else {
            throw new FelMyndighetException(new Exception(felmeddelande));
        }

        this.inparameterMyndighet = inparameterMyndighet;
        return arendenummer;
    }

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

    public void getArende() {
        Query q = em.createQuery("select a from Arende a");
        List<Arende> arendeList = q.getResultList();
        for (Arende arende : arendeList) {
            System.out.println(arende);
        }
        System.out.println("Size: " + arendeList.size());
    }

    /*
    @Resource
    UserTransaction ut;
    @PersistenceContext
    EntityManager entityManager;
    ...
            try {
        ut.begin();

        Employee employee = new Employee();
        employee.setEmpNo(empId);
        employee.setEname(name);
        employee.setSal(sal);

        entityManager.persist(employee);
        ut.commit();

        this.getServletContext().getRequestDispatcher(
                "/jsp/success.jsp").forward(request, response);
    }
    catch(Exception e) {
    ...
    }
    */



}
