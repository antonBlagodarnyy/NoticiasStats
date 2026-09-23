package com.SinAnimoDeLucro.NoticiasScraper.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Source {
    RTVE("RTVE"),
    EL_MUNDO("ElMundo"),
    VEINTE_MINUTOS( "20Minutos"),
    LA_RAZON( "LaRazon"),
    ABC( "ABC"),
    EL_DIARIO("ElDiario");

    private final String name;



}
