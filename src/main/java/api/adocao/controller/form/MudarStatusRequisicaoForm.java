package api.adocao.controller.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MudarStatusRequisicaoForm {
    @NotNull
    private Long requisicaoId;
    @NotNull
    private String status;
}
