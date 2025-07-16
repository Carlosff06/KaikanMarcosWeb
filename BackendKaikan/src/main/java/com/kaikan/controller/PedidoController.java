package com.kaikan.controller;

import com.kaikan.dto.PedidoRequestDTO;
import com.kaikan.generics.GenericController;
import com.kaikan.model.detallePedido.DetallePedido;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import com.kaikan.service.PedidoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController extends GenericController <Pedido, Long> {
    private final PedidoService pedidoService;
    public PedidoController (PedidoService pedidoService){
        super(pedidoService);
        this.pedidoService = pedidoService;
    }

    @GetMapping("/listar-por-email")
    public List<Pedido> listarPedidosPorEmail(@RequestParam("email") String email){
        return pedidoService.listarPedidosPorEmail(email);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> registrarPedido(@RequestBody Pedido pedido){
        pedidoService.registrarPedido(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/actualizar-pedido")
    public ResponseEntity<Pedido> actualizarPedido(@RequestBody Pedido pedido){

        pedidoService.actualizarPedido(pedido);
        return ResponseEntity.ok(pedido);
    }


}
