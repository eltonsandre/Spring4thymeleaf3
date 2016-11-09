package br.com.modulo.core.util;

import static java.lang.Integer.parseInt;
import static java.lang.System.out;

import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Esta classe tem a finalidade de aglutinar métodos utilitários de manipulação
 * de Strings
 *
 * @author
 * @version 1.0
 */
public class StringUtil {

	/**
	 * Testa e converte String evitando nulos e brancos
	 *
	 * @param text
	 *            - string de texto
	 * @param defaultValue
	 *            - valor default para a String
	 * @return string
	 * @since 1.0
	 */
	public static String parseString(String text, String defaultValue) {
		String str = defaultValue;
		if (text != null) {
			str = text.trim();
		}
		return str;
	}

	/**
	 * Converte uma String em um char,
	 *
	 * @param text
	 *            - string de texto
	 * @param defaultValue
	 *            - valor default para o char
	 * @return char - primeiro caracter da String
	 * @since 1.0
	 */
	public static char parseChar(String text, char defaultValue) {
		char chr = defaultValue;
		if (text != null && text.length() > 0) {
			chr = text.charAt(0);
		}
		return chr;
	}

	/**
	 * Converte uma String em um char,
	 *
	 * @param text
	 *            - string de texto
	 * @param defaultValue
	 *            - valor default para o char
	 * @return char - primeiro caracter da String
	 * @since 1.0
	 */
	public static char parseChar(char text, char defaultValue) {
		char chr = defaultValue;
		if (text != 0) {
			chr = text;
		}
		return chr;
	}

	/**
	 * Converte um java.lang.Number em String, ignorando nulos
	 *
	 * @param obj
	 *            - inst�ncia de Number
	 * @param defaultValue
	 *            - valor default caso o obj seja nulo
	 * @return string
	 * @since 1.0
	 */
	public static String parseNumber(Number obj, String defaultValue) {
		String other = defaultValue;
		if (obj != null) {
			other = obj.toString();
		}
		return other;
	}

	/**
	 * Preenche com "0" a esquerda do �ltimo valor decimal menos significativo
	 *
	 * @param origem
	 *            - String com valor, eg. 1.0, 1
	 * @param caracter
	 *            - caracter de preenchimento
	 * @param casas
	 *            - numero de casas de PAD (preenchimento com o caracter
	 *            informado);
	 * @return string - 1.000000000000000
	 */
	public static String numberPad(String origem, char caracter, int casas) {
		if (origem.equals("")) {
			origem = "0";
		}
		StringBuilder other = new StringBuilder(origem);
		int size = origem.length();
		int pointPos = origem.indexOf('.');
		if (pointPos != -1) {
			int lenght = casas - (size - pointPos - 1);
			if (origem != null) {
				for (int i = 0; i < lenght; i++) {
					other.append(caracter);
				}
			}
			return other.toString();
		} else {
			other.append('.');
			return numberPad(other.toString(), caracter, casas);
		}
	}

	/**
	 * Preenche com "0" a esquerda do �ltimo valor decimal menos significativo
	 *
	 * @param origem
	 *            - String com valor, eg. 1.0, 1
	 * @param caracter
	 *            - caracter de preenchimento
	 * @param casas
	 *            - numero de casas de PAD (preenchimento com o caracter
	 *            informado
	 * @param separador);
	 * @return string - 1.000000000000000
	 */
	public static String numberPad(String origem, char caracter, int casas, char separador) {

		String result = numberPad(origem, caracter, casas);
		return result.replace('.', ',');
	}

	/**
	 * M�todo respons�vel em trucar um texto e garantir um tamanho m�ximo na
	 * cadeia de String
	 *
	 * @param text
	 *            - texto inicial
	 * @param size
	 *            - tamanho m�ximo
	 * @return texto trucado se o tamanho for maior que o size
	 */
	public static String truncate(String text, int size) {
		String trunc = "";
		if (text.length() <= size) {
			trunc = text;
		} else {
			trunc = text.substring(0, size - 1);
		}
		return trunc;
	}

