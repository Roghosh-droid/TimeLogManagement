package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.ResourceLeave;

public interface LeaveRepository extends JpaRepository<ResourceLeave, Long> {
	@Query("INSERT INTO ResourceLeave (createdBy, creationDate, isActive, leaveEndDate, leaveMonth, leaveStartDate, leaveType, numberOfDays, resourceId, updatedBy, updationDate,version) " +
            "VALUES (:createdBy, :creationDate, :isActive, :leaveEndDate, :leaveMonth, :leaveStartDate, :leaveType, :numberOfDays, :resourceId, :updatedBy, :updationDate, :version)")
    void insertLeave(
            @Param("createdBy") String createdBy,
            @Param("creationDate") Date creationDate,
            @Param("isActive") boolean isActive,
            @Param("leaveEndDate") Date leaveEndDate,
            @Param("leaveMonth") String leaveMonth,
            @Param("leaveStartDate") Date leaveStartDate,
            @Param("leaveType") String leaveType,
            @Param("numberOfDays") int numberOfDays,
            @Param("resourceId") Long resourceId,
            @Param("updatedBy") String updatedBy,
            @Param("updationDate") Date updationDate,
            @Param("version") Long version
    );
	
	 @Query("SELECT u FROM ResourceLeave u WHERE u.leaveMonth =:a")
	  List<ResourceLeave> findByLeaveMonth(@Param("a") String leaveMonth);

}
