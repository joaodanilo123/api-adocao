package api.adocao.controller.form;

import api.adocao.entidade.Animal;
import api.adocao.entidade.Genero;
import api.adocao.entidade.Instituicao;
import api.adocao.repositorio.AnimalRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull
    private String genero;

    private Long instituicaoId;

    public Animal atualizar(Long id, AnimalRepository animalRepository) {
        Animal animal = animalRepository.getById(id);
        animal.setNome(this.nome);
        animal.setEspecie(this.especie);
        animal.setRaca(this.raca);
        animal.setDataNascimento(this.dataNascimento);
        animal.setGenero(Genero.valueOf(getGenero()));
        return animal;
    }
}
