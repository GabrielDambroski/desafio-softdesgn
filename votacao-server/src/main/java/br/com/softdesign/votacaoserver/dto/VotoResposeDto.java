package br.com.softdesign.votacaoserver.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoResposeDto {

    UUID id;

    UUID idAssociado;

    PautaResponseDto pauta;

    Boolean voto;
}
