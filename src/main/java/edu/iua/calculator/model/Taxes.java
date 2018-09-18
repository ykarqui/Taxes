package edu.iua.calculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taxes_details")
public class Taxes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "tax_name")
	private String taxName;
	@Column(name = "tax_percentage")
	private Float taxPercentage;


	public Taxes() {
	}

	
	public Taxes(int id, String taxName, Float taxPercentage) {
		super();
		this.id = id;
		this.taxName = taxName;
		this.taxPercentage = taxPercentage;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public Float getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Float taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	
    


}
