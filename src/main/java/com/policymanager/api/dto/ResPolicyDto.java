package com.policymanager.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.policymanager.api.model.Policy;

public class ResPolicyDto {

	private Long id;
	private String name;
	private String category;
	private int duration;
	private double initialDeposit;
	private int termPerYear;
	private double rateOfInterest;
	private String status;
	private String vendorName;
	private Long buyCount;
	private double slab1;
	private double slab2;
	private double slab3;
	private double slab4;
	
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


	public String getVendorName() {
		return vendorName;
	}


	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	public Long getBuyCount() {
		return buyCount;
	}


	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}


	public static List<ResPolicyDto> convertToDto(List<Policy> list) {
        List<ResPolicyDto> listDto = new ArrayList<>();
        for (Policy p : list) {
            ResPolicyDto dto = new ResPolicyDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setCategory(p.getCategory());
            dto.setDuration(p.getDuration());
            dto.setInitialDeposit(p.getInitialDeposit());
            dto.setRateOfInterest(p.getRateOfInterest());
            dto.setTermPerYear(p.getTermPerYear());
            dto.setSlab1(p.getSlab1());
            dto.setSlab2(p.getSlab2());
            dto.setSlab3(p.getSlab3());
            dto.setSlab4(p.getSlab4());
            dto.setStatus(p.getStatus());
            dto.setBuyCount(p.getBuyCount());
            dto.setVendorName(p.getVendor().getName());
            listDto.add(dto);
        }
        return listDto;
    }
	
}
