package com.example.Hospital.management;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service //this is a service layer where we apply all the business logic
public class NurseService {

    NurseRepository nurseRepository = new NurseRepository();
     public String addNurse(Nurse nurse){

           if(nurse.getNurseId() < 0){
               return "Enter Valid Nurse ID";
           }

           if(nurse.getName() == null){
               return "Name should not be null";
           }

           String ans = nurseRepository.addNurse(nurse);
           return ans;
     }

     public List<Nurse> getList(int age)
     {
         //calling all the nurses from database

         List<Nurse> nurses = nurseRepository.getAllNurses();

         //now I am writing the logic to extract required information

         List<Nurse> finalList = new ArrayList<>();

         for(Nurse nurse: nurses){
             if(nurse.getAge() > age){
                 finalList.add(nurse);
             }
         }

         return finalList;
     }

     public List<Nurse> getNursesWithQualification(String qualification)
     {
         //calling all the nurses from the database
         List<Nurse> nurseList = nurseRepository.getAllNurses();

         //now writing logic to extract nurse based on qualification

         List<Nurse> finalList = new ArrayList<>();

         for(Nurse nurse : nurseList)
         {
             if(Objects.equals(nurse.getQualification(), qualification))
             {
                 finalList.add(nurse);
             }
         }

         return finalList;
     }
}
