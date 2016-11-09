package br.com.modulo.core.util;

import java.math.BigDecimal;
import java.rmi.dgc.VMID;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta classe tem a finalidade de aglutinar métodos utilitários de formatação
 * numérica, conversão, etc
 *
 * @author
 * @version
 */
public class NumberUtil {

	/*
	 * Atributo que define um NumberFormat genérico
	 */
	private static NumberFormat simpleFormater;
	/*
	 * Atributo que define um NumberFormat para moeda
	 */
	private static NumberFormat currencyFormater;
	/*
	 * Atributo que define um formatador para Decimais
	 */
	private static DecimalFormat formatDecimal;
	/*
	 * Atributo que define o Locale Default
	 */
	private static final Locale DEFAULT_LOCALE = Locale.getDefault();

	// instancia do Logger
	private static final Logger LOG = LoggerFactory.getLogger(NumberUtil.class);

	/*
	 * Inicializador estático da classe, que inicializa os atributos estáticos
	 */
	static {
		simpleFormater = NumberFormat.getInstance(DEFAULT_LOCALE);
		currencyFormater = NumberFormat.getCurrencyInstance(DEFAULT_LOCALE);
		formatDecimal = new DecimalFormat();
	}

	/**
	 * Formata em String um número ponto flutuante
	 *
	 * @param value
	 *            - valor em double e float
	 * @return String usando a configurações locais de separadores
	 * @since 1.0
	 */
	public static String format(double value) {
		return simpleFormater.format(value);
	}

	/**
	 * Formata em String um número inteiro
	 *
	 * @param value
	 *            - valor em long, int, short e byte
	 * @return String usando a configurações locais de separadores
	 * @since 1.0
	 */
	public static String format(long value) {
		return simpleFormater.format(value);
	}

	/**
	 * Formata em String de Moeda um número ponto flutuante
	 *
	 * @param value
	 *            - valor em double e float
	 * @return String usando a configurações locais de Moeda
	 * @since 1.0
	 */
	public static String formatInCurrency(double value) {
		return currencyFormater.format(value);
	}

	/**
	 * Formata em String de Moeda um número ponto inteiro
	 *
	 * @param value
	 *            - valor em long, int, short e byte
	 * @return String usando a configurações locais de Moeda
	 * @since 1.0
	 */
	public static String formatInCurrency(long value) {
		return currencyFormater.format(value);
	}