	/**
	 * M�todo respons�vel em processar ranges obtidos atrav�z de Strings no
	 * formato valor1-valor2;valor3-valor4;valor5
	 *
	 * @param data
	 *            - String no formato de ranges, onde o ";" definie um range, e
	 *            o "-" define o valor inicial e final
	 *
	 * @return String[][] com os ranges gerados
	 * @since 1.0
	 * @throws IllegalArgumentException
	 *             se data for nulo ou branco ou nao for uma string de ranges
	 */
	public static String[][] processRanges(String data) {
		if (data == null || data.trim().length() == 0) {
			throw new IllegalArgumentException("Data can�t be null!");
		}
		int lastChar = data.length() - 1;
		if (data.charAt(lastChar) != ';') {
			data = data += ";";
		}
		String[] values = data.split(";");
		String[][] ranges = new String[values.length][];
		for (int i = 0; i < values.length; i++) {

			String[] range = null;
			out.println("Range " + i + ": " + values[i]);
			if (values[i].indexOf('-') != -1) {
				range = values[i].split("-");
			} else {
				range = new String[2];
				range[0] = values[i];
				range[1] = values[i];
			}
			ranges[i] = range;

		}
		return ranges;
	}

	/**
	 * M�todo que realiza a separa��o da String passada como argumento
	 * utilizando como delimitador tokenizer.
	 *
	 * @param src
	 * @param tokenizer
	 * @return vetor contendo a String original separada a partir do tokenizer
	 * @throws IllegalArgumentException
	 *             caso a String de argumento ou o caracter tokenizer forem
	 *             nulos
	 */
	public static String[] split(String src, String tokenizer) {
		if (src == null) {
			throw new IllegalArgumentException("Data can�t be null!");
		}

		if (tokenizer == null) {
			throw new IllegalArgumentException("The tokenizer can�t be null!");
		}

		StringTokenizer tokens = new StringTokenizer(src, tokenizer);
		String[] returnContent = new String[tokens.countTokens()];
		for (int i = 0; tokens.hasMoreTokens(); i++) {
			returnContent[i] = (String) tokens.nextToken();
		}
		return returnContent;
	}

	/**
	 * M�todo que gera sequ�ncias a partir de dois ranges dados como entrada.
	 * Caso os valores sejam n�meros inteiros v�lidos, ser� considerada uma
	 * sequ�ncia de n�meros iniciando-se em startRange e finalizando em
	 * endRange. N�o importa a ordem (crescente ou decrescente) que s�o
	 * informados os ranges. Caso os valores n�o sejam num�ricos, a sequ�ncia
	 * ser� composta apenas por startRange e endRange
	 *
	 * @param startRange
	 *            - Valor inicial da sequ�ncia
	 * @param endRange
	 *            - Valor final da sequ�ncia
	 *
	 * @return String entre v�rgulas que representa o conjunto iniciado em
	 *         startRange e finalizado em endRange
	 *
	 * @throws IllegalArgumentException
	 *             se algum dos ranges for nulo
	 *
	 * @since 1.0
	 */
	public static String createRangeSequence(String startRange, String endRange) {
		if (startRange == null || endRange == null) {
			throw new IllegalArgumentException("The input ranges can't be null");
		}
		StringBuilder rangeSequence = new StringBuilder();
		int start = 0;
		int end = 0;
		try {
			start = parseInt(startRange);
			end = parseInt(endRange);
			if (start > end) {
				int tmp = start;
				start = end;
				end = tmp;
			}
			rangeSequence.append("'").append(start++).append("'");
			for (; start <= end; start++) {
				rangeSequence.append(", '").append(start).append("'");
			}
		} catch (NumberFormatException nfe) {
			rangeSequence.append("'").append(startRange).append("'");
			if (!startRange.equals(endRange)) {
				rangeSequence.append(", '").append(endRange).append("'");
			}
		}

		return rangeSequence.toString();
	}

	/**
	 * Quebra uma String separada por um marcador num Set
	 *
	 * @param texto
	 * @param separador
	 * @return Set com os elementos da String quebrada
	 */
	public static Set<String> splitToSet(String texto, String separador) {
		Set<String> splited = new TreeSet<String>();
		if (texto == null || separador == null) {
			throw new IllegalArgumentException("Texto e separado nao pode ser nulos");
		}
		String[] params = texto.split(separador);
		splited.addAll(Arrays.asList(params));
		return splited;
	}

	/**
	 * Retorn a string passada no tamanho maximo informado
	 *
	 * @param dados
	 * @param tamanho
	 * @return null se dados for null ou string no tamanho maximo informado ou o
	 *         proprio dados
	 */
	public static String retornaStringTamanhoCampo(String dados, int tamanho) {
		String dado = dados;
		if (dados.length() > tamanho) {
			dado = dados.substring(0, (tamanho - 1));
		}
		return dado;
	}

	public static void main(String args[]) {

	}

}
