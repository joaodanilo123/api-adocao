package api.adocao.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NovaSenhaForm {
    @NotNull
    @NotEmpty
    @Length(min = 8)
    private String senha;
}
