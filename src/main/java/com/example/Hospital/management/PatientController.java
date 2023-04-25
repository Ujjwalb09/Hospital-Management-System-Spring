package com.example.Hospital.management;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/patient") //this patient end-point has added itself as a prefix in front of all end points we have written
public class PatientController {

    HashMap<Integer, Patient> patientDb = new HashMap<>();
    @PostMapping("/addViaParameters") //This is a 'Create' request. telling spring we are creating patient here via request parameters
    public String addPatient(@RequestParam("patientId")Integer patientId, @RequestParam("name")String name,
                             @RequestParam("age")Integer age, @RequestParam("disease")String disease)
    {
          Patient patient  = new Patient(patientId, name, disease, age); //object creation

          patientDb.put(patientId, patient);

          return "Patient added successfully";
    }

    @PostMapping("/addViaRequestBody")
    public String addPatient(@RequestBody Patient patient) //creating patient via request body
    {
        int key = patient.getPatientID();

        patientDb.put(key, patient);

        return "Patient added via Request Body";
    }

    @GetMapping("/get")
    public Patient getPatient(@RequestParam("patientId") Integer patientId)
    {

        return patientDb.get(patientId);
    }

  @GetMapping("/getPatients/{age}/{disease}")
  public List<Patient> getPatients(@PathVariable("age") Integer age, @PathVariable("disease") String disease)
  {
      List<Patient> patients = new ArrayList<>();

      for(Patient p: patientDb.values())
      {
          if(p.getAge() > age && Objects.equals(p.getDisease(), disease))
          {
              patients.add(p);
          }
      }

      return patients;
  }

   @GetMapping("/getInfoViaPathVariable/{patientId}")
   public Patient getPatientInfo(@PathVariable("patientId") Integer patientId){
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



    @PutMapping ("/updateDisease")
    public String updateDisease(@RequestParam("patientId") Integer patientId, @RequestParam("disease") String disease) {

        if(patientDb.containsKey(patientId)) //checking if patient id exists in our database
        {
            Patient patient = patientDb.get(patientId); //getting patient object with patient ID from database
            patient.setDisease(disease); //calling the setter function to update the disease
            patientDb.put(patientId, patient); //updating the patient object in database

            return "Updated Succesfully";
        } else
        {
            return "Patient do no exist";
        }
    }
    @PutMapping ("/updatePatientDetails")
    public String updatePatientDetails(@RequestBody Patient patient)
    {
        int key = patient.getPatientID();

        if(patientDb.containsKey(key)) {
            patientDb.put(key, patient);

            return "Patient has been updated";

        }

        else {
            return "Data do no exist";
        }
    }

    @DeleteMapping ("/deletePatient")
    public String deletePatient(@RequestParam("patientId") Integer patientId) {
        patientDb.remove(patientId);

        return "Patient deleted successfully";
    }


}
