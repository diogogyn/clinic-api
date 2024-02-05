package br.med.clinic.clinicapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class Address {
    String streetName;
    String neighborhood;
    String zipCode;
    String city;
    String state;
    String complement;
    String number;
}
