package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

   private String projectName;
   private String projectCode;
   private UserDTO assignedManager; //Another Object, we add this because to define Assigned Manager

   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private LocalDate startDate;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private LocalDate endDate;
   private String projectDetail;
   private Status projectStatus;

}
/*
We use time formatter because Spring uses different format than the LocalDate method and need to be matched.
 */
