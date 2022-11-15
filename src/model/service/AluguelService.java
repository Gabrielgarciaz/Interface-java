package model.service;

import java.time.Duration;

import model.entities.AluguelDeCarro;
import model.entities.Fatura;

public class AluguelService {
	private Double precoPorHora;
	private Double precoPorDia;
	
	private TaxaService taxaService;

	public AluguelService(Double precoPorHora, Double precoPorDia, TaxaService taxaService) {
		super();
		this.precoPorHora = precoPorHora;
		this.precoPorDia = precoPorDia;
		this.taxaService = taxaService;
	}
	public void processoFatura(AluguelDeCarro carroAluguel) {
		double minutos = Duration.between(carroAluguel.getInicio(), carroAluguel.getFim()).toMinutes();
		double horas = minutos / 60;
		
		double pagamentoBasico;
		if(horas <= 12) {
			pagamentoBasico = precoPorHora * Math.ceil(horas); // ceil arredondando pra cima
		
		}else {
			pagamentoBasico = precoPorDia * Math.ceil(horas / 24.0);
		}
		
		double tax = taxaService.taxa(pagamentoBasico);
		
		carroAluguel.setFatura(new Fatura(pagamentoBasico, tax));
	}
	
	
	
}
