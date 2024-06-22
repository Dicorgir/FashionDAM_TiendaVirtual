package com.jarellano.security;

import com.jarellano.entities.Empleado;
import com.jarellano.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpSession session;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empleado> empleadoOptional = empleadoService.findEmpleadoByDni(username);
        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();

            // Check if the user is under 18
            LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
            if (empleado.getFechaNacimiento().isAfter(eighteenYearsAgo)) {
                throw new UsernameNotFoundException("¡Empleado menor de 18 años!");
            }

            session.setAttribute("sid_empleado", empleado.getIdEmpleado());
            return User.builder()
                    .username(empleado.getDni())
                    .password(empleado.getPassword())
                    .roles()
                    .build();
        } else {
            throw new UsernameNotFoundException("¡Empleado no encontrado!");
        }
    }
}