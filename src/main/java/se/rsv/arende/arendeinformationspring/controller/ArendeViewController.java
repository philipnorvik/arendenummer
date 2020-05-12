package se.rsv.arende.arendeinformationspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import se.rsv.arende.arendeinformationspring.model.Arende;
import se.rsv.arende.arendeinformationspring.service.ArendeNrService;

@Controller
public class ArendeViewController {

    @Autowired
    ArendeNrService arendeNrService;

    @RequestMapping("/register")
    public String getRegister() {
        return "register";
    }

    @RequestMapping("/arendenummer/{arendenummer}")
    public ModelAndView getArendenummer(@PathVariable("arendenummer") String arendenummer) {
        Arende arende = arendeNrService.getArende(arendenummer);
        ModelAndView modelAndView = new ModelAndView("arendenummer");
        modelAndView.addObject("arende", arende);
        return modelAndView;
    }
}
