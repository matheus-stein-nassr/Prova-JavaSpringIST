package br.senai.sc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.senai.sc.models.Conta;
import br.senai.sc.models.Movimentacao;
import br.senai.sc.models.Pessoa;
import br.senai.sc.services.ContaService;
import br.senai.sc.services.MovimentacaoService;
import br.senai.sc.services.PessoaService;

@ViewScoped
@Controller
public class MovimentacaoController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_MOVIMENTACAO = "/public/movimentacao.jsf";

	@Autowired
	private MovimentacaoService movimentacaoService;

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ContaService contaService;

	@Autowired
	private Movimentacao movimentacao;

	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

	private List<SelectItem> listaSelectPessoas;
	private List<SelectItem> listaSelectContas;

	public MovimentacaoController() {
	}

	@PostConstruct
	public void init() {
		movimentacoes = movimentacaoService.ListarMovimentacoes();
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public List<SelectItem> carregaListaPessoas() {
		List<Pessoa> list = pessoaService.ListarPessoas();
		listaSelectPessoas = new ArrayList<SelectItem>();
		listaSelectPessoas.clear();
		for (Pessoa pess : list) {
			listaSelectPessoas.add(new SelectItem(pess, pess.getNome()));
		}
		return listaSelectPessoas;
	}
	
	/*
	 * public List<SelectItem> carregaListaContas() { List<Conta> list =
	 * contaService.ListarContas(); listaSelectContas = new ArrayList<SelectItem>();
	 * listaSelectContas.clear(); for(Conta cont : list) { listaSelectContas.add(new
	 * SelectItem(cont, cont.getConta().toString())); } return listaSelectContas; }
	 */
}
