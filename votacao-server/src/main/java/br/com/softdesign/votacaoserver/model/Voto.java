package br.com.softdesign.votacaoserver.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "sd_tbl_voto")
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "sd_tbl_pauta_id")
    Pauta pauta;

    // vou consedirar que o id do associado e um UUID
    @Column(name = "id_associado", updatable = false, nullable = false)
    UUID idAssociado;

    @Column(name = "fl_voto", updatable = false, nullable = false)
    Boolean voto;

}
