package api.adocao.controller;

import api.adocao.controller.dto.RequisicaoDTO;
import api.adocao.controller.form.MudarStatusRequisicaoForm;
import api.adocao.controller.form.RequisicaoForm;
import api.adocao.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {

    @Autowired
    RequisicaoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<RequisicaoDTO> criar(@RequestBody @Valid RequisicaoForm form, UriComponentsBuilder uri){
        return service.criar(form, uri);
    }

    @PutMapping("/status")
    @Transactional
    public ResponseEntity<?> mudarStatus(@RequestBody @Valid MudarStatusRequisicaoForm form){
        return service.mudarStatus(form);
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoDTO>> listar(){
        return service.listar();
    }

}
