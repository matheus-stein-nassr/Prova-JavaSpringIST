package br.senai.sc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.senai.sc.models.Conta;
import br.senai.sc.models.Pessoa;
import br.senai.sc.services.ContaService;
import br.senai.sc.services.PessoaService;
import br.senai.sc.utils.FacesUtils;

@ViewScoped
@Controller
public class ContaController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_CONTA = "/public/conta.jsf";
	
	@Autowired
	private ContaService contaService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private Conta conta;

	private List<Conta> contas = new ArrayList<Conta>();

	private List<SelectItem> listaSelectContas;
	private List<SelectItem> listaSelectPessoas;

	public ContaController() {
	}

	@PostConstruct
	public void init() {
		contas = contaService.ListarContas();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	public void salvar() {
		try {
			this.conta = contaService.salvar(conta);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));
			PrimeFaces.current().ajax().update("formAdd:addConta", "formList:tblConta");
			limpar();
			contas = contaService.ListarContas();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void selecionar(Conta conta) {
		contaService.contaFindById(conta.getId());
	}
	
	public void excluir () {
		try {
			contaService.delete(conta);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro excluido com sucesso!"));
			limpar();
			init();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public List<SelectItem> carregaComboContas() {
		List<Conta> list = contaService.ListarContas();
		listaSelectContas = new ArrayList<SelectItem>();

		listaSelectContas.clear();
		for (Conta cont : list) {
			listaSelectContas.add(new SelectItem(cont, cont.getNumConta()));
		}
		return listaSelectContas;
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
	
	public Object getRowKey(Conta cont) {
		return cont.getId();
	}
	
	public void rowSelected(SelectEvent<Conta> event) {
		this.conta = (Conta) event.getObject();
	}
	
	public void onRowUnselect(UnselectEvent<Conta> event) {
		this.conta = (Conta) event.getObject();
	}
	
	private void limpar() {
		conta = new Conta();
	}
}
