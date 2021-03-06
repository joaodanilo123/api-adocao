package api.adocao.controller;
import api.adocao.controller.dto.AnimalDTO;
import api.adocao.controller.dto.PontoDeColetaDTO;
import api.adocao.controller.form.AnimalForm;
import api.adocao.entidade.Animal;
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
    public ResponseEntity<List<AnimalDTO>> list (@PageableDefault(size = 5, page = 0, direction = Sort.Direction.ASC, sort = "nome")Pageable pagina,
                                                        @RequestParam(value = "id", required = false) Long id,
                                                        @RequestParam(value = "nome", required = false) String nome,
                                                        @RequestParam(value = "especie", required = false) String especie,
                                                        @RequestParam(value = "raca", required = false) String raca,
                                                        @RequestParam(value = "genero", required = false) String genero,
                                                        @RequestParam(value = "instituicao", required = false) Long instituicao){
        return ResponseEntity.ok(animalRepository.findAll()
                .stream()
                .map(animal -> modelMapper.map(animal, AnimalDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable @Valid Long id)
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

        //setando o ID pra null pois o modelMapper tamb??m atribui o instituicaoId pro id do animal, sobrescrevendo algum animal que j?? existe
        animal.setId(null);
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
            return ResponseEntity.ok(modelMapper.map(animal, AnimalDTO.class));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity remover(@PathVariable @Valid Long id){
        Optional<Animal> optional = animalRepository.findById(id);
        if (optional.isPresent()) {
            animalRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

}
