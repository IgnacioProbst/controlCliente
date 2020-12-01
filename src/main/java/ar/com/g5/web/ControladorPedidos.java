
package ar.com.g5.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ar.com.g5.domain.Pedido;
import ar.com.g5.servicio.PedidoService;
import ar.com.g5.servicio.PersonaService;
import ar.com.g5.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorPedidos {

    @Autowired
    private PedidoService pedidoService;
    
   @Autowired
    private PersonaService personaService;

   @Autowired
    private ProductoService productoService;
   
    @GetMapping("/pedidos")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
 
        var saldoTotal = 0D;
        for (var p : personas) {
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
   
        var recogiendo = productoService.listarProductos();
        model.addAttribute("recogiendo", recogiendo);
        model.addAttribute("totalProductos", recogiendo);
        
//        var pedidos = pedidoService.listarPedidos();
//         model.addAttribute("pedidos", pedidos);
      
        return "pedidos";
    }

    @GetMapping("/agregarPedido")
    public String agregar(Pedido pedido) {
        return "modificarPedido";
    }

    @PostMapping("/guardarPedido")
    public String guardar(@Valid Pedido pedido, Errors errores) {
        if (errores.hasErrors()) {
            return "modificarPedido";
        }
        pedidoService.guardar(pedido);
        return "pedidos";
    }

    @GetMapping("/editarPedido/{idPedido}")
    public String editar(Pedido pedido, Model model) {
        pedido = pedidoService.encontrarPedido(pedido);
        model.addAttribute("pedido", pedido);
        return "modificarPedido";
    }

    @GetMapping("/eliminarPedido")
    public String eliminar(Pedido pedido) {
        pedidoService.eliminar(pedido);
        return "pedidos";
    }

}

