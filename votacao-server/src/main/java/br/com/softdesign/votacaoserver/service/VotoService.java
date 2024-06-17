package br.com.softdesign.votacaoserver.service;

import br.com.softdesign.votacaoserver.dto.VotoDto;
import br.com.softdesign.votacaoserver.model.Voto;

public interface VotoService {

    Voto votar(VotoDto votoDto);



}
