package api.adocao.controller.form;

import api.adocao.entidade.Perfil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UsuarioForm {
    @NotNull
    @Length(min = 3, max = 255)
    private String nome;
    @NotNull
    @Length(min = 3, max = 255)
    @Email
    private String email;
    @NotNull
    @Length(min = 8)
    private String senha;
    @NotNull
    @Length(min = 11, max = 11)
    @CPF
    private String cpf;
    @NotNull
    @Length(min = 3, max = 255)
    private String telefone;
    @NotNull
    private LocalDate nascimento;
}
