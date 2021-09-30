package api.adocao.repositorio;

import api.adocao.entidade.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository <Instituicao, Long> {


    Instituicao getById(Long id);
}
