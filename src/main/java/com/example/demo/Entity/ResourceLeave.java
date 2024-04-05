package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;
@ToString
@Entity
@Table(name = "resourseLeave")
public class ResourceLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long resourceLeaveId;

	private String leaveMonth;

	private String leaveType;

	private Long resourceId;

	private Long numberOfDays;

	private Date leaveStartDate;

	private Date leaveEndDate;

	private String createdBy;

	private Date creationDate;

	private String updatedBy;

	private Date updationDate;

	private Long version = 0L;

	private Boolean isActive = true;

	public Long getResourceLeaveId() {
		return resourceLeaveId;
	}

	public void setResourceLeaveId(Long resourceLeaveId) {
		this.resourceLeaveId = resourceLeaveId;
	}

	public String getLeaveMonth() {
		return leaveMonth;
	}

	public void setLeaveMonth(String leaveMonth) {
		this.leaveMonth = leaveMonth;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Long numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
