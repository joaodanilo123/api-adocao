package api.adocao.controller;

import api.adocao.controller.dto.InventarioDTO;
import api.adocao.controller.form.InventarioForm;
import api.adocao.entidade.Inventario;
import api.adocao.entidade.PontoDeColeta;
import api.adocao.repositorio.InstituicaoRepository;
import api.adocao.repositorio.InventarioRepository;
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
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioRepository repository;

    @Autowired
    InstituicaoRepository instituicaoRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> list(@PageableDefault(size = 5, page = 0, direction = Sort.Direction.ASC, sort = "id") Pageable pagina,
                                                    @RequestParam(value = "id", required = false) Long id) {
        return ResponseEntity.ok(repository.findAll()
                .stream()
                .map(inventario -> modelMapper.map(inventario, InventarioDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarDetalhado(@PathVariable Long id) {
        Optional<Inventario> inventario = repository.findById(id);
        if (inventario.isPresent()) {
            InventarioDTO dto = modelMapper.map(inventario.get(), InventarioDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid InventarioForm form, UriComponentsBuilder uriComponentsBuilder) {
        Inventario inventario = new Inventario();
        inventario.setItem(form.getItem());
        inventario.setInstituicao(instituicaoRepository.getById(form.getInstituicaoId()));
        repository.save(inventario);

        URI uri = uriComponentsBuilder.path("inventario/id").buildAndExpand(inventario.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(inventario, InventarioDTO.class));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<InventarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid InventarioForm form)
    {
        Optional<Inventario> inventario = repository.findById(id);
        if (inventario.isPresent()) {
            Inventario invent = form.atualizar(id, repository);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id)
    {
        Optional<Inventario> inventario = repository.findById(id);
        if(inventario.isPresent())
        {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
