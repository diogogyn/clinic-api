package br.med.clinic.clinicapi.domain.doctor.record;

import br.med.clinic.clinicapi.domain.address.Address;
import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;

public record DoctorDetailsRecord(Long id, String name, String email, String phone, String crm, Speciality speciality, Address address, Boolean active) {
    public DoctorDetailsRecord (Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpeciality(), doctor.getAddress(), doctor.getActive());
    }
}
