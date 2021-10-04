package api.adocao.entidade;


/*CASO DE ERRO, É BOM LEMBRAR DE VER AS IMPORTAÇÕES DE BIBLIOTECAS SE ESTÃO CORRETAS
* OLHA ESSE TEXTO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA*/


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RestController
@RequestMapping
@Table(name = "ponto_de_coleta")
@Data
@RequiredArgsConstructor
public class PontoDeColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    @ManyToMany()//@JoinColumn(name = "instituicao_id")
    @ToString.Exclude
    private List<Instituicao> instituicoes = new ArrayList<>();

}
