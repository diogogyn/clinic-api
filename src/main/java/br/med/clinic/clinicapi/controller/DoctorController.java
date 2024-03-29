package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorDetailsRecord;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorListRecord;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorUpdateRecord;
import br.med.clinic.clinicapi.domain.doctor.repository.DoctorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    @Transactional
    @CacheEvict(value = "listAllDoctors", allEntries = true)
    public ResponseEntity create(@RequestBody @Valid DoctorRegisterRecord record, UriComponentsBuilder uriBuilder) {
        Doctor save = this.doctorRepository.save(new Doctor(record));
        URI uri = uriBuilder.path("/doctor/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailsRecord(save));
    }

    @GetMapping
    @Cacheable(value = "listAllDoctors")
    public ResponseEntity<Page<DoctorListRecord>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        Page<DoctorListRecord> pageResult = this.doctorRepository.findAllByActiveTrue(page).map(DoctorListRecord::new);
        return ResponseEntity.ok(pageResult);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity listOne(@PathVariable Long id){
        Doctor doctor = this.doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsRecord(doctor));
    }
    @PutMapping
    @Transactional
    @CacheEvict(value = "listAllDoctors", allEntries = true)
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateRecord record){
        Doctor doctor = this.doctorRepository.getReferenceById(record.id());
        doctor.updateInformation(record);

        return ResponseEntity.ok(new DoctorDetailsRecord(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listAllDoctors", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id){
        Doctor byId = this.doctorRepository.getReferenceById(id);
        byId.delete();
        return ResponseEntity.noContent().build();
    }
}
