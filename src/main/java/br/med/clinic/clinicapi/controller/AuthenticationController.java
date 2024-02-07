package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.user.AuthenticationRecord;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationRecord authenticationRecord){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticationRecord.login(), authenticationRecord.senha());
        Authentication authenticate = this.manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
