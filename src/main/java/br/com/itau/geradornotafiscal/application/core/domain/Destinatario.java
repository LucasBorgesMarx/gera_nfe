package br.com.itau.geradornotafiscal.application.core.domain;



import br.com.itau.geradornotafiscal.application.core.enuns.ERegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.application.core.enuns.ETipoPessoa;

import java.util.List;


public class Destinatario {

	public Destinatario() {
	}
	public Destinatario(String nome, ETipoPessoa ETipoPessoa, List<Documento> documentos, List<Endereco> enderecos) {
		this.nome = nome;
		this.ETipoPessoa = ETipoPessoa;
		this.documentos = documentos;
		this.enderecos = enderecos;
	}
	public Destinatario(String nome, ETipoPessoa ETipoPessoa, ERegimeTributacaoPJ regimeTributacao, List<Documento> documentos, List<Endereco> enderecos) {
		this.nome = nome;
		this.ETipoPessoa = ETipoPessoa;
		this.regimeTributacao = regimeTributacao;
		this.documentos = documentos;
		this.enderecos = enderecos;
	}

	private String nome;

	private ETipoPessoa ETipoPessoa;

	private ERegimeTributacaoPJ regimeTributacao;

	private List<Documento> documentos;

	private List<Endereco> enderecos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ETipoPessoa getTipoPessoa() {
		return ETipoPessoa;
	}

	public void setTipoPessoa(ETipoPessoa ETipoPessoa) {
		this.ETipoPessoa = ETipoPessoa;
	}

	public ERegimeTributacaoPJ getRegimeTributacao() {
		return regimeTributacao;
	}

	public void setRegimeTributacao(ERegimeTributacaoPJ regimeTributacao) {
		this.regimeTributacao = regimeTributacao;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}




