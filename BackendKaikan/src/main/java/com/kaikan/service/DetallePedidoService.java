package com.kaikan.service;

import com.kaikan.generics.GenericService;
import com.kaikan.model.detallePedido.DetallePedido;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService extends GenericService <DetallePedido, Long> {
    private final DetallePedidoRepository detallePedidoRepository;
    public DetallePedidoService (DetallePedidoRepository detallePedidoRepository){
        super(detallePedidoRepository);
        this.detallePedidoRepository = detallePedidoRepository;
    }
}
