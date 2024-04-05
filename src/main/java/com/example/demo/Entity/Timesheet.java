package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "timesheet_detail")
public class Timesheet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long timesheetDetailId;

    
    private String timesheetDetailMonth;

    
    private Long templateNameId;

    
    private Long resourceId;

    
    private String jobName;

    
    private int billableStatus;

   
    private Long billableHour;

    
    private String createdBy;

    
    private Date creationDate;

    
    private String updatedBy;

    
    private Date updationDate;

    
    private Long version;

    
    private int isActive;


	public Long getTimesheetDetailId() {
		return timesheetDetailId;
	}


	public void setTimesheetDetailId(Long timesheetDetailId) {
		this.timesheetDetailId = timesheetDetailId;
	}


	public String getTimesheetDetailMonth() {
		return timesheetDetailMonth;
	}


	public void setTimesheetDetailMonth(String timesheetDetailMonth) {
		this.timesheetDetailMonth = timesheetDetailMonth;
	}


	public Long getTemplateNameId() {
		return templateNameId;
	}


	public void setTemplateNameId(Long templateNameId) {
		this.templateNameId = templateNameId;
	}


	public Long getResourceId() {
		return resourceId;
	}


	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public int getBillableStatus() {
		return billableStatus;
	}


	public void setBillableStatus(int billableStatus) {
		this.billableStatus = billableStatus;
	}


	public Long getBillableHour() {
		return billableHour;
	}


	public void setBillableHour(Long billableHour) {
		this.billableHour = billableHour;
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


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

    
}


