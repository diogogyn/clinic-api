package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.entity.Doctor;
import br.med.clinic.clinicapi.record.DoctorListRecord;
import br.med.clinic.clinicapi.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DoctorRegisterRecord record) {
        this.doctorRepository.save(new Doctor(record));
    }

    @GetMapping
    public List<DoctorListRecord> listAll(){
        return doctorRepository.findAll().stream().map(DoctorListRecord::new).toList();
    }

}
