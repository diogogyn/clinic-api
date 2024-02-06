package br.med.clinic.clinicapi.record;

import br.med.clinic.clinicapi.entity.Address;
import br.med.clinic.clinicapi.entity.Doctor;
import br.med.clinic.clinicapi.enums.Speciality;

public record DoctorDetailsRecord(Long id, String name, String email, String phone, String crm, Speciality speciality, Address address, Boolean active) {
    public DoctorDetailsRecord (Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpeciality(), doctor.getAddress(), doctor.getActive());
    }
}
