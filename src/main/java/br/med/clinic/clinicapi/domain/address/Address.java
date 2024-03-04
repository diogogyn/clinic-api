package br.med.clinic.clinicapi.domain.address;

import br.med.clinic.clinicapi.domain.address.record.AddressRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
        this.setComplement(address.complement());
        this.setNumber(address.number());
    }

    public void updateInformation(AddressRecord address) {
        if(address.streetName() != null) this.setStreetName(address.streetName());
        if(address.neighborhood() != null) this.setNeighborhood(address.neighborhood());
        if(address.zipCode() != null) this.setZipCode(address.zipCode());
        if(address.city() != null) this.setCity(address.city());
        if(address.state() != null) this.setState(address.state());
        if(address.complement() != null) this.setComplement(address.complement());
        if(address.number() != null) this.setNumber(address.number());
    }
}
