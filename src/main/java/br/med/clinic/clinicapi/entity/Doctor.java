package br.med.clinic.clinicapi.entity;

import br.med.clinic.clinicapi.enums.Speciality;
import br.med.clinic.clinicapi.record.DoctorRegisterRecord;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Medico")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "crm")
    private String crm;
    @Column(name = "email")
    private String email;
    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Column(name = "endereco")
    @Embedded
    private Address address;

    public Doctor(DoctorRegisterRecord record) {
        this.setName(record.name());
        this.setEmail(record.email());
        this.setCrm(record.crm());
        this.setSpeciality(record.speciality());
        this.setAddress(new Address(record.address()));
    }
}
