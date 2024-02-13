package br.med.clinic.clinicapi.domain.appointment.repository;

import br.med.clinic.clinicapi.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndDate(Long aLong, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long aLong, LocalDateTime firstTime, LocalDateTime lastTime);
}
