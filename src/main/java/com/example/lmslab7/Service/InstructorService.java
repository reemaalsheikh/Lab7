package com.example.lmslab7.Service;
import com.example.lmslab7.Model.Instructor;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;

@Service
public class InstructorService {

    ArrayList <Instructor> instructors = new ArrayList<>();



    //1.Get all Instructors
    public ArrayList<Instructor> getAllInstructors () {
        return instructors;
    }
    //2.Add new Instructor
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    //3.update instructor
    public boolean updateInstructor (int id, Instructor instructor) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                instructors.set(i, instructor);
                return true;
            }
        }
        return false;
    }

    //4.delete Instructor
    public boolean deleteInstructor (int id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                instructors.remove(i);
                return true;
            }
        }
        return false;
    }

    //5.check In and Out Instructors.
    public boolean checkInOut (int id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                instructors.get(i).setCheckedin(true);
                instructors.get(i).setCheckedout(true);
                return true;

            }
        }
        return false;
    }

    //6.Display All checked in and checked out Instructors
    public ArrayList<Instructor> CheckedInOutInstructors () {
    ArrayList<Instructor> checkedInOutInstructors = new ArrayList<>();
    for (int i = 0; i < instructors.size(); i++) {
        if(instructors.get(i).isCheckedin() && instructors.get(i).isCheckedout()){
            checkedInOutInstructors.add(instructors.get(i));

        }
    }
    return checkedInOutInstructors;
    }

    //7.Serach Instructor by Id
    public Instructor InstructorById (int id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                return instructors.get(i);
            }
        }
        return null;
    }


    //8.Take the absence days and return all instructor salary with bonus (if absence days =0)
    // Verify that the instructor exists.
    public Instructor ChangeSalary (int id) {

        for (int i = 0; i < instructors.size(); i++) {
            Instructor instructor = instructors.get(i);
            if (instructor.getId()== id) {
                if (instructor.getAbsenceDays()==0){

                    double previousSalary = instructor.getSalary();
                    double bonus =  (previousSalary * (0.10));
                    double currentSalary = (previousSalary + bonus );
                    instructor.setSalary(currentSalary);
                    return instructor;

                }
            }
        }
        return null;


    }


    //9.Promote Instructor: Allows a professor to promote the Teacher Assistant.
    // Verify that the instructor with the specified ID exists.
    // Ensure that the requester is a professor.
    // Validate that the instructor years of experience is 8 or more.
    // Change the instructor position to "Professor" if they meet the criteria.

    public ArrayList<Instructor> promoteInstructor (int id1, int id2) {
        ArrayList<Instructor> promotion =new ArrayList<>();

        for (int i = 0; i < instructors.size(); i++) {
            Instructor instructor = instructors.get(i);
            if ( instructor.getId() == id1 && instructor.getPosition().equalsIgnoreCase("Professor")){

         for (Instructor ins: instructors) {
             if (ins.getId()== id2 && ins.getPosition().equalsIgnoreCase("Teacher Assistant") && ins.getYearsOfExperience()>=8) {
                 ins.setPosition("Professor");
                 promotion.add(ins);
             }}

            }

        }
        return promotion;
    }




}
