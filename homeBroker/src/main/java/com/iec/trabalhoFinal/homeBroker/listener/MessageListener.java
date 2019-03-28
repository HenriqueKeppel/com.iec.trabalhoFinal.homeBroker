package com.iec.trabalhoFinal.homeBroker.listener;

import java.util.Set;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.iec.trabalhoFinal.homeBroker.config.RabbitMQConfig;
import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Acionista;
import com.iec.trabalhoFinal.homeBroker.domain.Message;
import com.iec.trabalhoFinal.homeBroker.emailSender.EmailConfig;
import com.iec.trabalhoFinal.homeBroker.services.IAcaoService;
import com.iec.trabalhoFinal.homeBroker.services.IAcionistaService;

@Component
public class MessageListener {
	
	//static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	private final IAcaoService acaoService;
	private final IAcionistaService acionistaService;
	
	public MessageListener(IAcaoService acaoService,
			IAcionistaService acionistaService) {
		this.acaoService = acaoService;
		this.acionistaService = acionistaService;
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
	public void processMessage(Message message) {
		/*logger.info("Message received");
		logger.info("AcionistaId: " + message.getAcionistaId());
		logger.info("EmpresaId: " + message.getEmpresaId());		
		logger.info("Qtd: " + message.getQtd());*/
		
		Set<Acao> acoes = acaoService.getAcoesByEmpresaId(message.getEmpresaId());
    	int qtdComprada = 0;
    	
    	for (Acao acao : acoes) {    		
    		if (qtdComprada == message.getQtd())
    			break;
    		
    		if (acao.getAVenda() == true) {
    			acao.setAcionistaId(message.getAcionistaId());
    			acao.setAVenda(false);
    			
    			acaoService.saveAcao(acao.getId(), acao);
    			qtdComprada++;
    		}    		
    	}
    	
    	// Enviar email de confirmacao de compra
    	Acionista acionista = acionistaService.getById(message.getAcionistaId());
    	    	
		final String toEmail = acionista.getEmail();		
		
		EmailConfig config = new EmailConfig();
		
		config.sendEmail(toEmail, "Subject", "Email Body");
	}		
}
