package api.adocao.controller;

import api.adocao.controller.dto.InstituicaoDTO;
import api.adocao.controller.form.InstituicaoForm;
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
    public ResponseEntity<?> mostrar(@PathVariable Long id)
    {
        Optional<Instituicao> instituicao = repository.findById(id);
        if(instituicao.isPresent())
        {
            InstituicaoDTO dto = modelMapper.map(instituicao.get(), InstituicaoDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid InstituicaoForm form, UriComponentsBuilder uriComponentsBuilder)
    {
        Instituicao instituicao = modelMapper.map(form, Instituicao.class);
        repository.save(instituicao);

        URI uri = uriComponentsBuilder.path("/animais/{id}").buildAndExpand(instituicao.getCnpj()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(instituicao, InstituicaoDTO.class));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<InstituicaoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid InstituicaoForm form){
        Optional <Instituicao> optional = repository.findById(id);
        if (optional.isPresent()) {
            Instituicao instituicao = form.atualizar(id, repository);
            return ResponseEntity.ok(new InstituicaoDTO(instituicao));
        }
        return ResponseEntity.notFound().build();
    }





    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        Optional<Instituicao> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
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
