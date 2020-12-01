package ar.com.g5.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    
    @NotEmpty
    private Persona nombrePersona;
    
    @NotNull
    private Producto nombreProducto;
    
    private Date fecha;
    
    private int total;
   
}
