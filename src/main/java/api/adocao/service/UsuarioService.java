package api.adocao.service;

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
import org.springframework.web.util.UriComponentsBuilder;

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

    public ResponseEntity<List<UsuarioDTO>> listar(){
        List<UsuarioDTO> dtos = usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());

        if(dtos.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<UsuarioDetalhadoDTO> mostrar(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isPresent() ? ResponseEntity.ok(modelMapper.map(usuario.get(), UsuarioDetalhadoDTO.class)) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){
        Usuario usuario = modelMapper.map(form, Usuario.class);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(usuario, UsuarioDTO.class));

    }

    public ResponseEntity<?> atualizar(@RequestBody @Valid AtualizacaoUsuarioForm form, Long id) {
        Optional<Usuario> usuarioBusca = usuarioRepository.findById(id);

        if(usuarioBusca.isPresent()){
            Usuario usuario = usuarioBusca.get();

            usuario.setEmail(form.getEmail());
            usuario.setCpf(form.getCpf());
            usuario.setNome(form.getNome());
            usuario.setTelefone(form.getTelefone());

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deletar(Long id) {
        Optional<Usuario> usuarioBusca = usuarioRepository.findById(id);

        if(usuarioBusca.isPresent()) {
            usuarioRepository.delete(usuarioBusca.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
