package com.kaikan.repository;

import com.kaikan.generics.GenericRepository;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends GenericRepository <Pedido, Long> {


    @Query("""
        SELECT p FROM Pedido p 
    WHERE p.usuario.email=:email"""
            
            )
    List<Pedido> listarPedidosPorEmail(@Param("email") String email);

}
