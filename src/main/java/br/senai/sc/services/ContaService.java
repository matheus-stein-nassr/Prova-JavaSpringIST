package br.senai.sc.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sc.models.Conta;
import br.senai.sc.repositories.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	public List<Conta> ListarContas(){
		return contaRepository.findAll();
	}
	
	@Transactional
	public Conta salvar(Conta conta) {
		return contaRepository.save(conta);
	}
	
	@Transactional
	public void delete(Conta conta) {
		contaRepository.delete(conta);
	}
	
	public void contaFindById(Long id) {
		contaRepository.findById(id);
	}

}
