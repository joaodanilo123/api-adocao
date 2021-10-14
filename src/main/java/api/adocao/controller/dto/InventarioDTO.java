package api.adocao.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InventarioDTO {
    private Long id;
    private String item;

    //@JsonFormat(pattern="dd-MM-yyyy")
    private Date data_doacao;
}
