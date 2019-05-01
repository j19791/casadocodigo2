package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.confs.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

//JUnit deverá carregar configurações do Spring Test para poder executar os testes
@RunWith(SpringJUnit4ClassRunner.class) //classe irá executar os testes encontrados na nossa suite de testes.
@ContextConfiguration(classes = {JPAConfiguration.class,ProdutoDAO.class, DataSourceConfigurationTest.class})// classes de configurações para execução dos testes
@ActiveProfiles("test") //recurso no qual podemos agrupar configurações para determinadas partes da aplicação
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Transactional //o metodo abaixo precisa de uma transação com o banco de dados para que tudo funcione corretamente
	@Test
	public void deveSomarTodosOsPrecosPorTipoLivro() {
		
		
		
		//3 produtos do tipo impresso, valor 10
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
		
		//3 produtos do tipo ebbok com valor 10
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();
		
		//laço para percorrer cada uma das listas e salvar cada um dos produtos no banco de dados com produtoDAO
		livrosImpressos.stream().forEach(produtoDAO::gravar); 
	    livrosEbook.stream().forEach(produtoDAO::gravar);
		
	    
	    BigDecimal valor = produtoDAO.somaPrecosPorTipo(TipoPreco.EBOOK);
	    Assert.assertEquals(new BigDecimal(40).setScale(2), valor);//valor retornado do banco de dados é igual ao valor da soma dos produtos ??
		//setScale(2)  adiciona duas casas decimais ao valor 40
		
	}
	
	
}
