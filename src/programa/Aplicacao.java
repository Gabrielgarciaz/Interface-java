package programa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelDeCarro;
import model.entities.Veiculo;
import model.service.AluguelService;
import model.service.BrasilTaxaService;

public class Aplicacao {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel: ");
		System.out.print("Qual o modelo do carro ?");
		String modelo = sc.nextLine();
		System.out.println("Data de retirada: (dd/MM/yyyy hh:mm): ");
		LocalDateTime inicio = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.println("Data de devolucao: (dd/MM/yyyy hh:mm): ");
		LocalDateTime fim = LocalDateTime.parse(sc.nextLine(), dtf);
		
		AluguelDeCarro carro = new AluguelDeCarro(inicio, fim, new Veiculo(modelo));
		
		
		System.out.print("Preço por hora: ");
		Double precoPorHora = sc.nextDouble();
		System.out.println("Preço por dia: ");
		Double precoPorDia = sc.nextDouble();
		
		AluguelService aluguelService = new AluguelService(precoPorHora, precoPorDia, new BrasilTaxaService());
		
		aluguelService.processoFatura(carro);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento basico: " + carro.getFatura().getPagamentoBasico());
		System.out.println("Imposto: " + carro.getFatura().getTaxa());
		System.out.println("Pagamento total: " + carro.getFatura().getValorTotal());
		sc.close();
	}
}
