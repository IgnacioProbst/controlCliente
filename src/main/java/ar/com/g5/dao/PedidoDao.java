
package ar.com.g5.dao;

import ar.com.g5.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoDao extends JpaRepository<Pedido, Long>{
    
}
