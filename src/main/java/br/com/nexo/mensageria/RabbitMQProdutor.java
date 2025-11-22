package br.com.nexo.mensageria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProdutor {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProdutor.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(String mensagem) {

        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.ROTEADOR,
                    RabbitMQConfig.CHAVE_ROTA,
                    mensagem
            );

            logger.info("Enviando mensagem via RabbitMQ: {}", mensagem);

        } catch (AmqpException e) {
            logger.warn("RabbitMQ indisponível. Mensagem NÃO enviada. Motivo: {}", e.getMessage());

        } catch (Exception e) {
            logger.warn("Falha inesperada ao enviar mensagem ao RabbitMQ. Ignorando. Motivo: {}", e.getMessage());
        }
    }
}
