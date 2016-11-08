package br.com.modulo.core.model.escola;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embeddable;

/**
 * @author elton.santos
 */
@Embeddable
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Email> emails;
	private List<Telefone> telefones;

	public List<Email> getEmails() {
		return emails;
	}
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}



}
