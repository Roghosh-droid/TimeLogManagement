package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")

public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long userId;

   
    private String userLoginId;

    
    private String password;

    
    private String userType;

    
    private Long roleId;

    
    private String createdBy;

    
    private Date creationDate;

    
    private String updatedBy;

   
    private Date updationDate;

    
    private Long version = 0L;

    
    private boolean isActive = true;
    
    @OneToMany(targetEntity = Role.class, fetch = jakarta.persistence.FetchType.EAGER)
    private List<Role> roles; 


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserLoginId() {
		return userLoginId;
	}


	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

    // Getters and setters (omitted for brevity)
}


