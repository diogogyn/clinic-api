package br.med.clinic.clinicapi.domain.doctor.repository;

import br.med.clinic.clinicapi.domain.address.record.AddressRecord;
import br.med.clinic.clinicapi.domain.appointment.Appointment;
import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.domain.patient.Patient;
import br.med.clinic.clinicapi.domain.patient.record.PatientRegisterRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    TestEntityManager em;
    @Autowired
    private DoctorRepository doctorRepository;
    @Test
    @DisplayName("Deveria devolver null quando unico doctor cadastrado não está disponivel na data")
    void chooseDoctorRandomFreeOnDate() {
        LocalDateTime nextDate = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Doctor medico = cadastrarDoctor("medico", "medico@email.com", "12345", Speciality.CARDIOLOGIA);
        Patient patient = cadastrarPatient("Paciente", "paciente@email.com", "00000000000");
        cadastrarAppointment(medico, patient, nextDate);
        Doctor doctor = this.doctorRepository.chooseDoctorRandomFreeOnDate(Speciality.CARDIOLOGIA, nextDate);
        assertThat(doctor).isNull();
    }
    @Test
    @DisplayName("Deveria devolver medico quando estiver disponivel na data")
    void chooseDoctorRandomFreeOnDate2() {
        LocalDateTime nextDate = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Doctor medico = cadastrarDoctor("medico", "medico@email.com", "12345", Speciality.CARDIOLOGIA);

        Doctor doctor = this.doctorRepository.chooseDoctorRandomFreeOnDate(Speciality.CARDIOLOGIA, nextDate);
        assertThat(doctor).isEqualTo(medico);
    }
    private void cadastrarAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        em.persist(new Appointment(null, doctor, patient, data));
    }

    private Doctor cadastrarDoctor(String nome, String email, String crm, Speciality especialidade) {
        var doctor = new Doctor(dadosDoctor(nome, email, crm, especialidade));
        em.persist(doctor);
        return doctor;
    }

    private Patient cadastrarPatient(String nome, String email, String cpf) {
        var patient = new Patient(dadosPatient(nome, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorRegisterRecord dadosDoctor(String nome, String email, String crm, Speciality especialidade) {
        return new DoctorRegisterRecord(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PatientRegisterRecord dadosPatient(String nome, String email, String cpf) {
        return new PatientRegisterRecord(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private AddressRecord dadosEndereco() {
        return new AddressRecord(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}