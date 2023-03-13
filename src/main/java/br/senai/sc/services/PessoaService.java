package br.senai.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sc.models.Pessoa;
import br.senai.sc.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> ListarPessoas() {
		return pessoaRepository.findAll();
	}

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@Transactional
    public void delete(Pessoa pessoa) {
    	pessoaRepository.delete(pessoa);
    }
    
    public void pessoaFindById(Long id) {
        pessoaRepository.findById(id);
    }
}
