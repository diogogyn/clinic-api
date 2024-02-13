package br.med.clinic.clinicapi.controller;


import br.med.clinic.clinicapi.record.appointment.AppointmentDetailsRecord;
import br.med.clinic.clinicapi.record.appointment.ScheduleAppointmentRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @PostMapping
    public ResponseEntity schedule(ScheduleAppointmentRecord record){

        return ResponseEntity.ok(new AppointmentDetailsRecord(null, null, null, null));
    }
}
