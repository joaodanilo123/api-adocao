package api.adocao.controller.form;

import api.adocao.entidade.Instituicao;
import api.adocao.repositorio.InstituicaoRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Data
public class InstituicaoForm {

    @NotNull
    @Length(min = 3, max = 255)
    private String cnpj;
    @Length(min = 3, max = 255)
    private String nome;
    @NotNull
    @Length(min = 3, max = 255)
    private String endereco;


    public Instituicao atualizar(Long id, InstituicaoRepository repository)
    {
        Instituicao instituicao = repository.getById(id);
        instituicao.setNome(this.nome);
        instituicao.setEndereco(this.endereco);
        return instituicao;
    }
    /*    public Animal atualizar(Long id, AnimalRepository animalRepository) {
        Animal animal = animalRepository.getById(id);
        animal.setNome(this.nome);
        animal.setEspecie(this.especie);
        animal.setRaca(this.raca);
        return animal;
    }*/
}
