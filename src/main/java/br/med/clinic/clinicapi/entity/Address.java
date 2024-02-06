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
    @Column(name = "logradouro")
    String streetName;
    @Column(name = "bairro")
    String neighborhood;
    @Column(name = "cep")
    String zipCode;
    @Column(name = "cidade")
    String city;
    @Column(name = "uf")
    String state;
    @Column(name = "complemento")
    String complement;
    @Column(name = "numero")
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
