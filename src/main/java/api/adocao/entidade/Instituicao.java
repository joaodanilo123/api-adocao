package api.adocao.entidade;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instituicao")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Instituicao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    @OneToMany(mappedBy = "instituicao")
    @ToString.Exclude
    private List<Animal> animais = new ArrayList<>();

}
