package com.example.Hospital.management;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository //this is a repository layer whose task is to send the raw data
public class NurseRepository {

    //this is a class that will contain database information (hashmap, mysql)

    HashMap<Integer, Nurse> nurseDB = new HashMap<>();
    public String addNurse(Nurse nurse){

        int key = nurse.getNurseId();

        nurseDB.put(key, nurse);

        return "Nurse added Successfully";
    }

    public List<Nurse> getAllNurses()
    {
          return nurseDB.values().stream().toList(); //converting the nurse type object to list
    }

}
