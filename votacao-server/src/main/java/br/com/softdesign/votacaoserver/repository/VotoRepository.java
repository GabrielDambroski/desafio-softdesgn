package br.com.softdesign.votacaoserver.repository;

import br.com.softdesign.votacaoserver.model.Voto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotoRepository extends JpaAndRevisionRepository<Voto>{

    @Query(value = "SELECT vot FROM Voto vot WHERE pauta.id = :idPauta AND idAssociado = :idAssociado")
    Voto getByIdPautaAndIdAssociado(UUID idPauta, UUID idAssociado);

    @Query(value = "SELECT COUNT(stv.id) FROM sd_tbl_voto stv WHERE sd_tbl_pauta_id = :idPauta and fl_voto = :voto ", nativeQuery = true)
    Long getCountVoto(UUID idPauta, Boolean voto);

}