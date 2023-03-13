package br.senai.sc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.senai.sc.models.Pessoa;
import br.senai.sc.services.PessoaService;
import br.senai.sc.utils.FacesUtils;

@Controller
@ViewScoped
public class PessoaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_PESSOA = "/public/pessoa.jsf";
	private UIForm form;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private Pessoa pessoa;

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private List<SelectItem> listaSelectPessoas;
	
	public PessoaController() {
	}

	@PostConstruct
	public void init() {
		pessoas = pessoaService.ListarPessoas();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void salvar() {
		try {
			this.pessoa = pessoaService.salvar(pessoa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));
			PrimeFaces.current().ajax().update("formAdd:addPessoa", "formList:tblPessoa");
			limpar();
			pessoas = pessoaService.ListarPessoas();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void selecionar(Pessoa pessoa) {
		pessoaService.pessoaFindById(pessoa.getId());
	}

	public void excluir() {
		try {
			pessoaService.delete(pessoa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro excluido com sucesso!"));
			limpar();
			init();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public List<SelectItem> carregaComboPessoas() {

		List<Pessoa> lista = pessoaService.ListarPessoas();
		listaSelectPessoas = new ArrayList<SelectItem>();

		listaSelectPessoas.clear();
		for (Pessoa pess : lista) {
			listaSelectPessoas.add(new SelectItem(pess, pess.getNome()));
		}
		return listaSelectPessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Object getRowKey(Pessoa pess) {
		return pess.getId();
	}

	public void rowSelected(SelectEvent<Pessoa> event) {
		this.pessoa = (Pessoa) event.getObject();
	}

	public void onRowUnselect(UnselectEvent<Pessoa> event) {
		this.pessoa = (Pessoa) event.getObject();
	}

	private void limpar() {
		pessoa = new Pessoa();
	}	

}