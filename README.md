# casadocodigo2
Projeto prático do curso Spring MVC - Parte 2

[Link do projeto inicial](https://s3.amazonaws.com/caelum-online-public/springmvc-2-integracao-cache-seguranca-e-templates/springmvc2-projeto-inicial.zip)

## Content Negotiation
[Acesso ao json do detalhe do produto]
(http://localhost:8080/casadocodigo/produtos/detalhe/15.json)

## BootStrap
Download Compiled CSS and JS (http://getbootstrap.com/getting-started/#download)  ejogar as pastas descompactadas na pasta resources

### Documentação CSS 
[CSS](http://getbootstrap.com/css)

## Autenticação com Spring Security
### Inserindo usuário e role no banco de dados

insert into Role values ('ROLE_ADMIN');

insert into Usuario (email, nome, senha) values ('admin@casadocodigo.com.br', 'Administrador', '$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq');

insert into Usuario_Role (Usuario_email, roles_nome) values ('admin@casadocodigo.com.br', 'ROLE_ADMIN');

senha do usuário: 123456

[BCrypt Calculator](https://www.dailycred.com/article/bcrypt-calculator)


## Logout
(http://localhost:8080/casadocodigo/logout)
