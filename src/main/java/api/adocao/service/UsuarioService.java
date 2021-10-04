package api.adocao.service;

import api.adocao.config.security.TokenService;
import api.adocao.controller.dto.UsuarioDTO;
import api.adocao.controller.dto.UsuarioDetalhadoDTO;
import api.adocao.controller.form.AtualizacaoUsuarioForm;
import api.adocao.controller.form.UsuarioForm;
import api.adocao.entidade.Usuario;
import api.adocao.repositorio.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    public ResponseEntity<List<UsuarioDTO>> listar(){
        List<UsuarioDTO> dtos = usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());

        if(dtos.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){
        Usuario usuario = modelMapper.map(form, Usuario.class);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(usuario, UsuarioDTO.class));

    }

    public ResponseEntity<?> atualizar(@RequestBody @Valid AtualizacaoUsuarioForm form, String header) {
        Usuario usuario = recuperarUsuarioPorToken(header);

        if(usuario != null){
            usuario.setEmail(form.getEmail());
            usuario.setCpf(form.getCpf());
            usuario.setNome(form.getNome());
            usuario.setTelefone(form.getTelefone());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletar(String header) {
        Usuario usuario = recuperarUsuarioPorToken(header);

        if(usuario != null) {
            usuarioRepository.delete(usuario);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UsuarioDetalhadoDTO> mostrar(String header){
        Usuario usuario = recuperarUsuarioPorToken(header);
        if(usuario != null) {
            UsuarioDetalhadoDTO dto = modelMapper.map(usuario, UsuarioDetalhadoDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }



    private Usuario recuperarUsuarioPorToken(String header){
        String token = tokenService.recuperarTokenDoHeader(header);
        if(tokenService.isTokenValido(token)){
            Long id = tokenService.getIdUsuario(token);
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if(usuario.isPresent()){
                return usuario.get();
            }
        }

        return null;
    }
}
