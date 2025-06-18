package com.kaikan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    private Long idUsuario;
    private LocalDateTime fechaPago;
    private List<DetalleDTO> detalles;
    @Data
    @AllArgsConstructor
    public static class DetalleDTO{
        private Long idPlato;
        private Integer cantidad;
    }

}
