package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.patient.Patient;
import br.med.clinic.clinicapi.domain.patient.record.PatientDetailsRecord;
import br.med.clinic.clinicapi.domain.patient.record.PatientListRecord;
import br.med.clinic.clinicapi.domain.patient.record.PatientRegisterRecord;
import br.med.clinic.clinicapi.domain.patient.record.PatientUpdateRecord;
import br.med.clinic.clinicapi.domain.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("patient")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegisterRecord record, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(record);
        repository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsRecord(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListRecord>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
        var page = repository.findAllByActiveTrue(paginacao).map(PatientListRecord::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateRecord record) {
        var patient = repository.getReferenceById(record.id());
        patient.atualizarInformacoes(record);

        return ResponseEntity.ok(new PatientDetailsRecord(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity softDelete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsRecord(patient));
    }

}
