package br.com.softdesign.votacaoserver.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaResponseDto {

    UUID id;

    String titulo;

    String descricao;

    ResultadoEnum status;

    Long qtdVotos;

}
