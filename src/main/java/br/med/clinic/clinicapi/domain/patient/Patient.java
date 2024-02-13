package br.med.clinic.clinicapi.domain.patient;

import br.med.clinic.clinicapi.domain.address.Address;
import br.med.clinic.clinicapi.record.patient.PatientRegisterRecord;
import br.med.clinic.clinicapi.record.patient.PatientUpdateRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String phone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientRegisterRecord dados) {
        this.active = true;
        this.name = dados.name();
        this.email = dados.email();
        this.phone = dados.phone();
        this.cpf = dados.cpf();
        this.address = new Address(dados.address());
    }

    public void atualizarInformacoes(PatientUpdateRecord dados) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.phone() != null) {
            this.phone = dados.phone();
        }
        if (dados.address() != null) {
            this.address.updateInformation(dados.address());
        }

    }

    public void excluir() {
        this.active = false;
    }
}
