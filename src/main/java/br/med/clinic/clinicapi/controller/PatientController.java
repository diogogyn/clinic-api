package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.patient.Patient;
import br.med.clinic.clinicapi.record.patient.PatientDetailsRecord;
import br.med.clinic.clinicapi.record.patient.PatientListRecord;
import br.med.clinic.clinicapi.record.patient.PatientRegisterRecord;
import br.med.clinic.clinicapi.record.patient.PatientUpdateRecord;
import br.med.clinic.clinicapi.repository.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PatientRegisterRecord dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Patient(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsRecord(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListRecord>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByActiveTrue(paginacao).map(PatientListRecord::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PatientUpdateRecord dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new PatientDetailsRecord(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsRecord(paciente));
    }

}
