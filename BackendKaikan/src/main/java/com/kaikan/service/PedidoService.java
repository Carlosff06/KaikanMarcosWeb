package com.kaikan.service;

import com.kaikan.dto.PedidoRequestDTO;
import com.kaikan.generics.GenericService;
import com.kaikan.model.detallePedido.DetallePedido;
import com.kaikan.model.pedido.Estado;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import com.kaikan.model.usuario.Usuario;
import com.kaikan.repository.DetallePedidoRepository;
import com.kaikan.repository.PedidoRepository;
import com.kaikan.repository.PlatoRepository;
import com.kaikan.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService extends GenericService <Pedido, Long> {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlatoRepository platoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    public PedidoService (PedidoRepository pedidoRepository,
                          UsuarioRepository usuarioRepository,
                          PlatoRepository platoRepository,
                          DetallePedidoRepository detallePedidoRepository
    ){
        super(pedidoRepository);
        this.usuarioRepository = usuarioRepository;
        this.pedidoRepository = pedidoRepository;
        this.platoRepository = platoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public void registrarPedido(PedidoRequestDTO dto){
        Pedido pedido = new Pedido();
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).
                orElseThrow(() -> new RuntimeException("ID de Usuario no encontrado"));

        pedido.setUsuario(usuario);
        pedido.setFecha_pedido(LocalDateTime.now());
        pedido.setFecha_pago(dto.getFechaPago());
        pedido.setEstado(Estado.Pendiente);
        pedido = pedidoRepository.save(pedido);


        for (PedidoRequestDTO.DetalleDTO detalleDTO: dto.getDetalles()){
            Plato plato = platoRepository.findById(detalleDTO.getIdPlato())
                    .orElseThrow(()-> new RuntimeException("ID de Plato no encontrado"));

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setPlato(plato);
            detalle.setCantidad(detalleDTO.getCantidad());

            detallePedidoRepository.save(detalle);


        }
    }
}
