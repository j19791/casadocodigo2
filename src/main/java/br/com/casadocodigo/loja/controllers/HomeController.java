package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {

	@Autowired
    ProdutoDAO produtoDao;

    @RequestMapping("/")
    public ModelAndView index(){
        List<Produto> produtos = produtoDao.listar(); //buscar os produtos no BD
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);//manda a lista de produtos para a view home
        return modelAndView;//não estamos mais retornando o nome da view, mas sim, o objeto modelAndView que sabe qual view carregar e leva a lista de produtos para serem exibidos
    }
}
