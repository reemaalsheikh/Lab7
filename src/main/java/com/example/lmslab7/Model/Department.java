package com.example.lmslab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Department {

    @NotEmpty(message= "Id should not be Empty!")
    @Pattern(regexp = "^8\\d{4}$",message = "Id must start with 8, consists of exactly 5 digits")
    @Size(max = 5)
    private String id;

    @Email
    private String email;

    @NotEmpty(message = "Department Name should not be Empty!")
    @Pattern(regexp = "^(IT|IS|CS)$", message = "Two valid inputs: IT(Information Technology) or IS(Information System) or CS(Computer Science)only")
    private String departmentName;

    @NotEmpty(message = "Head Of Department Name should not be Empty!")
    //Head Of Department Name
    private String HODName;

    @NotNull(message= "Number Of Instructors should not be Null!")
    private int numberOfInstructors;

    @NotNull(message= "Number Of Students should not be Null!")
    private int numberOfStudents;

    @AssertFalse
    private boolean isFull;


}
