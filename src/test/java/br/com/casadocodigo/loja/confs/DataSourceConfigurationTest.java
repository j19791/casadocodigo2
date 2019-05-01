package br.com.casadocodigo.loja.confs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// nova classe de configuração para definir qual o banco de dados será usado para testes 
public class DataSourceConfigurationTest {

	
	@Bean //para q o Spring consiga manipula-lo
    @Profile("test") //para que o Spring consiga relacionar os Profiles de testes da aplicação.
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("pc1000");
        return dataSource; //descreve os dados de acesso ao banco
    }
}
