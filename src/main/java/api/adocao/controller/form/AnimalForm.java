package api.adocao.controller.form;

import api.adocao.entidade.Animal;
import api.adocao.entidade.Instituicao;
import api.adocao.repositorio.AnimalRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class AnimalForm {

    @NotNull
    @Length(min = 3, max = 255)
    private String nome;
    @NotNull
    @Length(min = 3, max = 255)
    private String especie;
    @NotNull
    @Length(min = 3, max = 255)
    private String raca;
    private Instituicao instituicao;

    public Animal atualizar(Long id, AnimalRepository animalRepository) {
        Animal animal = animalRepository.getById(id);
        animal.setNome(this.nome);
        animal.setEspecie(this.especie);
        animal.setRaca(this.raca);
        animal.setInstituicao(this.instituicao);
        return animal;
    }
}
