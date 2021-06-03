package cz.czechitas.java2webapps.lekce10.controller;

import cz.czechitas.java2webapps.lekce10.service.TridaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TridaController {

    private final TridaService tridaService;

    public TridaController(TridaService tridaService) {
        this.tridaService = tridaService;
    }

    @GetMapping("/")
    public ModelAndView seznamTrid(@PageableDefault(sort = {"nazev"}) Pageable pageable) {
        return new ModelAndView("seznam-trid")
                .addObject("seznamTrid", tridaService.listTrid(Pageable.unpaged()));
    }

    @GetMapping(path = "/", params = "id")
    public ModelAndView zobrazDetailTridy(@PageableDefault(sort = {"prijmeni, jmeno"}) short id) {
        ModelAndView result = new ModelAndView("detail-tridy");
        result.addObject("seznamStudentu", tridaService.listStudentuTridy(id, Pageable.unpaged()));
        result.addObject("trida", tridaService.findTridaById(id, Pageable.unpaged()));
        return result;
    }

    @GetMapping(path = "/student", params = "id")
    public ModelAndView zobrazDetailStudenta(@PageableDefault(sort = {"prijmeni, jmeno"}) Integer id) {
        ModelAndView result = new ModelAndView("detail-studenta");
        result.addObject("student", tridaService.findById(id, Pageable.unpaged()));
        result.addObject("seznamRodicu", tridaService.listRodicuStudenta(id, Pageable.unpaged()));
        return result;
    }
}
