package ar.com.g5.web;

import javax.validation.Valid;

import ar.com.g5.domain.Producto;
import ar.com.g5.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorProductos {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/product")
    public String producto(Model model, @AuthenticationPrincipal User user) {
        
        var recogiendo = productoService.listarProductos();
        model.addAttribute("recogiendo", recogiendo);
        model.addAttribute("totalProductos", recogiendo);
        return "product";
    }
    
    @GetMapping("/agregarProducto")
    public String agregar(Producto producto) {
        return "modificarProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardar(@Valid Producto producto, Errors errores) {
        if (errores.hasErrors()) {
            return "modificarProducto";
        }
        productoService.guardar(producto);
        return "redirect:/product";
    }

    @GetMapping("/editarProducto/{idProducto}")
    public String editar(Producto producto, Model model) {
        producto = productoService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        return "modificarProducto";
    }

    @GetMapping("/eliminarProducto")
    public String eliminar(Producto producto) {
        productoService.eliminar(producto);
        return "redirect:/product";
    }

}
