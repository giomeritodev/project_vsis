package com.giomerito.vsis.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.giomerito.vsis.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComDinheiro")
public class PagamentoComDinheiro extends Pagamento{
	private static final long serialVersionUID = 1L;

	private Double valorPago;
	private Double valorDesconto;
		
	public PagamentoComDinheiro() {
		
	}

	public PagamentoComDinheiro(Integer id, EstadoPagamento estado, Pedido pedido, Double valorPago, Double valorDesconto) {
		super(id, estado, pedido);
		this.valorPago = valorPago;
		this.valorDesconto = valorDesconto;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
}
