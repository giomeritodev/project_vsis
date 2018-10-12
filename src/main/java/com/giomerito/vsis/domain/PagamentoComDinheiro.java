package com.giomerito.vsis.domain;

import javax.persistence.Entity;

import com.giomerito.vsis.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComDinheiro extends Pagamento{
	private static final long serialVersionUID = 1L;

	private Double valorPago;
		
	public PagamentoComDinheiro() {
		
	}

	public PagamentoComDinheiro(Integer id, EstadoPagamento estado, Pedido pedido, Double valorPago) {
		super(id, estado, pedido);
		this.valorPago = valorPago;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
}
