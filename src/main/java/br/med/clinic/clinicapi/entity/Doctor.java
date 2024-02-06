package br.med.clinic.clinicapi.entity;

import br.med.clinic.clinicapi.enums.Speciality;
import br.med.clinic.clinicapi.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.record.DoctorUpdateRecord;
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
    @Column(name = "telefone")
    private String phone;
    @Column(name = "ativo")
    private Boolean active;

    public Doctor(DoctorRegisterRecord record) {
        this.setName(record.name());
        this.setEmail(record.email());
        this.setCrm(record.crm());
        this.setSpeciality(record.speciality());
        this.setAddress(new Address(record.address()));
        this.setPhone(record.phone());
        this.setActive(true);
    }

    public void updateInformation(DoctorUpdateRecord record) {
        if(record.name() != null) this.setName(record.name());
        if(record.phone() != null) this.setPhone(record.phone());
        if(record.address() != null) this.address.updateInformation(record.address());
    }

    public void delete() {
        this.setActive(false);
    }
}
