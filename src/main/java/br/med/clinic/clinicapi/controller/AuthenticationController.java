package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.user.User;
import br.med.clinic.clinicapi.domain.user.record.AuthenticationRecord;
import br.med.clinic.clinicapi.infra.security.TokenJWTRecord;
import br.med.clinic.clinicapi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationRecord authenticationRecord){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRecord.login(), authenticationRecord.password());
        Authentication authenticate = this.manager.authenticate(authenticationToken);
        String tokenJWT = this.tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.ok().body(new TokenJWTRecord(tokenJWT));
    }
}
