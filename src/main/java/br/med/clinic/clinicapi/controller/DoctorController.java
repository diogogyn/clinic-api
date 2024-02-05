package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.entity.Doctor;
import br.med.clinic.clinicapi.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    public void create(@RequestBody DoctorRegisterRecord record) {

        doctorRepository.save(new Doctor(record));
        //System.out.println(dados);
    }

}
