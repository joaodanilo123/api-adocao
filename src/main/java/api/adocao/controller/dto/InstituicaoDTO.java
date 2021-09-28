package api.adocao.controller.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class InstituicaoDTO {

    private String cnpj;

    private String nome;

    private String endereco;
}
