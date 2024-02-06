package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.entity.Doctor;
import br.med.clinic.clinicapi.record.DoctorListRecord;
import br.med.clinic.clinicapi.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.record.DoctorUpdateRecord;
import br.med.clinic.clinicapi.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Page<DoctorListRecord> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        return this.doctorRepository.findAllByActiveTrue(page).map(DoctorListRecord::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateRecord record){
        Doctor byId = this.doctorRepository.getReferenceById(record.id());
        byId.updateInformation(record);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        Doctor byId = this.doctorRepository.getReferenceById(id);
        byId.delete();
    }
}
