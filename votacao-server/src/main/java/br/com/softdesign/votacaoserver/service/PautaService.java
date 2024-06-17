package br.com.softdesign.votacaoserver.service;


import br.com.softdesign.votacaoserver.dto.PautaDto;
import br.com.softdesign.votacaoserver.dto.PautaResponseDto;
import br.com.softdesign.votacaoserver.model.Pauta;

import java.util.List;
import java.util.UUID;

public interface PautaService {

    Pauta criarPauta(Pauta pauta);

    List<Pauta> getPautas();


    PautaResponseDto getResultadoPauta(UUID id);

}
