package br.med.clinic.clinicapi.record;

import br.med.clinic.clinicapi.domain.Doctor;
import br.med.clinic.clinicapi.enums.Speciality;

public record DoctorListRecord (Long id, String name, String email, String crm, Speciality speciality, Boolean active){
    public DoctorListRecord(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality(), doctor.getActive());
    }
}
