package api.adocao.entidade;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "requisicao")
@Getter
@Setter
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusRequisicaoEnum status;

    private LocalDate dataDeCadastro = LocalDate.now();

    @Nullable
    private LocalDate dataDeFechamento;

}
