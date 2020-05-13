package se.rsv.arende.arendeinformationspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.rsv.arende.arendeinformationspring.model.Arende;
import se.rsv.arende.arendeinformationspring.service.ArendeNrService;

@Controller
public class ArendeViewController {

    @Autowired
    ArendeNrService arendeNrService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }

    @RequestMapping(value = "/arendenummer/{arendenummer}" , method = RequestMethod.GET)
    public ModelAndView getArendenummer(@PathVariable("arendenummer") String arendenummer) {
        Arende arende = arendeNrService.getArende(arendenummer);
        ModelAndView modelAndView = new ModelAndView("arendenummer");
        modelAndView.addObject("arende", arende);
        return modelAndView;
    }
}
