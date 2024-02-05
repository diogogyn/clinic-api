package br.med.clinic.clinicapi.record;

import br.med.clinic.clinicapi.enums.Speciality;

public record DoctorRegisterRecord(String name, String email, String crm, Speciality speciality, AddressRecord address) {
}
