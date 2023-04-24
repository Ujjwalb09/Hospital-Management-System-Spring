package com.example.Hospital.management;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class PatientController {

    HashMap<Integer, Patient> patientDb = new HashMap<>();
    @PostMapping("/addPatientViaParameters") //This is a 'Create' request. telling spring we are creating patient here via request parameters
    public String addPatient(@RequestParam("patientId")Integer patientId, @RequestParam("name")String name,
                             @RequestParam("age")Integer age, @RequestParam("disease")String disease)
    {
          Patient patient  = new Patient(patientId, name, disease, age); //object creation

          patientDb.put(patientId, patient);

          return "Patient added successfully";
    }

    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient) //creating patient via request body
    {
        int key = patient.getPatientID();

        patientDb.put(key, patient);

        return "Patient added via Request Body";
    }

    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId") Integer patientId)
    {
        Patient patient = patientDb.get(patientId);

        return patient;
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients()
    {
        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDb.values())
        {
            patients.add(p);
        }

        return patients;
    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name") String name)
    {
        for(Patient p: patientDb.values()){
            if(Objects.equals(p.getName(), name)){
                return p;
            }
        }

        return null;
    }

    @GetMapping("/getPatientGreaterThanAge")
    public List<Patient> getPatientGreaterThanAge(@RequestParam("age") Integer age)
    {
        List<Patient> patientList = new ArrayList<>();

        for(Patient p: patientDb.values())
        {
            if(p.getAge() > age){
                patientList.add(p);
            }
        }

        return patientList;
    }
}
