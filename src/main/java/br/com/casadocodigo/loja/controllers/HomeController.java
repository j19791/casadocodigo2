package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {

	@Autowired
    ProdutoDAO produtoDao;

	@Cacheable(value="produtosHome") //ConcurrentMapCacheManager usa Map (conjunto de chave e valor) para gerenciar o cache. passarmos um parâmetro para a anotação @Cacheable Indicar qual é a chave do cache daquele método. 
	//recurso que permite que guardemos dados no contexto da aplicação, não no bd (acesso ao hd). Qdo os dados forem requisitados, a app já as terá e não precisará carregá-las novamente.
    // lista não muda com muita frequência: usar cache
	@RequestMapping("/")
    public ModelAndView index(){
        List<Produto> produtos = produtoDao.listar(); //buscar os produtos no BD
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);//manda a lista de produtos para a view home
        return modelAndView;//não estamos mais retornando o nome da view, mas sim, o objeto modelAndView que sabe qual view carregar e leva a lista de produtos para serem exibidos
    }
}
