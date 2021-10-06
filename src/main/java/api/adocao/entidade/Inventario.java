package api.adocao.entidade;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

@Entity
@RestController
@RequestMapping
@Table(name = "inventario_de_doacoes")
@Data
@RequiredArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
