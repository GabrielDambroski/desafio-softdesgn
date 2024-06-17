package br.com.softdesign.votacaoserver.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "sd_tbl_sessao_voto")
public class SessaoVotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;

    @OneToOne
    @JoinColumn(name = "sd_tbl_pauta_id")
    Pauta pauta;

    @Column(name = "dt_data_inicio")
    LocalDateTime dataInicio;

    @Column(name = "dt_data_fim")
    LocalDateTime dataFim;

}
