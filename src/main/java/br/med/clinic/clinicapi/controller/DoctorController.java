package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.record.DoctorRegisterRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
public class DoctorController {
    @PostMapping
    public void create(@RequestBody DoctorRegisterRecord dados) {
        System.out.println(dados);
    }

}
