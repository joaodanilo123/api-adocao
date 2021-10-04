package api.adocao.controller;

import api.adocao.controller.dto.UsuarioDTO;
import api.adocao.controller.dto.UsuarioDetalhadoDTO;
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

    @GetMapping("/detalhes")
    public ResponseEntity<UsuarioDetalhadoDTO> mostrar(@RequestHeader(name = "Authorization") String header){
        return service.mostrar(header);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){
        return service.cadastrar(form, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid AtualizacaoUsuarioForm form, @RequestHeader(name = "Authorization") String header){
        return service.atualizar(form, header);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletar(@RequestHeader String header){
        return service.deletar(header);
    }

}
