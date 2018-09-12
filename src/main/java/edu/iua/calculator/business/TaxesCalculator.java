package edu.iua.calculator.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import org.json.JSONObject;

/**
 * Taxes Calculator returns a calculated total amount based on taxes percentages
 */
public class TaxesCalculator {
	public static void main(String[] args) {
		System.out.print("Enter amount value: ");

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		try {

			float amount = Float.parseFloat(br.readLine());
			HashMap<String, Float> calculatedTax = calculate(amount);

			JSONObject obj = new JSONObject(calculatedTax);

			System.out.println(obj);

		} catch (IOException e) {
			System.out.println("Sorry, an error has occurred. Please try again!");
		} catch (NumberFormatException e) {
			System.out.println("Invalid amount value. Please try again!");
		}

	}

	public static HashMap<String, Float> getTaxesPercentage() {

		HashMap<String, Float> taxesPercentage = new HashMap<String, Float>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select tax_name, tax_percentage from taxes_details");
			while (rs.next()) {
				taxesPercentage.put(rs.getString(1), rs.getFloat(2));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return taxesPercentage;
	}

	public static HashMap<String, Float> calculate(float amount) {

		HashMap<String, Float> calculatedTaxesAmount = new HashMap<String, Float>();

		
		HashMap<String, Float> taxesPercentages = getTaxesPercentage();

		System.out.println("Applicable taxes:");
		float totalAmount = amount;
		for (String tax : taxesPercentages.keySet()) {
			float taxValue = taxesPercentages.get(tax);
			float applicableTax = amount * taxValue;
			calculatedTaxesAmount.put(tax, applicableTax);
			System.out.println("TAX: " + tax + " = " + applicableTax);
			totalAmount = totalAmount + applicableTax;
		}

		System.out.println("\nTOTAL Amount: " + totalAmount);

		return calculatedTaxesAmount;
	}
}
