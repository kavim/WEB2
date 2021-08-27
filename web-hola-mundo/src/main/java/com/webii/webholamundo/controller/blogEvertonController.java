package com.webii.webholamundo.controller;

import com.webii.webholamundo.model.Postagens;
import com.webii.webholamundo.repository.web2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class blogEvertonController {
    @Autowired
    web2Repository blogEvertonRepository;

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

    @RequestMapping(value = "/postagens", method = RequestMethod.GET)
    public ModelAndView getPostagens() {
        ModelAndView mv = new ModelAndView("postagens");
        List<Postagens> postagens = blogEvertonRepository.findAll();
        mv.addObject("postagens", postagens);

        return mv;
    }

    @RequestMapping(value = "/postagem/{id}", method = RequestMethod.GET)
    public ModelAndView getPostagem(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("postagem");
        Optional<Postagens> postagem = blogEvertonRepository.findById(id);
        mv.addObject("autor", postagem.get().getAutor());
        mv.addObject("titulo", postagem.get().getTitulo());
        mv.addObject("data", postagem.get().getData());
        mv.addObject("texto", postagem.get().getTexto());

        return mv;
    }

    @RequestMapping(value = "/postagem/create", method = RequestMethod.GET)
    public String postagemCreate() {
        return "postagem-form";
    }

    @RequestMapping(value = "/postagem/create", method = RequestMethod.POST)
    public String postagemStore(@Valid Postagens postagem, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "Verifique todos os campos obrigatorios");
            return "redirect:/postagem/create";
        }

        postagem.setData(LocalDate.now());
        blogEvertonRepository.save(postagem);

        return "redirect:/postagens";
    }
}
