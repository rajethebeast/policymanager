package com.policymanager.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "policy")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String category;
	private int duration;
	private double initialDeposit;
	private int termPerYear;
	private double rateOfInterest;
	private String status;
	private Long buyCount;
	private double slab1;
	private double slab2;
	private double slab3;
	private double slab4;

	@OneToOne
	private Vendor vendor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public int getTermPerYear() {
		return termPerYear;
	}

	public void setTermPerYear(int termPerYear) {
		this.termPerYear = termPerYear;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}
	
	public double getSlab1() {
		return slab1;
	}

	public void setSlab1(double slab1) {
		this.slab1 = slab1;
	}

	public double getSlab2() {
		return slab2;
	}

	public void setSlab2(double slab2) {
		this.slab2 = slab2;
	}

	public double getSlab3() {
		return slab3;
	}

	public void setSlab3(double slab3) {
		this.slab3 = slab3;
	}

	public double getSlab4() {
		return slab4;
	}

	public void setSlab4(double slab4) {
		this.slab4 = slab4;
	}



	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", name=" + name + ", category=" + category + ", duration=" + duration
				+ ", initialDeposit=" + initialDeposit + ", termPerYear=" + termPerYear + ", rateOfInterest="
				+ rateOfInterest + ", status=" + status + ", buyCount=" + buyCount + ", slab1=" + slab1 + ", slab2="
				+ slab2 + ", slab3=" + slab3 + ", slab4=" + slab4 + ", vendor=" + vendor + "]";
	}

	

}
