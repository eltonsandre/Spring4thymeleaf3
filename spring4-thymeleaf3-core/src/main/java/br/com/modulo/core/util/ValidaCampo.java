package br.com.modulo.core.util;

public class ValidaCampo {

	public static boolean nome(String nome) {
		return validaER(nome, "([a-zA-Z\\s])?");
	}

	public static boolean numero(String numero) {
		return validaER(numero, "([0-9_])?");
	}

	public static boolean email(String email) {
		return validaER(email, "([a-z0-9_]+@[a-z0-9]+\\.[a-z]{3}(\\.[a-z]{2})?)?");
	}

	public static boolean cpf(String cpf) {
		return validaER(cpf, "([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2})?");
	}

	public static boolean rg(String rg) {
		return validaER(rg, "([0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\-[x_x0-9_]{1})?");
	}

	public static boolean cnpj(String cnpj) {
		return validaER(cnpj, "([0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}\\-[0-9]{2})?");
	}

	public static boolean inscricaoEstadual(String ie) {
		return validaER(ie, "([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\.[0-9]{3})?");
	}

	public static boolean cep(String cep) {
		return validaER(cep, "([0-9]{5}-[0-9]{3})?");
	}

	public static boolean telefone(String telefone) {
		return validaER(telefone, "(\\([0-9]{2}\\)( )?[0-9]{4}-[0-9]{4})?");
	}

	public static boolean data(String data) {
		return validaER(data, "((0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3})?");
	}

	public static boolean placaAuto(String placa) {
		return validaER(placa, "[A-Z]{3}-[0-9]{4}?");
	}

	public static boolean ip(String ip) {
		return validaER(ip, "(([1]?[0-9]{1,2}|2([0-4][0-9]|5[0-5]))\\.){3}([1]?[0-9]{1,2}|2([0-4][0-9]|5[0-5]))?");
	}

	public static boolean validaER(String doc, String er) {
		ExpressaoRegular expressaoRegular = new ExpressaoRegular();
		expressaoRegular.setExpressaoRegular(er);
		expressaoRegular.setValidar(doc);
		expressaoRegular.setPadrao();
		expressaoRegular.setPesquisa();

		return expressaoRegular.getPesquisa().matches();
	}
}
