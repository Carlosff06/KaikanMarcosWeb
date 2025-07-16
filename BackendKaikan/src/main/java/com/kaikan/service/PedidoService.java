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
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public List<Pedido> listarPedidosPorEmail(String email){
        return this.pedidoRepository.listarPedidosPorEmail(email);
    }

    /**
     * Registra un nuevo pedido en la base de datos.
     *
     * @param dto Objeto Pedido que contiene los datos para crear el nuevo pedido.
     * @throws RuntimeException si no se encuentra el usuario asociado al pedido.
     */
    @Transactional
    public void registrarPedido(Pedido dto) {
        // Crear una nueva instancia de Pedido que será guardada en BD
        Pedido pedido = new Pedido();

        // Buscar el usuario por email; lanzar excepción si no existe
        Usuario usuario = usuarioRepository.findByEmail(dto.getUsuario().getEmail())
                .orElseThrow(() -> new RuntimeException("ID de Usuario no encontrado"));

        // Asociar el usuario encontrado al nuevo pedido
        pedido.setUsuario(usuario);

        // Establecer el estado del pedido recibido
        pedido.setEstado(dto.getEstado());

        // Definir fechas según el estado del pedido
        if (pedido.getEstado().name().equals("Finalizado")) {
            // Si el pedido está finalizado, establecer fecha de pago al momento actual
            pedido.setFecha_pago(LocalDateTime.now());
        } else if (pedido.getEstado().name().equals("Pendiente")) {
            // Si el pedido está pendiente, establecer fecha de pedido al momento actual
            pedido.setFecha_pedido(LocalDateTime.now());
        }

        // Asociar el pedido recién creado a cada detalle del pedido recibido
        for (DetallePedido detalleDTO : dto.getDetallesPedido()) {
            detalleDTO.setPedido(pedido);
        }

        // Asignar la lista de detalles al pedido
        pedido.setDetallesPedido(dto.getDetallesPedido());

        // Opcional: imprimir el pedido para debug
        System.out.println(pedido);

        // Guardar el pedido con sus detalles en la base de datos
        pedidoRepository.save(pedido);
    }


    /**
     * Actualiza un pedido existente en la base de datos.
     *
     * @param pedido El objeto Pedido con los datos actualizados.
     * @throws IllegalArgumentException si el pedido o su ID son nulos.
     * @throws NoSuchElementException si no se encuentra el pedido con el ID proporcionado.
     */
    public void actualizarPedido(Pedido pedido) {
        // Validar que el pedido y su ID no sean nulos para evitar errores
        if (pedido == null || pedido.getId() == null) {
            throw new IllegalArgumentException("El pedido o su ID no pueden ser nulos.");
        }

        // Buscar el pedido existente por ID
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(pedido.getId());

        // Lanzar excepción si el pedido no existe en la base de datos
        if (pedidoExistente.isEmpty()) {
            throw new NoSuchElementException("No existe el pedido con ID: " + pedido.getId());
        }

        // Si el pedido tiene detalles asociados, vincularlos correctamente al pedido
        if (pedido.getDetallesPedido() != null) {
            for (DetallePedido detallePedido : pedido.getDetallesPedido()) {
                detallePedido.setPedido(pedido);
            }
        }

        // Guardar los cambios en el pedido (incluyendo detalles) en la base de datos
        pedidoRepository.save(pedido);
    }


}
