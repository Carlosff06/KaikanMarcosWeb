package com.kaikan.controller;

import com.kaikan.generics.GenericController;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import com.kaikan.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController extends GenericController <Pedido, Long> {
    private final PedidoService pedidoService;
    public PedidoController (PedidoService pedidoService){
        super(pedidoService);
        this.pedidoService = pedidoService;
    }
}
