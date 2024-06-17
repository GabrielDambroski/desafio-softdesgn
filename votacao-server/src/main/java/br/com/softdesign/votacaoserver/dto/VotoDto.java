package br.com.softdesign.votacaoserver.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoDto {

    UUID idAssociado;

    String cpfAssociado;

    UUID idPauta;

    Boolean voto;
}
