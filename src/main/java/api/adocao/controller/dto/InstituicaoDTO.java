package api.adocao.controller.dto;

import api.adocao.entidade.Instituicao;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class InstituicaoDTO {

    private Long id;

    private String telefone;

    private String nome;

    private String endereco;

    public InstituicaoDTO (Instituicao instituicao)
    {
        this.telefone = instituicao.getTelefone();
        this.nome = instituicao.getNome();
        this.endereco = instituicao.getEndereco();
    }



}
