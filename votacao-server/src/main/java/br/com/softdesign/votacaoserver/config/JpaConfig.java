package br.com.softdesign.votacaoserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        enableDefaultTransactions = false, //Retira o autocommit das transações
        basePackages = {"br.com.softdesign.votacaoserver.repository"}
)
public class JpaConfig {

}