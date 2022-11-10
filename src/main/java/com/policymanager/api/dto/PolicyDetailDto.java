package com.policymanager.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.policymanager.api.model.CustPolicy;
import com.policymanager.api.model.Policy;

@Component
public class PolicyDetailDto {
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
	private LocalDate startDate;
	private LocalDate endDate;
	private double maturityAmount;
	private double termAmount;

	public double getTermAmount() {
		return termAmount;
	}

	public void setTermAmount(double termAmount) {
		this.termAmount = termAmount;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public static List<PolicyDetailDto> convertToDto(Policy policy,LocalDate startDate,LocalDate endDate,
			double maturityAmount,double termAmount) {
		List<PolicyDetailDto> listDto = new ArrayList<>();

		

			PolicyDetailDto dto = new PolicyDetailDto();
			dto.setStartDate(startDate);
			dto.setEndDate(endDate);
			dto.setMaturityAmount(maturityAmount);
			dto.setTermAmount(termAmount);

			dto.setId(policy.getId());
			dto.setName(policy.getName());
			dto.setCategory(policy.getCategory());
			dto.setDuration(policy.getDuration());
			dto.setInitialDeposit(policy.getInitialDeposit());
			dto.setRateOfInterest(policy.getRateOfInterest());
			dto.setTermPerYear(policy.getTermPerYear());
			dto.setStatus(policy.getStatus());
			dto.setBuyCount(policy.getBuyCount());
			dto.setSlab1(policy.getSlab1());
			dto.setSlab2(policy.getSlab2());
			dto.setSlab3(policy.getSlab3());
			dto.setSlab4(policy.getSlab4());
			dto.setVendorName(policy.getVendor().getName());

			listDto.add(dto);
		

		return listDto;
	}
}
