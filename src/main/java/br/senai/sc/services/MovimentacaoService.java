package br.senai.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sc.models.Movimentacao;
import br.senai.sc.repositories.MovimentacaoRepository;


@Service
public class MovimentacaoService {
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	public List<Movimentacao> ListarMovimentacoes(){
		return movimentacaoRepository.findAll();
	}
	
	
}
