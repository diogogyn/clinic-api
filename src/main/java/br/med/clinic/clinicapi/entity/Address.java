package br.med.clinic.clinicapi.entity;

import br.med.clinic.clinicapi.record.AddressRecord;
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

    public Address(AddressRecord address) {
        this.setStreetName(address.streetName());
        this.setNeighborhood(address.neighborhood());
        this.setZipCode(address.zipCode());
        this.setCity(address.city());
        this.setState(address.state());
        this.setCity(address.city());
        this.setComplement(address.complement());
        this.setNumber(address.number());
    }
}
