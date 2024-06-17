package br.com.softdesign.votacaoserver.service.utils;

import br.com.softdesign.votacaoserver.dto.CpfValidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Autowired
    private RestTemplateService restTemplateService;

    @Value("${url.validar.cpf}")
    private String urlServiceValidarCpf;

    @Override
    public CpfValidoDto validarCpf(String cpf) {

        try {
            ResponseEntity<CpfValidoDto> retorno = (ResponseEntity<CpfValidoDto>) restTemplateService.get((urlServiceValidarCpf + cpf), null, CpfValidoDto.class);

            if(retorno.getStatusCodeValue() == 404){
                new IllegalArgumentException("CPF inválido");
            } if(retorno.getStatusCodeValue() == 200){
                return retorno.getBody();
            } else {
                new IllegalArgumentException("Não foi possivel validar o CPF");
            }
        } catch (Exception e){
            throw new IllegalArgumentException("Não foi possivel validar o CPF");
        }

        return null;
    }
}
