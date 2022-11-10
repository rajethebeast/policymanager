package com.policymanager.api.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustPolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long policyId;
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

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "CustPolicy [id=" + id + ", policyId=" + policyId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", maturityAmount=" + maturityAmount + "]";
	}

}
