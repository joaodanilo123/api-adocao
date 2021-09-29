package api.adocao.controller.dto;

import lombok.Data;

@Data
public class AnimalDTO {
    private Long id;
    private String nome;
    private String especie;
    private String raca;
}
