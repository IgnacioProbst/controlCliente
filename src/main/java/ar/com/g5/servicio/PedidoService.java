package ar.com.g5.servicio;

import java.util.List;
import ar.com.g5.domain.Pedido;

public interface PedidoService {

    public List<Pedido> listarPedidos();
    
    public void guardar(Pedido pedido);
    
    public void eliminar(Pedido pedido);
    
    public Pedido encontrarPedido(Pedido pedido);
    
}
