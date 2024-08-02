package com.example.lmslab7.Controller;

import com.example.lmslab7.Api.ApiResponse;
import com.example.lmslab7.Model.Department;
import com.example.lmslab7.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    //1.Get all Departments
    @GetMapping("/get")
    public ResponseEntity getAllDepartments (){
        return ResponseEntity.status(200).body(departmentService.getAllDepartments());
    }

    //2.Add new Department
    @PostMapping("/add")
    public ResponseEntity addDepartment (@Valid @RequestBody Department department , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        departmentService.addDepartment(department);
        return ResponseEntity.status(200).body(new ApiResponse("Department added successfully"));
    }

    //3.update Department
    @PutMapping("/update/{id}")
    public ResponseEntity updateDepartment(@PathVariable String id,@Valid @RequestBody Department department , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = departmentService.updateDepartment(id, department);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Department updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Department not found"));
    }

    //4.delete Department
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDepartment(@PathVariable String id){
        boolean isDeleted = departmentService.deleteDepartment(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Department deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Department not found"));
    }

    //5.Search HOD by id
    @GetMapping("search/{id}")
    public ResponseEntity SearchHOD (@PathVariable String id){
        Department hod = departmentService.SearchHOD(id);

        if(hod == null){
            return ResponseEntity.status(400).body(new ApiResponse("Department not found"));

        }
        return ResponseEntity.status(200).body(hod);
    }


    //6.Display department by department name
    @GetMapping("/display/{name}")
  public ResponseEntity DisplayDepartments (@PathVariable String name){
        Department departmentsbyn = departmentService.DisplayAllDepartmentsByName(name);
        if(departmentsbyn == null){
            return ResponseEntity.status(400).body(new ApiResponse("Department not found"));
        }
        return ResponseEntity.status(200).body(departmentsbyn);

  }
    //7.The department is full when number of instructors equals 30 and number of students equals 300.
    @PutMapping("/full/{id}")
  public ResponseEntity isFull (@PathVariable String id){

      boolean isFull = departmentService.isFull(id);
      if(isFull){
          return ResponseEntity.status(200).body(new ApiResponse("Department is Successfully full"));
      }
      return ResponseEntity.status(400).body(new ApiResponse("Department not full"));
    }

    //8.Display All full department
    @GetMapping("/all/full")
    public ResponseEntity DisplayAllFullDepartments(){
        ArrayList <Department> full = departmentService.DisplayAllFullD();
        if(full.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Department not found"));
        }
        return ResponseEntity.status(200).body(full);

    }






}
