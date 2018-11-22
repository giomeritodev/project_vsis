package com.giomerito.vsis.services;

import org.springframework.mail.SimpleMailMessage;

import com.giomerito.vsis.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
