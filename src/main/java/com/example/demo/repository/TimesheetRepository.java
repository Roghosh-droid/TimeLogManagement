package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.ResourceLeave;
import com.example.demo.Entity.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
	 @Query("SELECT u FROM Timesheet u WHERE u.timesheetDetailMonth =:a")
	  

	List<Timesheet> findByTimesheetLeaveMonth(@Param("a") String leaveMonth);
	 
	 
	 @Query("INSERT INTO Timesheet (timesheetDetailMonth, templateNameId, resourceId, jobName, billableStatus, billableHour, createdBy, creationDate, updatedBy, updationDate, version,isActive) " +
	            "VALUES (:timesheetDetailMonth, :templateNameId, :resourceId, :jobName, :billableStatus, :billableHour, :createdBy, :creationDate, :updatedBy, :updationDate, :version, :isActive)")
	    void insertTimesheet(
	            @Param("timesheetDetailMonth") Long createdBy,
	            @Param("templateNameId") Long creationDate,
	            @Param("resourceId") Long resourceId,
	            @Param("jobName") String leaveEndDate,
	            @Param("billableStatus") int leaveMonth,
	            @Param("billableHour") Long leaveStartDate,
	            @Param("createdBy") String leaveType,
	            @Param("creationDate") Date numberOfDays,
	            @Param("updatedBy") String By,
	            @Param("updationDate") Date updatedBy,
	            @Param("version") Long updationDate,
	            @Param("isActive") int isActive
	    );
	 
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.timesheetDetailMonth =:a")
	  List<Timesheet> findByTimesheetMonth(@Param("a") String leaveMonth);

}
