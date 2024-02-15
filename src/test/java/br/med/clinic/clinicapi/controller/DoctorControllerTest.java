package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.address.Address;
import br.med.clinic.clinicapi.domain.address.record.AddressRecord;
import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorDetailsRecord;
import br.med.clinic.clinicapi.domain.doctor.record.DoctorRegisterRecord;
import br.med.clinic.clinicapi.domain.doctor.repository.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorRegisterRecord> doctorRegisterRecordJacksonTester;

    @Autowired
    private JacksonTester<DoctorDetailsRecord> doctorDetailsRecordJacksonTester;

    @MockBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/doctor"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DoctorRegisterRecord(
                "Doctor",
                "medico@voll.med",
                "61999999999",
                "123456",
                Speciality.CARDIOLOGIA,
                addressRecord());

        when(repository.save(any())).thenReturn(new Doctor(dadosCadastro));

        var response = mvc
                .perform(post("/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorRegisterRecordJacksonTester.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DoctorDetailsRecord(
                null,
                dadosCadastro.name(),
                dadosCadastro.email(),
                dadosCadastro.phone(),
                dadosCadastro.crm(),
                dadosCadastro.speciality(),
                new Address(dadosCadastro.address()),
                true
        );
        var jsonEsperado = doctorDetailsRecordJacksonTester.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private AddressRecord addressRecord() {
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