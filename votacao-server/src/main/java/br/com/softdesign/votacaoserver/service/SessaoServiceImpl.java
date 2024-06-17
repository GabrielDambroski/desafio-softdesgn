package br.com.softdesign.votacaoserver.service;

import br.com.softdesign.votacaoserver.dto.SessaoDto;
import br.com.softdesign.votacaoserver.dto.SessaoResponseDto;
import br.com.softdesign.votacaoserver.model.Pauta;
import br.com.softdesign.votacaoserver.model.SessaoVotacao;
import br.com.softdesign.votacaoserver.repository.PautaRepository;
import br.com.softdesign.votacaoserver.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class SessaoServiceImpl implements SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    public SessaoVotacao criarSessaoVotos(SessaoDto sessaoDto){

        Pauta pauta = pautaRepository.getById(sessaoDto.getIdPauta());

        if(Objects.isNull(pauta)){
            throw new IllegalArgumentException("Pauta não encontrada");
        }

        SessaoVotacao sessaoVotacao = sessaoRepository.getByPautaId(pauta.getId());

        if(Objects.nonNull(sessaoVotacao)){
            throw new IllegalArgumentException("Pauta já possui uma sesaão aberta");
        }

        LocalDateTime dataInicio = LocalDateTime.now();

        sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataInicio(dataInicio);
        sessaoVotacao.setDataFim(dataInicio.plusMinutes(sessaoDto.getQtdMinSessao()));
        sessaoVotacao = sessaoRepository.save(sessaoVotacao);

        return sessaoVotacao;
    }

    @Override
    public Boolean isSessaoAberta(UUID idPauta) {
        return Objects.nonNull(sessaoRepository.isSessaoAberto(idPauta, LocalDateTime.now()));
    }
}