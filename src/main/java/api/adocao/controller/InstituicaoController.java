package api.adocao.controller;

import api.adocao.controller.dto.InstituicaoDTO;
import api.adocao.controller.form.InstituicaoForm;
import api.adocao.entidade.Instituicao;
import api.adocao.repositorio.InstituicaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    InstituicaoRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<InstituicaoDTO>> list (@PageableDefault(size = 5, page = 0, direction = Sort.Direction.ASC, sort= "nome") Pageable pagina,
                                                      @RequestParam(value = "id", required = false) Long id,
                                                      @RequestParam(value = "nome", required = false) String nome){
        return ResponseEntity.ok(repository.findAll().stream().map(instituicao -> modelMapper.map(instituicao, InstituicaoDTO.class)).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarDetalhado(@PathVariable Long id)
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
                                                //correção dessa merda de novo pq tava bugado sei la porque mas tava animais e telefone
        URI uri = uriComponentsBuilder.path("/instituicao/{id}").buildAndExpand(instituicao.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(instituicao, InstituicaoDTO.class));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<InstituicaoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid InstituicaoForm form){
        Optional <Instituicao> optional = repository.findById(id);
        if (optional.isPresent()) {
            Instituicao instituicao = form.atualizar(id, repository);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        Optional<Instituicao> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }
}
