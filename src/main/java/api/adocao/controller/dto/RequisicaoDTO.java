package api.adocao.controller.dto;

import api.adocao.entidade.StatusRequisicaoEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequisicaoDTO {

    private Long id;
    private Long animalId;
    private Long usuarioId;
    private StatusRequisicaoEnum status;
    private LocalDate dataDeCadastro;
    private LocalDate dataDeFechamento;

}