	/**
	 * Formata o número recebido como argumento de acordo com o padrão de
	 * formatação
	 *
	 * @param value
	 *            - valor a ser formatado
	 * @param pattern
	 *            - padrão para formatação
	 * @return String que representa o valor formatado
	 * @see java.text.DecimalFormat
	 */
	public static String formatDecimal(long value, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value);
	}

	/**
	 * Formata o número recebido como argumento de acordo com o padrão de
	 * formatação
	 *
	 * @param value
	 *            - valor a ser formatado
	 * @param pattern
	 *            - padrão para formatação
	 * @return String que representa o valor formatado
	 * @see java.text.DecimalFormat
	 */
	public static String formatDecimal(double value, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value);
	}

	/**
	 * Formata o número Decimal recebido como argumento utilizando o formatador
	 * decimal default
	 *
	 * @param value
	 *            - número a ser formatado
	 * @return - String que representa o número apos formatado
	 */
	public static String formatDecimal(Number value) {
		return formatDecimal.format(value);
	}

	/**
	 * Formata o número recebido como argumento de acordo com o padrão de
	 * formatação
	 *
	 * @param value
	 *            - valor a ser formatado
	 * @param pattern
	 *            - padrão para formatação
	 * @return String que representa o valor formatado
	 * @see java.text.DecimalFormat
	 */
	public static String formatDecimal(Number value, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value);
	}

	/**
	 * Converte um valor em String para um valor em double
	 *
	 * @param value
	 *            - String de valor
	 * @return valor em Number
	 * @throws ParseException
	 *             caso a String nao seja um valor valido
	 * @since 1.0
	 */
	public static Number parse(String value) throws ParseException {
		return simpleFormater.parse(value);
	}

	/**
	 * Converte um Integer em String
	 *
	 * @param integer
	 *            - instancia de Integer
	 * @return String convetida
	 * @since 1.0
	 */
	public static String parseInt(Integer integer) {
		try {
			return "" + integer.intValue();
		} catch (Exception ex) {

			LOG.warn("parseInt() Erro na convers�o: " + ex.getMessage() + " , causa: " + ex.getCause());
			return "";
		}

	}

	/**
	 * Converte uma String para Integer
	 *
	 * @param numero
	 *            - String de números
	 * @return Integer
	 * @since 1.0
	 */
	public static Integer parseInteger(String numero) {
		try {
			return new Integer(numero);
		} catch (Exception ex) {
			LOG.warn("parseInteger() Erro na conversão: numero: " + numero + ", erro: " + ex.getMessage() + " , causa: "
					+ ex.getCause());
			return new Integer(0);
		}
	}

	/**
	 * Converte uma String para Long
	 *
	 * @param numero
	 *            - String de números
	 * @return Long
	 * @since 1.0
	 */
	public static Long parseLong(String numero) {
		try {
			return new Long(numero);
		} catch (Exception ex) {
			LOG.warn("parseLong() Erro na convers�o: numero: " + numero + ", erro: " + ex.getMessage() + " , causa: "
					+ ex.getCause());
			return new Long(0);
		}
	}

	/**
	 * Converte uma String para Double
	 *
	 * @param numero
	 *            - String de números
	 * @return Long
	 * @since 1.0
	 */
	public static Double parseDouble(String numero) {
		try {
			if (numero.indexOf(",") > -1) {
				numero = numero.replace(',', '.');
			}
			return new Double(numero);
		} catch (Exception ex) {
			LOG.warn("parseDouble() Erro na convers�o: numero: " + numero + ", erro: " + ex.getMessage() + " , causa: "
					+ ex.getCause());
			return new Double(0);
		}
	}

	/**
	 * Converte uma String em BigDecimal
	 *
	 * @param numero
	 *            - String de numeros
	 * @return BidDecimal
	 * @since 1.0
	 */
	public static BigDecimal parseBigDecimal(String numero) {
		try {
			if (numero.indexOf(",") > -1) {
				numero = numero.replace(',', '.');
			}

			return new BigDecimal(numero);
		} catch (Exception ex) {
			LOG.warn("parseBigDecimal() Erro na convers�o: numero: " + numero + ", erro: " + ex.getMessage()
					+ " , causa: " + ex.getCause());
			return new BigDecimal(0);
		}
	}

	/**
	 * Converte um numero para BigDecimal
	 *
	 * @param numero
	 *            - double
	 * @return BigDecimal
	 * @since 1.0
	 */
	public static BigDecimal parseBigDecimal(double numero) {
		try {
			return new BigDecimal(numero);
		} catch (NumberFormatException ex) {
			LOG.warn("parseBigDecimal() Erro na convers�o: numero: " + numero + ", erro: " + ex.getMessage()
					+ " , causa: " + ex.getCause());
			return new BigDecimal(0);
		}
	}

	/**
	 * Converte um int para Integer
	 *
	 * @param numero
	 *            - int
	 * @return Integer
	 * @since 1.0
	 */
	public static Integer parseInteger(int numero) {
		try {
			return new Integer(numero);
		} catch (NumberFormatException ex) {
			LOG.warn("parseInteger() Erro na convers�o: numero: " + numero + ", erro: " + ex.getMessage() + " , causa: "
					+ ex.getCause());
			return new Integer(0);
		}
	}

	/**
	 * Sobrecarga do Metodo setScale(BigDecimal, int, int) que seta como
	 * roundMode a constante BigDecimal.ROUND_HALF_UP como default
	 *
	 * @param number
	 *            Instancia de BigDecimal
	 * @param scale
	 *            - Nova escala
	 * @return BigDecimal
	 */
	public static BigDecimal setScale(BigDecimal number, int scale) {
		return setScale(number, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Metodo que seta a escala para um BigDecimal, baseando-se em um roundmode
	 *
	 * @param number
	 * @param scale
	 * @param roundMode
	 * @return
	 */
	public static BigDecimal setScale(BigDecimal number, int scale, int roundMode) {
		if (number == null) // throw new IllegalArgumentException("O argumento
							// n�o pode ser null");
		{
			number = new BigDecimal(0);
		}

		return number.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Metodo que calcula o complemento entre o número dado como base at� o
	 * maximo scale. O Metodo considera adicionar o scale ao maior multiplo de
	 * scale que seja menor que a base.
	 *
	 * @param scale
	 *            - Escala que sera utilizada como refer�ncia para calcular o
	 *            complemento
	 * @param base
	 *            - Valor que contem a base de calculo de complemento
	 * @param adicioneScale
	 *            - Permite adicionar ou nao o scale ao complemento caso o mesmo
	 *            seja igual eh escala
	 *
	 * @return o complemento entre a base e scale e seus multiplos
	 */
	public static int calculeComplemento(int scale, int base, boolean adicioneScale) {
		if ((base == 0)) {
			return scale;
		}
		if (base < scale) {
			return scale - base;
		} else if (base > scale) {
			int qtde = base / scale;
			qtde = qtde * scale; // O número que representa o maior multiplo de
									// scale que � menor que base

			// � permitido adicionar novamente o scale ao valor, quando o base
			// extrapola os multiplos de scale?
			return adicioneScale ? (qtde + scale) - base : ((qtde > base) ? qtde : base) - base;
		} else {
			// Base = Scale --> Adicionar Scale � base?
			return adicioneScale ? (2 * scale) - base : scale - base;
		}
	}

	/**
	 * Retorna um GUID para identificar unicamente cada entidade. O GUID �
	 * baseado num número rand�mico seguro
	 *
	 * @return guid
	 * @since 1.0
	 * @see java.security.SecureRandom.nextLong()
	 */
	public static long generateGUID() {
		long random = new VMID().hashCode();
		try {
			random = SecureRandom.getInstance("SHA1PRNG").nextLong();
		} catch (Exception e) {
			System.err.printf("Message: " + e.getMessage() + "\ncause: " + e.getCause());
		}
		return Math.abs(random);
	}

	/**
	 * Retorna sempre prositivo
	 *
	 * @param valor
	 * @return
	 */
	public static BigDecimal retornaPositivo(BigDecimal valor) {
		BigDecimal valor2 = new BigDecimal(0);
		if (valor != null) {
			if (valor.doubleValue() < 0) {
				valor2 = new BigDecimal(valor.doubleValue()).abs();
			} else {
				valor2 = valor;
			}
		}
		return valor2;
	}

	public static void main(String[] args) {
		// System.out.println("13, 2, true: " + calculeComplemento(13, 2,
		// true));
		// System.out.println("13, 2, false: " + calculeComplemento(13, 2,
		// false));
		// System.out.println("13, 14, false: " + calculeComplemento(13, 14,
		// false));
		// System.out.println("13, 14, true: " + calculeComplemento(13, 14,
		// true));
		// System.out.println("13, 13, false: " + calculeComplemento(13, 13,
		// false));
		// System.out.println("13, 13, true: " + calculeComplemento(13, 13,
		// true));

		System.out.println(NumberUtil.formatDecimal(1345.98, "############.##"));
	}
}
