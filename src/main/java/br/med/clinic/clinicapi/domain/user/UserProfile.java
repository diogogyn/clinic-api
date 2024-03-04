package br.med.clinic.clinicapi.domain.user;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "usuarios_perfis")
@Entity(name = "UsuarioPerfis")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "usuario_id")
    Long userId;
    @Column(name = "perfis_id")
    Long profileId;
}
