package api.adocao.controller;

import api.adocao.controller.dto.InstituicaoDTO;
import api.adocao.entidade.Instituicao;
import api.adocao.repositorio.InstituicaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    InstituicaoRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrar(@PathVariable String id)
    {
        Optional<Instituicao> instituicao = repository.findById(id);
        if(instituicao.isPresent())
        {
            InstituicaoDTO dto = modelMapper.map(instituicao.get(), InstituicaoDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    /*@PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarAnimais(@RequestBody @Valid AnimaisForm form, UriComponentsBuilder uriComponentsBuilder)
    {
        Animais animais = modelMapper.map(form, Animais.class);
        repository.save(animais);

        URI uri = uriComponentsBuilder.path("/animais/{id}").buildAndExpand(animais.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(animais, AnimaisDTO.class));
    }*/



}
