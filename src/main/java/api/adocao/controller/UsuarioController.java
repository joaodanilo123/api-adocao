package api.adocao.controller;

import api.adocao.controller.dto.UsuarioDTO;
import api.adocao.controller.form.AtualizacaoUsuarioForm;
import api.adocao.controller.form.UsuarioForm;
import api.adocao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrar(@PathVariable Long id){
        return service.mostrar(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){
        return service.cadastrar(form, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid AtualizacaoUsuarioForm form, @PathVariable Long id){
        return service.atualizar(form, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        return service.deletar(id);
    }

}
