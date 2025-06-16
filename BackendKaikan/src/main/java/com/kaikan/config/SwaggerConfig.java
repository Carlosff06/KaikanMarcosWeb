package com.kaikan.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "API KAIKAN",
        description = """
    📌 Descripción:
    Esta API ha sido desarrollada para facilitar la comunicación entre la aplicación del restaurante Kaikan
    y su base de datos. Permite consultar, gestionar y actualizar de forma eficiente la información relacionada
    con platos, categorías y demás recursos del sistema.

    👥 Autores:
    - Carlos Felipe Fernández Falcón — U21310389
    - Jerry Marino Domínguez Rivera — U22204886
    - Christian Hernan Villanueva Alvarez — U21311412
    """
,
             version="1.0.0"
        )
)
public class SwaggerConfig {}
