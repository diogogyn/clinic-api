package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.appointment.record.AppointmentDetailsRecord;
import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.service.ScheduleAppointmentService;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private JacksonTester<ScheduleAppointmentRecord> scheduleAppointmentRecordJacksonTester;
    @Autowired
    private JacksonTester<AppointmentDetailsRecord> appointmentDetailsRecordJacksonTester;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleAppointmentService scheduleAppointmentService;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações estao invalidas")
    @WithMockUser
    void schedule_scenario1() throws Exception {
        var response = mvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informações estao validas")
    @WithMockUser
    void schedule_scenario2() throws Exception {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        Speciality speciality = Speciality.CARDIOLOGIA;
        var details = new AppointmentDetailsRecord(null, 1L, 1L, now);
        when(scheduleAppointmentService.schedule(any())).thenReturn(details);
        var response = mvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(scheduleAppointmentRecordJacksonTester.write(
                                new ScheduleAppointmentRecord(1L, 1L, now, speciality)
                        ).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonExpected = appointmentDetailsRecordJacksonTester.write(details).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}