package api.adocao.repositorio;

import api.adocao.entidade.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

}
