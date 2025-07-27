package com.ChallengeBackEnd.ApiRest.service;

import com.ChallengeBackEnd.ApiRest.Repository.AutorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {
    private final AutorRepository autorRepo;

    public AutenticacionService(AutorRepository autorRepo) {
        this.autorRepo = autorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return autorRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
