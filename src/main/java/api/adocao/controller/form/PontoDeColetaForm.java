package api.adocao.controller.form;

import api.adocao.entidade.PontoDeColeta;
import api.adocao.repositorio.PontoDeColetaRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class PontoDeColetaForm {

    @NotNull
    @Length(min = 3, max = 255)
    private String nome;
    @Length(min = 3, max = 255)
    private String endereco;
    @Length(min = 3, max = 255)
    private String telefone;

    public PontoDeColeta atualizar(Long id, PontoDeColetaRepository repository)
    {
        PontoDeColeta ponto = repository.getById(id);
        ponto.setNome(this.nome);
        ponto.setEndereco(this.endereco);
        ponto.setTelefone(this.telefone);
        return ponto;
    }
}
