package api.adocao.controller.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequisicaoForm {
    @NotNull
    private Long animalId;
    @NotNull
    private Long usuarioId;
}
