package br.com.softdesign.votacaoserver.service;

import br.com.softdesign.votacaoserver.dto.SessaoDto;
import br.com.softdesign.votacaoserver.dto.SessaoResponseDto;
import br.com.softdesign.votacaoserver.model.SessaoVotacao;

import java.util.UUID;

public interface SessaoService {

    SessaoVotacao criarSessaoVotos(SessaoDto sessaoDto);

    Boolean isSessaoAberta(UUID idPauta);
}
