package com.example.lmslab7.Service;

import com.example.lmslab7.Model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DepartmentService {

    ArrayList<Department> departments = new ArrayList<>();


    //1.Get all Departments
    public ArrayList<Department> getAllDepartments () {
        return departments;
    }
    //2.Add new department
    public void addDepartment (Department department) {
        departments.add(department);
    }

    //3.update department
    public boolean updateDepartment (String id, Department department) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId().equals(id)) {
                departments.set(i, department);
                return true;
            }
        }
        return false;
    }

    //4.delete department
    public boolean deleteDepartment (String id) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId().equals(id)) {
                departments.remove(i);
                return true;
            }
        }
        return false;
    }


    //5.Search HOD by id
    public Department SearchHOD (String id){
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId().equals(id)) {
                return departments.get(i);
            }
        }
        return null;
    }

    //6.Display All departments by department name
    public Department DisplayAllDepartmentsByName(String departmentName) {
       for (int i = 0; i < departments.size(); i++) {
           if (departments.get(i).getDepartmentName().equals(departmentName)) {
               return departments.get(i);
           }

        }
       return null;
    }


    //7.The department is full when number of instructors equals 30 and number of students equals 300.
    public boolean isFull (String id){
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId().equals(id) && departments.get(i).getNumberOfInstructors()>=30 && departments.get(i).getNumberOfStudents()>=300) {
                departments.get(i).setFull(true);
              return true;
            }
        }
        return false;
    }


    //8.Display All full department
    public ArrayList DisplayAllFullD (){
        ArrayList<Department> fullDepartments = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
           if(departments.get(i).isFull()){
               fullDepartments.add(departments.get(i));

           }
        }
        return fullDepartments;

    }



}
