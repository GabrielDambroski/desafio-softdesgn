package br.com.softdesign.votacaoserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoDto {

    UUID idPauta;

    @Schema(description = "Quantidade de minutos que a pauta ficara aberta para votação, numero default e 1")
    Integer qtdMinSessao = 1;

}
