package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.confs.DataSourceConfigurationTest;

@WebAppConfiguration //carregamento das demais configurações de MVC do Spring
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, 
		AppWebConfiguration.class,// carregamos a classe que tem todas as configurações de MVC da aplicação 
		DataSourceConfigurationTest.class,
		SecurityConfiguration.class}) //especificamos as classes de configurações do Spring Security
@ActiveProfiles("test")
public class ProdutosControllerTest {

	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
    private Filter springSecurityFilterChain;

	private MockMvc mockMvc;//objeto capaz de simular (mock) requisições sem o uso de um navegador
	
	
	@Before //método que será executado antes dos testes: carregamento de recursos e definição de configurações que devem ser executadas antes de qualquer teste.
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void deveRetornarParaHomeComOsLivros() throws Exception {//fazer uma requisição para a página inicial e verificar se a view home.jsp está realmente sendo retornada. 
	    mockMvc.perform(MockMvcRequestBuilders.get("/")) //requisicao
	    .andExpect(MockMvcResultMatchers.model().attributeExists("produtos")) //verificar os objetos (produtos) retornados pela requição  na resposta da app
	    .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));//verificará se foi feito um redirecionamento no servidor para a view
	}
	
	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form") 
	  //tentativa de autenticação com Post Processor: processo de POST antes de executar o GET da página, passando neste Post Processor os dados de autenticação do usuário.
	    	.with(SecurityMockMvcRequestPostProcessors
                .user("user@casadocodigo.com.br")
                .password("123456")
                .roles("Usuario")))
            	.andExpect(MockMvcResultMatchers.status().is(403));//permissao OK
	    

	}
	
}
