package br.com.softdesign.votacaoserver.controller;

import br.com.softdesign.votacaoserver.dto.PautaDto;
import br.com.softdesign.votacaoserver.dto.PautaResponseDto;
import br.com.softdesign.votacaoserver.model.Pauta;
import br.com.softdesign.votacaoserver.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pauta")
public class PautaController extends AbstractController {

    @Autowired
    private PautaService pautaService;

    @PostMapping()
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Criar Pauta", description = "Esse end-point serve para criar uma pauta.")
    public ResponseEntity<PautaResponseDto> save(@RequestBody PautaDto pautaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body((PautaResponseDto) toDto(pautaService.criarPauta((Pauta) dtoTo(pautaDto, Pauta.class)), PautaResponseDto.class));
    }

    @GetMapping()
    @Operation(summary = "Buscar Pautas", description = "Esse end-point serve para buscar pautas criadas.")
    public ResponseEntity<?>getPautas(){
        return ResponseEntity.ok(listToDto(this.pautaService.getPautas(), PautaResponseDto.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar resultado da pauta", description = "Esse end-point serve para buscar resultado da pauta que foi votada.")
    public ResponseEntity<PautaResponseDto>getResultadoPauta(@PathVariable("id") UUID id){
        return ResponseEntity.ok(this.pautaService.getResultadoPauta(id));
    }
}
