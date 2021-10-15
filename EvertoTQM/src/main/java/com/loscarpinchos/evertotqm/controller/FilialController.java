package com.loscarpinchos.evertotqm.controller;

import com.loscarpinchos.evertotqm.model.Filial;
import com.loscarpinchos.evertotqm.repository.FilialRepository;
import com.loscarpinchos.evertotqm.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class FilialController {
    @Autowired
    FilialRepository filialRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @RequestMapping(value = "/filiais", method = RequestMethod.GET)
    public ModelAndView getFiliais() {
        ModelAndView mv = new ModelAndView("filiais");
        List<Filial> filiais = filialRepository.findAll();
        mv.addObject("filiais", filiais);

        return mv;
    }

    @RequestMapping(value = "/filial/{id}", method = RequestMethod.GET)
    public ModelAndView getFilial(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("filial");
        Optional<Filial> filial = filialRepository.findById(id);
        mv.addObject("name", filial.get().getName());
        mv.addObject("email", filial.get().getEmail());
        mv.addObject("city", filial.get().getCity());
        mv.addObject("funcionarios", filial.get().getFuncionarios());

        return mv;
    }

    @RequestMapping(value = "/filial/create", method = RequestMethod.GET)
    public String filialCreate() {
        return "filial-create-form";
    }

    @RequestMapping(value = "/filial/store", method = RequestMethod.POST)
    public String filialStore(@Valid Filial filial, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "Verifique todos os campos obrigatorios");
            return "redirect:/filial/create";
        }
        filialRepository.save(filial);

        return "redirect:/filiais";
    }

    @RequestMapping(value = "/filial/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editFilial(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("filial-update-form");
        Optional<Filial> filial = filialRepository.findById(id);
        mv.addObject("id", filial.get().getId());
        mv.addObject("name", filial.get().getName());
        mv.addObject("email", filial.get().getEmail());
        mv.addObject("city", filial.get().getCity());

        return mv;
    }

    @RequestMapping(value = "/filial/update", method = RequestMethod.POST)
    public String updatePostagem(@Valid Filial requestfilial, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("msg", "Verifique todos os campos obrigatorios");
            return "redirect:/filial/create";
        }

        Filial filial = filialRepository.findById(requestfilial.getId()).orElse(null);

        if (filial == null) {
            attributes.addFlashAttribute("msg", "ERROR!");
            return "redirect:/filiais";
        }

        filial.setName(requestfilial.getName());
        filial.setEmail(requestfilial.getEmail());
        filial.setCity(requestfilial.getCity());
        filialRepository.save(filial);
        attributes.addFlashAttribute("success", "Atualizado!");

        return "redirect:/filiais";
    }

    @RequestMapping(value = "/filial/delete/{id}", method = RequestMethod.GET)
    public String deleteFilial(@PathVariable("id") int id, RedirectAttributes attributes) {

        filialRepository.deleteById(id);
        attributes.addFlashAttribute("success", "Deletado con exito!");

        return "redirect:/filiais";
    }

    @RequestMapping(value = "/filial/search", method = RequestMethod.POST)
    public ModelAndView getFilialSearch(@RequestParam String search, int tipopesquisa) {
        ModelAndView modelAndView = new ModelAndView("filiais");
        List<Filial> filials = null;

//        assert search != null;
        if (search.isEmpty() || tipopesquisa < 1) {
            filials = filialRepository.findAll();
            modelAndView.addObject("filiais", filials);

            return modelAndView;
        }

        if (tipopesquisa == 1) {
            filials = filialRepository.findFilialByNameContaining(search);
        } else {
            filials = filialRepository.findFilialByCityContaining(search);
        }

        modelAndView.addObject("filiais", filials);
        return modelAndView;
    }
}
