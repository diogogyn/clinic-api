package br.med.clinic.clinicapi.entity;

import br.med.clinic.clinicapi.enums.Speciality;
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
    @Column(name = "nome")
    private String crm;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;
}
