package api.adocao.controller.form;

import api.adocao.entidade.Inventario;
import api.adocao.repositorio.InventarioRepository;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InventarioForm {
    @NotNull
    private String item;

    @NotNull
    private Long instituicaoId;

    public Inventario atualizar(Long id, InventarioRepository repository)
    {
        Inventario inventario = repository.getById(id);
        inventario.setItem(this.item);
        return inventario;
    }
}
