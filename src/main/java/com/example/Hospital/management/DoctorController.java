package com.example.Hospital.management;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;


@RestController //telling spring that this is where end points are written
public class DoctorController {

    HashMap<Integer, Doctor> doctorDb = new HashMap<>();
    @PostMapping ("/addDoctor")
    public String addDoctor(@RequestBody Doctor doctor)
    {
        int key = doctor.getDoctorId();

        doctorDb.put(key, doctor);

        return "Doctor has been successfully added";
    }

    @GetMapping ("/getDoctor")
    public Doctor getDoctor(@RequestParam("doctorId") Integer doctorId)
    {
        Doctor doctor = doctorDb.get(doctorId);

        return doctor;
    }

    @GetMapping("/getDoctorByName")
    public Doctor getDoctorByName(@RequestParam("name") String name)
    {
        for(Doctor d: doctorDb.values())
        {
            if(Objects.equals(d.getName(), name))
            {
                return d;
            }
        }

        return null;
    }
}
