package br.med.clinic.clinicapi.infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncript {
    public String encriptyPassword(String password){
        BCryptPasswordEncoder encripty = new BCryptPasswordEncoder();
        return encripty.encode(password);
    }
}
