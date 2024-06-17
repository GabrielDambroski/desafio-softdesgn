package br.com.softdesign.votacaoserver.repository;

import br.com.softdesign.votacaoserver.model.Pauta;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaAndRevisionRepository<Pauta>{
    
}
