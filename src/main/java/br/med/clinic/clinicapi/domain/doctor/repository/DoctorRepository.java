package br.med.clinic.clinicapi.domain.doctor.repository;

import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable page);

    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.active = true
            AND
            m.speciality =:speciality
            AND
            m.id not in(
                SELECT c.doctor.id FROM Appointment c
                WHERE
                c.date =:data
            )
            ORDER BY rand()
            LIMIT 1
            """)
    Doctor chooseDoctorRandomFreeOnDate(Speciality speciality, LocalDateTime date);

    @Query("""
            SELECT m.active FROM Medico
            WHERE m.id = :id
            """)
    Boolean findActiveById(Long aLong);
}
