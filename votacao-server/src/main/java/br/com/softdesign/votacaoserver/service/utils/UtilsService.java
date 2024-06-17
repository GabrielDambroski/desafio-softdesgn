package br.com.softdesign.votacaoserver.service.utils;

import br.com.softdesign.votacaoserver.dto.CpfValidoDto;

public interface UtilsService {

    CpfValidoDto validarCpf(String cpf);

}
