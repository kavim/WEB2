package com.loscarpinchos.evertotqm.controller;

import com.loscarpinchos.evertotqm.model.Filial;
import com.loscarpinchos.evertotqm.model.Funcionario;
import com.loscarpinchos.evertotqm.model.FuncionarioForm;
import com.loscarpinchos.evertotqm.repository.FilialRepository;
import com.loscarpinchos.evertotqm.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FuncionarioController {
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    FilialRepository filialRepository;

    @RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
    public ModelAndView getFuncionarios() {
        ModelAndView mv = new ModelAndView("funcionarios");
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        mv.addObject("funcionarios", funcionarios);

        return mv;
    }

    @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
    public ModelAndView getFuncionario(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("funcionario");
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        mv.addObject("name", funcionario.get().getName());
        mv.addObject("email", funcionario.get().getEmail());
        mv.addObject("phone", funcionario.get().getPhone());
        mv.addObject("sector", funcionario.get().getSector());
        mv.addObject("filialName", funcionario.get().getFilial().getName());

        return mv;
    }

    @RequestMapping(value = "/funcionario/create", method = RequestMethod.GET)
    public ModelAndView funcionarioCreate() {
        ModelAndView mv = new ModelAndView("funcionario-create-form");
        List<Filial> filiais = filialRepository.findAll();
        mv.addObject("filiais", filiais);

        return mv;
    }

    @RequestMapping(value = "/funcionario/store", method = RequestMethod.POST)
    public String funcionarioStore(FuncionarioForm funcionarioForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "Verifique todos os campos obrigatorios");
            return "redirect:/funcionario/create";
        }

        Filial filial = filialRepository.findById(funcionarioForm.getFilial()).get();
        Funcionario funcionario = new Funcionario(funcionarioForm, filial);
        funcionarioRepository.save(funcionario);

        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "/funcionario/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editFuncionario(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("funcionario-update-form");

        List<Filial> filiais = filialRepository.findAll();
        mv.addObject("filiais", filiais);

        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        mv.addObject("id", funcionario.get().getId());
        mv.addObject("name", funcionario.get().getName());
        mv.addObject("email", funcionario.get().getEmail());
        mv.addObject("phone", funcionario.get().getPhone());
        mv.addObject("sector", funcionario.get().getSector());
        mv.addObject("filialId", funcionario.get().getFilial().getId());

        return mv;
    }

    @RequestMapping(value = "/funcionario/update", method = RequestMethod.POST)
    public String updateEmployee(Funcionario funcionarioForm, RedirectAttributes attributes) {

        Funcionario funcionarios = funcionarioRepository.findById(funcionarioForm.getId()).orElse(null);
        funcionarios.setName(funcionarioForm.getName());
        funcionarios.setEmail(funcionarioForm.getEmail());
        funcionarios.setPhone(funcionarioForm.getPhone());
        funcionarios.setSector(funcionarioForm.getSector());
        funcionarios.setFilial(funcionarioForm.getFilial());
        funcionarioRepository.save(funcionarios);

        attributes.addFlashAttribute("success", "Actualizazdo con éxito");
        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "/funcionario/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateFuncionario(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("funcionario-update-form");
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        modelAndView.addObject("name", funcionario.get().getName());
        modelAndView.addObject("email", funcionario.get().getEmail());
        modelAndView.addObject("phone", funcionario.get().getPhone());
        modelAndView.addObject("sector", funcionario.get().getSector());
        modelAndView.addObject("filial", funcionario.get().getFilial());

        return modelAndView;
    }

    @RequestMapping(value = "/funcionario/delete/{id}", method = RequestMethod.GET)
    public String deleteFilial(@PathVariable("id") int id, RedirectAttributes attributes) {
        funcionarioRepository.deleteById(id);
        attributes.addFlashAttribute("success", "Deletado con exito!");

        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "/funcionario/search", method = RequestMethod.POST)
    public ModelAndView getFuncionarioSearch(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("funcionarios");
        List<Funcionario> funcionarios = null;

//        assert search != null;
        if (search.isEmpty()) {
            funcionarios = funcionarioRepository.findAll();
            modelAndView.addObject("funcionarios", funcionarios);

            return modelAndView;
        }

        funcionarios = funcionarioRepository.findFuncionarioByNameContaining(search);

        modelAndView.addObject("funcionarios", funcionarios);
        return modelAndView;
    }
}
