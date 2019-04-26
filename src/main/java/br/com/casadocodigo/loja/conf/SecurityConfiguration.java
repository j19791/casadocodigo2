package br.com.casadocodigo.loja.conf;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // habilita segurança de forma automática
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {//armazenar as configurações de segurança e configura (Adapter)

}
