package api.adocao.controller.dto;

import lombok.Data;

@Data
public class PontoDeColetaDTO {
    private Long id;

    private String telefone;

    private String nome;

    private String endereco;
}
