package api.adocao.controller;
import api.adocao.controller.dto.AnimalDTO;
import api.adocao.controller.form.AnimalForm;
import api.adocao.entidade.Animal;
import api.adocao.entidade.Instituicao;
import api.adocao.repositorio.AnimalRepository;
import api.adocao.repositorio.InstituicaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public Page<AnimalDTO> listarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){
        Page<Animal> pageable = this.animalRepository.findAll(paginacao);
        List<AnimalDTO> states = pageable.getContent()
                .stream()
                .map(animal -> modelMapper.map(animal, AnimalDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<AnimalDTO>(states, paginacao, pageable.getTotalElements());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id)
    {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isPresent())
        {
            AnimalDTO dto = modelMapper.map(animal.get(), AnimalDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AnimalForm form, UriComponentsBuilder uriBuilder){
        Animal animal = modelMapper.map(form, Animal.class);

        animalRepository.save(animal);

        URI uri = uriBuilder.path("/animais/{id}").buildAndExpand(animal.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(animal, AnimalDTO.class));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AnimalDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AnimalForm form){
        Optional<Animal> optional = animalRepository.findById(id);
        if (optional.isPresent()) {
            Animal animal = form.atualizar(id, animalRepository);
            return ResponseEntity.ok(new AnimalDTO(animal));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        Optional<Animal> optional = animalRepository.findById(id);
        if (optional.isPresent()) {
            animalRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

}
