package api.adocao.controller.dto;

import api.adocao.entidade.Perfil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsuarioDetalhadoDTO extends UsuarioDTO {
    private String senha;
    private String cpf;
    private List<Perfil> perfis = new ArrayList<>();
}
