package com.example.lmslab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Instructor {

    @NotNull(message= "Id should not be Null!")
    private int id;

    @NotEmpty(message = "Name should not be Empty!")
    private String name;

    @Email
    private String email;

    @NotNull(message = "Salary should not be Null!")
    private double salary;

    @NotEmpty(message = "Position should not be Empty!")
    @Pattern(regexp = "^(Professor|Teacher Assistant)$", message = "Two valid inputs: Professor or Teacher Assistant only")
    private String position;

    @AssertFalse
    private boolean isCheckedin;

    @AssertFalse
    private boolean isCheckedout;

    @NotNull(message= "Id should not be Null!")
    @Min(value = 2, message = "courses assigned must be between 2-4")
    @Max(value= 4)
    private int courses;

    @NotNull(message= "Absence Days should not be Null!")
    private int absenceDays;

    @NotNull(message= "Years Of Experience should not be Null!")
    private int YearsOfExperience;


}
