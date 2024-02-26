package br.med.clinic.clinicapi.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "perfis")
@Entity(name = "Perfis")
public class Profile implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}
