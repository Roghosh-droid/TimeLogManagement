package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long roleId;

    
    private String roleDescription;

    
    private String createdBy;

    
    private Date creationDate;

    
    private String updatedBy;

    
    private Date updationDate;

    
    private Long version = 0L;

    
    private Boolean isActive = true;


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public String getRoleDescription() {
		return roleDescription;
	}


	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
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


