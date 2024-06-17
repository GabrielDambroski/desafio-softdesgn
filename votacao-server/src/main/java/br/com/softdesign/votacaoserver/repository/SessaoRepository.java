package br.com.softdesign.votacaoserver.repository;

import br.com.softdesign.votacaoserver.model.SessaoVotacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SessaoRepository extends JpaAndRevisionRepository<SessaoVotacao>{

    SessaoVotacao getByPautaId(UUID idPauta);

    @Query(value = "SELECT * FROM sd_tbl_sessao_voto stsv WHERE sd_tbl_pauta_id = :idPauta AND dt_data_fim > :dataAgora ", nativeQuery = true)
    SessaoVotacao isSessaoAberto(UUID idPauta, LocalDateTime dataAgora);

}
