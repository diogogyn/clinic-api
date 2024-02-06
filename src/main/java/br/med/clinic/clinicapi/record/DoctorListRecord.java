package br.med.clinic.clinicapi.record;

import br.med.clinic.clinicapi.entity.Doctor;
import br.med.clinic.clinicapi.enums.Speciality;

public record DoctorListRecord (String name, String email, String crm, Speciality speciality){
    public DoctorListRecord(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
