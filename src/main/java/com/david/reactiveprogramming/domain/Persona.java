package com.david.reactiveprogramming.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "seg_persona")
public class Persona {
    @Id
    private Long id_persona;
    private String cx_nombre_completo;
    private String cx_nombre;
    private String cx_primer_apellido;
    private String cx_segundo_apellido;
    private java.time.LocalDate df_fecha_nacimiento;
    private String cx_correo;
    private Integer lock_flag;
}
