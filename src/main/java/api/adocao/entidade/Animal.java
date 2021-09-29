package api.adocao.entidade;

import javax.persistence.*;

@Table(name = "animal")
@Entity
public class Animal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "instituicao_cnpj")
    private Instituicao instituicao;

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}