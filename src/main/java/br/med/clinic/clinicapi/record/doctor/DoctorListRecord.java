package br.med.clinic.clinicapi.record.doctor;

import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.enums.Speciality;

public record DoctorListRecord (Long id, String name, String email, String crm, Speciality speciality, Boolean active){
    public DoctorListRecord(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality(), doctor.getActive());
    }
}
