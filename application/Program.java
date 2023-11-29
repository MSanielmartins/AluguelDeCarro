package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrasilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel) );
		
		System.out.print("entre com o preco por Horus: ");
		Double pricyPerHorus = sc.nextDouble();
		System.out.print("entre com o preco por day: ");
		Double pricyPerDay = sc.nextDouble();

	

		RentalService rentalService = new RentalService(pricyPerDay, pricyPerHorus, new BrasilTaxService());
		
		rentalService.processInvoicee(cr);
		
		System.out.println("FATURA");
		System.out.println();
		System.out.println("PAGAMENTO BASICO :" + cr.getInvoice().getBasicPayment());
		System.out.println("taxa: " + cr.getInvoice().getTax());
		System.out.println("total a ser pago = " + cr.getInvoice().getTotalPayment());
		


	}

}