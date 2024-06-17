package br.com.softdesign.votacaoserver.controller;

import br.com.softdesign.votacaoserver.dto.SessaoDto;
import br.com.softdesign.votacaoserver.dto.SessaoResponseDto;
import br.com.softdesign.votacaoserver.dto.VotoDto;
import br.com.softdesign.votacaoserver.dto.VotoResposeDto;
import br.com.softdesign.votacaoserver.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voto")
public class VotoController extends AbstractController{

    @Autowired
    private VotoService votoService;

    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Votar pauta", description = "Esse end-point serve para votar em uma pauta.")
    public ResponseEntity<VotoResposeDto> votar(@RequestBody VotoDto votoDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body((VotoResposeDto) toDto(votoService.votar(votoDto), VotoResposeDto.class));
    }

}
