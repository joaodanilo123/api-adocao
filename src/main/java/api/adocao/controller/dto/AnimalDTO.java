package api.adocao.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTO {
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private Long idInstituicao;
}
