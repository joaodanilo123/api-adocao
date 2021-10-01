package api.adocao.entidade;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "instituicao")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Instituicao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty
    private String cnpj;
    @NotNull @NotEmpty @Min(5) @Max(255)
    private String nome;
    private String endereco;
    private String telefone;
    @OneToMany(mappedBy = "instituicao")
    @ToString.Exclude
    private List<Animal> animais = new ArrayList<>();

}
