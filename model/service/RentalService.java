package model.service;

import java.time.Duration;

import model.entities.CarRental;import model.entities.Invoice;

public class RentalService {
	private double priciPerDay;
	private double pricePerHours;
	
	private TaxService taxService;

	public RentalService(double priciPerDay, double pricePerHours, TaxService taxService) {
		this.priciPerDay = priciPerDay;
		this.pricePerHours = pricePerHours;
		this.taxService = taxService;
	}

	
	  public void processInvoicee(CarRental carRental) {
		double minuts = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minuts / 60.0;
	
		double basicPeyment;
		
		if(hours <= 12.0) {
			basicPeyment = pricePerHours * Math.ceil(hours);
		}
		else {
			basicPeyment = priciPerDay * Math.ceil(hours / 24.0);
		}
	
		double tax = taxService.tax(basicPeyment);
		
		carRental.setInvoice(new Invoice(basicPeyment, tax));
		
		
	  }
		 
	}


