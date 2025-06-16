package com.kaikan.controller;

import com.kaikan.generics.GenericController;
import com.kaikan.model.detallePedido.DetallePedido;
import com.kaikan.model.plato.Plato;
import com.kaikan.service.DetallePedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detallepedido")
public class DetallePedidoController extends GenericController <DetallePedido, Long> {
    private final DetallePedidoService detallePedidoService;
    public DetallePedidoController(DetallePedidoService detallePedidoService){
        super(detallePedidoService);
        this.detallePedidoService = detallePedidoService;
    }
}
