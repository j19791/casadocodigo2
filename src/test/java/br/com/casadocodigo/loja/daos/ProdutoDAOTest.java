package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

public class ProdutoDAOTest {

	
	@Test
	public void deveSomarTodosOsPrecosPorTipoLivro() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
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
