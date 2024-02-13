package br.med.clinic.clinicapi.controller;


import br.med.clinic.clinicapi.domain.appointment.record.AppointmentDetailsRecord;
import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.service.ScheduleAppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;

    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid ScheduleAppointmentRecord record){
        AppointmentDetailsRecord schedule = this.scheduleAppointmentService.schedule(record);
        return ResponseEntity.ok(schedule);
    }
}
