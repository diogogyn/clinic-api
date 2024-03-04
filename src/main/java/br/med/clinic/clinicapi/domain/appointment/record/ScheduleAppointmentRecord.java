package br.med.clinic.clinicapi.domain.appointment.record;

import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleAppointmentRecord(
        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,
        Speciality speciality
) {
}
