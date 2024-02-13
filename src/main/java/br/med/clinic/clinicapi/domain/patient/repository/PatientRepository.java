package br.med.clinic.clinicapi.domain.patient.repository;

import br.med.clinic.clinicapi.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable paginacao);

    @Query("""
            SELECT m.active FROM Paciente
            WHERE m.id = :id
            """)
    Boolean findActiveById(Long aLong);
}
