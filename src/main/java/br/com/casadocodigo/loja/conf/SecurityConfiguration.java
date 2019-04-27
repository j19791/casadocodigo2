package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // habilita segurança de forma automática
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService usuarioDao;


//armazenar as configurações de segurança e configura (Adapter)

	@Override
	protected void configure(HttpSecurity http) throws Exception {//descrever os padrões de URLs que queremos ou não bloquear.
		http.authorizeRequests()
	    .antMatchers("/resources/**").permitAll() //permitindo o acesso a todos
	    .antMatchers("/carrinho/**").permitAll() //permitindo o acesso a todos 
	    .antMatchers("/pagamento/**").permitAll() //permitindo o acesso a todos
	    .antMatchers("/produtos/form").hasRole("ADMIN") //bloqueando o acesso para todos que não são ADMIN
	    .antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN") //bloqueando o acesso para todos que não são ADMIN
	    .antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN") //bloqueando o acesso para todos que não são ADMIN
	    .antMatchers("/produtos/**").permitAll() //permitindo o acesso a todos
	    .antMatchers("/resources/**").permitAll() //desbloqueia os resources de CSS e JS do Bootstrap
	    .antMatchers("/").permitAll() //permitindo o acesso a todos
	    .anyRequest().authenticated() //verificações devem ser feitas para todas as requisições e que as bloqueadas através do hasRole devem ser autenticadas.
	    .and().formLogin().loginPage("/login").permitAll() //utilizando a view de login personalizada e nao mais a padrão
	    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));//logout
	}
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDao).passwordEncoder(new BCryptPasswordEncoder());;
    }
	
}
