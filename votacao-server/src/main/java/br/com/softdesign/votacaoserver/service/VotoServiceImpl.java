package br.com.softdesign.votacaoserver.service;

import br.com.softdesign.votacaoserver.dto.CpfValidoDto;
import br.com.softdesign.votacaoserver.dto.StatusCpfEnum;
import br.com.softdesign.votacaoserver.dto.VotoDto;
import br.com.softdesign.votacaoserver.model.Pauta;
import br.com.softdesign.votacaoserver.model.Voto;
import br.com.softdesign.votacaoserver.repository.PautaRepository;
import br.com.softdesign.votacaoserver.repository.VotoRepository;
import br.com.softdesign.votacaoserver.service.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VotoServiceImpl implements VotoService{

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private UtilsService utilsService;

    @Override
    public Voto votar(VotoDto votoDto) {

        Pauta pauta = pautaRepository.getById(votoDto.getIdPauta());

        if(Objects.isNull(pauta)){
            throw new IllegalArgumentException("Pauta não encontrada");
        }

        if(!sessaoService.isSessaoAberta(votoDto.getIdPauta())){
            throw new IllegalArgumentException("Sessão não esta mais aberta");
        }

        Voto voto = votoRepository.getByIdPautaAndIdAssociado(pauta.getId(), votoDto.getIdAssociado());

        if(Objects.isNull(voto)){

            CpfValidoDto cpfValidoDto = utilsService.validarCpf(votoDto.getCpfAssociado());

            if(cpfValidoDto.getStatus().equals(StatusCpfEnum.ABLE_TO_VOTE)){
                voto = new Voto();
                voto.setPauta(pauta);
                voto.setIdAssociado(votoDto.getIdAssociado());
                voto.setVoto(votoDto.getVoto());

                return votoRepository.save(voto);
            }else{
                throw new IllegalArgumentException("Associado não pode votar");
            }
        }else{
            throw new IllegalArgumentException("Associado ja realizou voto para essa pauta");
        }
    }
}