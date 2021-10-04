package api.adocao.controller;


import api.adocao.controller.dto.PontoDeColetaDTO;
import api.adocao.controller.form.PontoDeColetaForm;
import api.adocao.entidade.PontoDeColeta;
import api.adocao.repositorio.PontoDeColetaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/ponto_de_coleta")
public class PontoDeColetaController {

    @Autowired
    PontoDeColetaRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PontoDeColetaDTO>> list (@PageableDefault(size = 5, page = 0, direction = Sort.Direction.ASC, sort = "nome")Pageable pagina,
                                                        @RequestParam(value = "id", required = false) Long id,
                                                        @RequestParam(value = "nome", required = false) String nome){
        return ResponseEntity.ok(repository.findAll()
                .stream()
                .map(pontoDeColeta -> modelMapper.map(pontoDeColeta, PontoDeColetaDTO.class))
                .collect(Collectors.toList()));
    }
    @GetMapping("/id")
    public ResponseEntity<?> mostrarDetalhado(@PathVariable Long id)
    {
        Optional<PontoDeColeta> ponto = repository.findById(id);
        if(ponto.isPresent())
        {
            PontoDeColetaDTO dto = modelMapper.map(ponto.get(), PontoDeColetaDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PontoDeColetaForm form, UriComponentsBuilder uriComponentsBuilder)
    {
        PontoDeColeta ponto = modelMapper.map(form, PontoDeColeta.class);
        repository.save(ponto);

        URI uri = uriComponentsBuilder.path("ponto_de_coleta/{id}").buildAndExpand(ponto.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(ponto, PontoDeColetaDTO.class));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PontoDeColetaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PontoDeColetaForm form)
    {
        Optional<PontoDeColeta>opt = repository.findById(id);
        if(opt.isPresent()){
            PontoDeColeta ponto = form.atualizar(id, repository);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id)
    {
        Optional<PontoDeColeta> ponto = repository.findById(id);
        if(ponto.isPresent())
        {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
