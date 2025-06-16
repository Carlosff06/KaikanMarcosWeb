package com.kaikan.model.categoria;


import com.kaikan.generics.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "categoria", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Categoria extends BaseEntity {
    private String nombre;
    private String img_url;
}
