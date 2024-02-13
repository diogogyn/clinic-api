package br.med.clinic.clinicapi.domain.appointment.repository;

import br.med.clinic.clinicapi.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
