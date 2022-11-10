package com.policymanager.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.policymanager.api.model.CustPolicy;
import com.policymanager.api.model.Policy;

@Component
public class ResCustPolicyDto {
	
	private LocalDate startDate;// localdate
	private LocalDate endDate;// localdate
	private double maturityAmount;
	private double termAmount;
	
	public double getTermAmount() {
		return termAmount;
	}

	public void setTermAmount(double termAmount) {
		this.termAmount = termAmount;
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



	



	public static List<ResCustPolicyDto> convertToDto(List<CustPolicy> list) {
		List<ResCustPolicyDto> listDto = new ArrayList<>();
		for (CustPolicy cp : list) {
			ResCustPolicyDto dto = new ResCustPolicyDto();
			Policy p = new Policy();
			
			dto.setStartDate(cp.getStartDate());
			dto.setEndDate(cp.getEndDate());
			dto.setMaturityAmount(cp.getMaturityAmount());
			listDto.add(dto);
		}
		return listDto;
	}

}
