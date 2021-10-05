package api.adocao.entidade;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especie;
    private String raca;

    @ManyToOne
    @JoinColumn(name = "id_instituicao")
    private Instituicao instituicao;

}
