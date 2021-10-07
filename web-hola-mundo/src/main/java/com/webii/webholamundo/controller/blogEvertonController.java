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

    @RequestMapping(value = "/postagens/tipo/{id}", method = RequestMethod.GET)
    public ModelAndView getPostagensByTipo(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("postagens");
        List<Postagens> postagens = blogEvertonRepository.findPostagensByTipo(id);
        mv.addObject("postagens", postagens);

        return mv;
    }

    @RequestMapping(value = "/postagem/delete/{id}", method = RequestMethod.GET)
    public String deletePostagem(@PathVariable("id") int id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("postagem");
        blogEvertonRepository.deleteById(id);
        attributes.addFlashAttribute("success", "Deletado con exito!");

        return "redirect:/postagens";
    }

    @RequestMapping(value = "/postagem/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPostagem(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("postagem-update-form");
        Optional<Postagens> postagem = blogEvertonRepository.findById(id);
        mv.addObject("autor", postagem.get().getAutor());
        mv.addObject("titulo", postagem.get().getTitulo());
        mv.addObject("data", postagem.get().getData());
        mv.addObject("tipo", postagem.get().getTipo());
        mv.addObject("texto", postagem.get().getTexto());

        return mv;
    }

    @RequestMapping(value = "/postagem/update", method = RequestMethod.POST)
    public String updatePostagem(Postagens postagens, RedirectAttributes attributes) {
        Postagens post = blogEvertonRepository.findById(postagens.getId()).orElse(null);

        if(post == null){
            attributes.addFlashAttribute("msg", "ERROR!");
            return "redirect:/postagens";
        }

        post.setTipo(postagens.getTipo());
        post.setAutor(postagens.getAutor());
        post.setTitulo(postagens.getTitulo());
        post.setTexto(postagens.getTexto());
        blogEvertonRepository.save(post);
        attributes.addFlashAttribute("success", "Atualizado!");

        return "redirect:/postagens";
    }
}
