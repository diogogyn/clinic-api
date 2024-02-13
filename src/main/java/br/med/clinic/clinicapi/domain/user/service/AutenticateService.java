package br.med.clinic.clinicapi.domain.user.service;

import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticateService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * https://www.youtube.com/watch?v=zNNvdPUR3-o
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*UserDetails byLogin = this.userRepository.findByLogin(username);
        if(byLogin != null && byLogin.isEnabled()){
            Set<GrantedAuthority> profiles = new HashSet<>();
            for(int i = 0; i<5;i++){
                GrantedAuthority ga = new SimpleGrantedAuthority("ADMIN");
                profiles.add(ga);
            }
            User user = new User(1L, byLogin.getUsername(), byLogin.getPassword());
            return user;
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado ou inativo");
        }*/
        return this.userRepository.findByLogin(username);
    }
}
