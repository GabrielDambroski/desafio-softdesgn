package br.com.softdesign.votacaoserver.service;

import br.com.softdesign.votacaoserver.dto.PautaDto;
import br.com.softdesign.votacaoserver.dto.PautaResponseDto;
import br.com.softdesign.votacaoserver.dto.ResultadoEnum;
import br.com.softdesign.votacaoserver.model.Pauta;
import br.com.softdesign.votacaoserver.model.SessaoVotacao;
import br.com.softdesign.votacaoserver.model.Voto;
import br.com.softdesign.votacaoserver.repository.PautaRepository;
import br.com.softdesign.votacaoserver.repository.SessaoRepository;
import br.com.softdesign.votacaoserver.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PautaServiceImpl implements PautaService{

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Override
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public List<Pauta> getPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public PautaResponseDto getResultadoPauta(UUID id) {

        Pauta pauta = pautaRepository.getById(id);

        if(Objects.isNull(pauta)){
            throw new IllegalArgumentException("Pauta não encontrada");
        }

        SessaoVotacao sessaoVotacao = sessaoRepository.getByPautaId(pauta.getId());

        if(Objects.isNull(sessaoVotacao)){
            throw new IllegalArgumentException("Pauta não foi iniciada para a votação");
        }

        PautaResponseDto pautaResponseDto = new PautaResponseDto();

        pautaResponseDto.setTitulo(pauta.getTitulo());
        pautaResponseDto.setDescricao(pauta.getDescricao());

        Boolean sessaoFinalizado = sessaoService.isSessaoAberta(pauta.getId());

        if(!sessaoFinalizado){
            Long votosSim = votoRepository.getCountVoto(pauta.getId(), true);
            Long votosNao = votoRepository.getCountVoto(pauta.getId(), false);

            if(votosSim == votosNao){
                pautaResponseDto.setStatus(ResultadoEnum.EMPATADO);
            }else if(votosSim > votosNao){
                pautaResponseDto.setStatus(ResultadoEnum.APROVADO);
            }else{
                pautaResponseDto.setStatus(ResultadoEnum.REPROVADO);
            }

            pautaResponseDto.setQtdVotos(votosSim + votosNao);
        }else{
            pautaResponseDto.setStatus(ResultadoEnum.EM_VOTACAO);
        }

        return pautaResponseDto;
    }

}
