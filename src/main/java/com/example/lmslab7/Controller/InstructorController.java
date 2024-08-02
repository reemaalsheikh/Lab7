package com.example.lmslab7.Controller;
import com.example.lmslab7.Api.ApiResponse;
import com.example.lmslab7.Model.Instructor;
import com.example.lmslab7.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {

private final InstructorService instructorService;


    //1.Get all Instructors
    @GetMapping("/get")
    public ResponseEntity getAllInstructors (){
        return ResponseEntity.status(200).body(instructorService.getAllInstructors());
    }

    //2.Add new Instructor
    @PostMapping("/add")
    public ResponseEntity addInstructor (@Valid @RequestBody Instructor instructor , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body(new ApiResponse("Instructor added successfully"));
    }

    //3.update Instructor
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@Valid @RequestBody Instructor instructor  , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = instructorService.updateInstructor(id, instructor);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Instructor updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));
    }

    //4.delete Instructor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id){
        boolean isDeleted = instructorService.deleteInstructor(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Instructor deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));
    }


    //5.check In and Out Instructors.
    @PutMapping("/checkInOut/{id}")
    public ResponseEntity checkInOut(@PathVariable int id){
        boolean ischeckedInOut = instructorService.checkInOut(id);
        if(ischeckedInOut){
            return ResponseEntity.status(200).body(new ApiResponse("Instructor checked In and Out successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));
    }


    //6.Display All checked in and checked out Instructors
    @GetMapping("/check/inout")
    public ResponseEntity DisplayAllCheckedInOutInstructors (){
        return ResponseEntity.status(200).body(instructorService.CheckedInOutInstructors());
    }

    //7.Serach Instructor by Id
    @GetMapping("/get/{id}")
    public ResponseEntity SearchInstructorById(@PathVariable int id){
        Instructor instructor = instructorService.InstructorById(id);

        if (instructor == null){
            return ResponseEntity.status(404).body(new ApiResponse("Instructor not found"));
        }
        return ResponseEntity.status(200).body(instructor);
    }

    //8.Take the absence days and return all instructor salary with bonus (if absence days =0)
    // Verify that the instructor exists.
    @PutMapping("/update/salary/{id}")
    public ResponseEntity ChangeSalary(@PathVariable int id){

        Instructor cs = instructorService.ChangeSalary(id);

        if (cs == null){
            return ResponseEntity.status(400).body(new ApiResponse("Instructor does not Qualify for bonus!"));
        }
        return ResponseEntity.status(200).body(instructorService.ChangeSalary(id));

    }

    //9.Promote Instructor: Allows a professor to promote the Teacher Assistant.
    // Verify that the instructor with the specified ID exists.
    // Ensure that the requester is a professor.
    // Validate that the instructor years of experience is 8 or more.
    // Change the instructor position to "Professor" if they meet the criteria.
    @PutMapping("/promotion/{firstId}/{secondId}")
    public ResponseEntity InstructorPromotion (@PathVariable int firstId,@PathVariable int secondId) {
        ArrayList <Instructor> promote = instructorService.promoteInstructor(firstId,secondId);

        if (promote.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Instructor does not Qualify for promotion!"));
        }

     return ResponseEntity.status(200).body(promote);
    }



}
