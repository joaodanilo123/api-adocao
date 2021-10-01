package api.adocao.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class AtualizacaoUsuarioForm {
    @NotNull
    @Length(min = 3, max = 255)
    private String nome;
    @NotNull
    @Length(min = 3, max = 255)
    private String email;
    @NotNull
    @Length(min = 11, max = 11)
    private String cpf;
    @NotNull
    @Length(min = 3, max = 255)
    private String telefone;
}
