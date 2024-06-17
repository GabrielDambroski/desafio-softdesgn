package br.com.softdesign.votacaoserver.controller;

import br.com.softdesign.votacaoserver.dto.SessaoDto;
import br.com.softdesign.votacaoserver.dto.SessaoResponseDto;
import br.com.softdesign.votacaoserver.service.SessaoService;
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
@RequestMapping("/sessao-voto")
public class SessaoVotacaoController extends AbstractController{

    @Autowired
    private SessaoService sessaoService;


    @PostMapping
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Iniciar sessão", description = "Esse end-point serve para iniciar uma sessão de voto em uma pauta.")
    public ResponseEntity<?> iniciarSessao(@RequestBody SessaoDto sessaoDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDto(sessaoService.criarSessaoVotos(sessaoDto), SessaoResponseDto.class));
    }

}
