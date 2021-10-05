package api.adocao.controller.dto;

import api.adocao.entidade.Animal;
import api.adocao.entidade.Instituicao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTO {
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private Instituicao instituicao;

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.nome = animal.getNome();
        this.especie = animal.getEspecie();
        this.raca = animal.getRaca();
        this.instituicao = animal.getInstituicao();
    }
}
