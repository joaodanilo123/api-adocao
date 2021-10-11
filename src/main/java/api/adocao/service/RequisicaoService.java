package api.adocao.service;

import api.adocao.controller.dto.RequisicaoDTO;
import api.adocao.controller.form.MudarStatusRequisicaoForm;
import api.adocao.controller.form.RequisicaoForm;
import api.adocao.entidade.Animal;
import api.adocao.entidade.Requisicao;
import api.adocao.entidade.StatusRequisicaoEnum;
import api.adocao.entidade.Usuario;
import api.adocao.repositorio.AnimalRepository;
import api.adocao.repositorio.RequisicaoRepository;
import api.adocao.repositorio.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequisicaoService {

    @Autowired
    RequisicaoRepository repository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<RequisicaoDTO>> listar() {
        List<Requisicao> requisicoes = repository.findAll();
        List<RequisicaoDTO> dtos = requisicoes.stream().map(req -> modelMapper.map(req, RequisicaoDTO.class)).collect(Collectors.toList());
        if(dtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<RequisicaoDTO> criar(RequisicaoForm form, UriComponentsBuilder uriBuilder){
        Requisicao requisicao = new Requisicao();

        Optional<Animal> animal = animalRepository.findById(form.getAnimalId());
        Optional<Usuario> usuario = usuarioRepository.findById(form.getUsuarioId());

        if(animal.isPresent() && usuario.isPresent()){
            requisicao.setAnimal(animal.get());
            requisicao.setUsuario(usuario.get());

            repository.save(requisicao);
            RequisicaoDTO dto = modelMapper.map(requisicao, RequisicaoDTO.class);

            URI uri = uriBuilder.path("/requisicao/{id}").buildAndExpand(requisicao.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }

        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity<?> mudarStatus(MudarStatusRequisicaoForm form){
        Requisicao requisicao = buscarRequisicao(form.getRequisicaoId());
        if(requisicao != null && StatusRequisicaoEnum.isValid(form.getStatus())) {
            StatusRequisicaoEnum novoStatus = StatusRequisicaoEnum.valueOf(form.getStatus());
            requisicao.setStatus(novoStatus);

            if(novoStatus != StatusRequisicaoEnum.PENDENTE && novoStatus != StatusRequisicaoEnum.EM_ANALISE){
                requisicao.setDataDeFechamento(LocalDate.now());
            }


            return ResponseEntity.ok().build();
        } else if(requisicao == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private Requisicao buscarRequisicao(Long id){
        Optional<Requisicao> requisicao = repository.findById(id);
        return requisicao.orElse(null);
    }


}
