package br.med.clinic.clinicapi.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "login")
    @Getter
    private String login;
    @Column(name = "senha")
    @Getter
    private String senha;
    @Column(name = "ativo")
    @Getter
    private Boolean active;
    @Column(name = "expirado")
    @Getter
    private Boolean expired;
    @Column(name = "bloqueado")
    @Getter
    private Boolean locked;
    @Column(name = "credencial_expirada")
    @Getter
    private Boolean credentialsExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> perfis = new ArrayList<>();

    public List<Profile> getPerfis() {
        return perfis;
    }

    /**
     * para controlde de acessos/perfis. Implementar no futuro
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }
    /**
     * Implementar no futuro o controle de expiração do usuario. Procurar como implementar a expiração da credencial do usuario
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
