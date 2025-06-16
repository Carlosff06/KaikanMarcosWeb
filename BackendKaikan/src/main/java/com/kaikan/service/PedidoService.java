package com.kaikan.service;

import com.kaikan.generics.GenericService;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends GenericService <Pedido, Long> {
    private final PedidoRepository pedidoRepository;
    public PedidoService (PedidoRepository pedidoRepository){
        super(pedidoRepository);
        this.pedidoRepository = pedidoRepository;
    }
}
