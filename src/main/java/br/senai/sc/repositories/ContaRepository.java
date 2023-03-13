package br.senai.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.sc.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
