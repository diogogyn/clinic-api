package br.med.clinic.clinicapi.http;


//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

import br.med.clinic.clinicapi.domain.http.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.name}", url = "${api.viacep.url}")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json")
    ViaCepResponse getAddress(@PathVariable String cep);
}
