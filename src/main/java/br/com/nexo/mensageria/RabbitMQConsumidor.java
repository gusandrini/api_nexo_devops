package br.com.nexo.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQConsumidor {


	@Autowired
	private NotificacaoService notificacaoService;

	@RabbitListener(queues = RabbitMQConfig.FILA)
	public void lerMensagem(String mensagem) {
		System.out.println("Consumindo mensagem via RabbitMQ: " + mensagem);
		notificacaoService.adicionar(mensagem);
	}
	
}
