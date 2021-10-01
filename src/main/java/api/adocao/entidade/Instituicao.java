package api.adocao.entidade;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "entidade")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Instituicao {

    @Id
    private String cnpj;
    @NotNull @NotEmpty @Min(5) @Max(255)
    private String nome;
    @NotNull @NotEmpty @Min(5) @Max(255)
    private String endereco;

    @OneToMany(mappedBy = "instituicao")
    @ToString.Exclude
    private List<Animal> animais = new ArrayList<>();

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

}
