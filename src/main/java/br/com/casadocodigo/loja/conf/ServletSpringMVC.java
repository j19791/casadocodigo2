package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {//carrega configurações logo ao iniciar a aplicação
		return new Class[]{SecurityConfiguration.class,AppWebConfiguration.class, JPAConfiguration.class};//para que a classe a classe SecurityConfiguration (Spring Security) ser reconhecida pelo Spring
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[] {encodingFilter};
    }
	
	//veja tbm https://cursos.alura.com.br/forum/topico-atualizacao-resources-nao-sao-carregados-na-aula-10-58813
	@Override
	protected void customizeRegistration(Dynamic registration) {
			registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	
	//definir qual configuração de Data Source o Spring deve usar ao inicializar a aplicação, precisaremos de um ouvinte de contexto, que ao perceber a inicialização da aplicação, defina que o profile a ser utilizado será o de dev
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    super.onStartup(servletContext);
	    servletContext.addListener(new RequestContextListener());//adicionaremos um ouvinte de contexto de requisição
	    servletContext.setInitParameter("spring.profiles.active", "dev");
	}
}