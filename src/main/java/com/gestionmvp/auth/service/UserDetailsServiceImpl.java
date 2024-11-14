package com.gestionmvp.auth.service;

import com.gestionmvp.auth.dto.AuthCreateUserRequest;
import com.gestionmvp.auth.dto.AuthLoginRequest;
import com.gestionmvp.auth.dto.AuthResponse;
import com.gestionmvp.auth.persistence.entity.Rol;
import com.gestionmvp.auth.persistence.entity.Usuario;
import com.gestionmvp.auth.persistence.repository.RolRepository;
import com.gestionmvp.auth.persistence.repository.UsuarioRepository;
import com.gestionmvp.auth.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuario.getRoles()
                .forEach(rol -> authorityList.add(new SimpleGrantedAuthority("ROL_".concat(rol.getRoleEnum().name()))));

        usuario.getRoles().stream()
                .flatMap(rol -> rol.getPermisosLista().stream())
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnable(),
                usuario.isAccountNoExpired(),
                usuario.isCredentialNoExpired(),
                usuario.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "Login de usuario exitoso", accessToken, true);

        return  authResponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Username o Password incorrectos");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Password incorrecto");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest){
        String username = authCreateUserRequest.username();
        String password = authCreateUserRequest.password();
        List<String> rolRequest = authCreateUserRequest.rolRequest().rolList();

        Set<Rol> rolSet = rolRepository.findRolByRoleEnumIn(rolRequest).stream().collect(Collectors.toSet());

        if (rolSet.isEmpty()){
            throw new IllegalArgumentException("Los roles especificados no existen");
        }

        Usuario usuario = Usuario.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(rolSet)
                .isEnable(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        Usuario usuarioCreado = usuarioRepository.save(usuario);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuarioCreado.getRoles().forEach(rol -> authorityList.add(new SimpleGrantedAuthority("ROL_".concat(rol.getRoleEnum().name()))));

        usuarioCreado.getRoles()
                .stream()
                .flatMap(rol -> rol.getPermisosLista().stream())
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioCreado.getUsername(), usuarioCreado.getPassword(), authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(usuarioCreado.getUsername(), "Usuario creado exitosamente", accessToken, true);

        return  authResponse;
    }
}
