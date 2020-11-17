package ar.com.g5.servicio;

import java.util.List;
import ar.com.g5.domain.Producto;

public interface ProductoService {
    
    public List<Producto> listarProductos();
    
    public void guardar(Producto producto);
    
    public void eliminar(Producto producto);
    
    public Producto encontrarProducto(Producto producto);
}