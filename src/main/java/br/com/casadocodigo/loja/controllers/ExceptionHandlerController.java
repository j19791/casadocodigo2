package br.com.casadocodigo.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //este monitore todos os outros controllers da aplicação
public class ExceptionHandlerController {// o tratamento de erros deve ser feito em todos os controllers da aplicação.

	// capturar o objeto gerado com a mensagem de erro. Imprimir sua Stack Trace (Pilha de erros) no console e ainda enviar este objeto para a página
	@ExceptionHandler(Exception.class)
    public ModelAndView trataExceptionGenerica(Exception exception){
        System.out.println("Erro genérico acontecendo");
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
	
}
