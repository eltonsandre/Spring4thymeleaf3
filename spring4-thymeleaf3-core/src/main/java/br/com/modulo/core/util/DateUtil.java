package br.com.modulo.core.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta classe tem a finalidade de aglutinar metodos utilitarios de formatacao
 * de data, hora, textos, html, xml, etc.
 *
 * @author
 * @version 1.0
 */
public class DateUtil {

	/*
	 * Atributo que define um SimpleDateFormater para datas dd/MM/yyyy
	 */
	private static DateFormat simpleFormater, webFormater;
	/*
	 * Variavel do Log4j
	 */
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	/*
	 * Variavel que define a localizacao default para internacionalizacao
	 */
	private static final Locale DEFAULT_LOCALE = Locale.getDefault();

	/*
	 * Inicializador est�tico da classe, que inicializa os atributos est�ticos
	 */
	static {
		simpleFormater = DateFormat.getDateInstance(DateFormat.MEDIUM, DEFAULT_LOCALE);
		webFormater = new SimpleDateFormat("EEEEEEEEEEEEEE, dd 'de' MMMMMMMMMM 'de' yyyy ", DEFAULT_LOCALE);

	}

	/**
	 * Formata em String uma data (java.util.Date)
	 *
	 * @param date
	 *            - objeto de data a ser formatada
	 * @return representacao em String dd/MM/yyyy da data (DateFormat.MEDIUM),
	 *         ou "" se date for null
	 * @since 1.0
	 */
	public static String formatDate(Date date) {
		String formated;
		if (date == null) {
			formated = "";
		} else {
			formated = simpleFormater.format(date);
		}
		return formated;
	}

	/**
	 * Formata em String uma data (java.util.Date)
	 *
	 * @param date
	 *            - objeto de data a ser formatada
	 * @return representação em String "Segunda, 5 Agosto, 2006"
	 * @throws IllegalArgumentException
	 *             se date for nulo.
	 * @since 1.0
	 */
	public static String formatDateWeb(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Data não pode ser nula!");
		}
		String formated = webFormater.format(date);
		return formated;
	}

	/**
	 * Converte em data (java.util.Date) uma String
	 *
	 * @param date
	 *            - objeto de data em formato String dd/MM/yyyy
	 *            (DateFormat.MEDIUM)
	 * @return objeto de Data
	 * @throws ParseException
	 *             se date não estiver no formato adequado
	 * @throws IllegalArgumentException
	 *             se date for nulo.
	 * @since 1.0
	 */
	public static Date parseDate(String date) throws ParseException {
		if (date == null) {
			throw new IllegalArgumentException("Data não pode ser nula!");
		} else if (date.trim().length() == 0) {
			throw new IllegalArgumentException("Data não pode estar em branco!");
		}
		Date parsed = null;
		try {
			if (date.indexOf('-') >= 0) {
				date = date.replace('-', '/');
			}
			if (date.indexOf('/') >= 4) {
				if (date.length() == 10) { // yyyy/MM/dd
					parsed = DateUtil.parseDate(date, "yyyy/MM/dd");
				}
			} else {
				if (date.length() == 6 && date.indexOf("/") == 1) { // M/yyyy
					parsed = DateUtil.parseDate(date, "M/yyyy");
				} else if (date.length() == 7 && date.indexOf("/") == 2) { // MM/yyyy
					parsed = DateUtil.parseDate(date, "MM/yyyy");
				} else if (date.length() == 8) { // d/M/yyyy
					parsed = DateUtil.parseDate(date, "d/M/yyyy");
				} else if (date.length() == 9 && date.indexOf("/") == 2) { // dd/M/yyyy
					parsed = DateUtil.parseDate(date, "dd/M/yyyy");
				} else if (date.length() == 9 && date.indexOf("/") == 1) { // d/MM/yyyy
					parsed = DateUtil.parseDate(date, "d/MM/yyyy");
				} else if (date.length() == 10) { // dd/MM/yyyy
					parsed = simpleFormater.parse(date);
				} else if (date.length() == 16) { // dd/MM/yyyy HH:mm
					parsed = DateUtil.parseDate(date, "dd/MM/yyyy HH:mm");
				} else if (date.length() == 17 && date.indexOf("/") == 1) { // d/M/yyyy
																			// HH:mm:ss
					parsed = DateUtil.parseDate(date, "d/M/yyyy HH:mm:ss");
				} else if (date.length() == 18 && date.indexOf("/") == 2) { // dd/M/yyyy
																			// HH:mm:ss
					parsed = DateUtil.parseDate(date, "dd/M/yyyy HH:mm:ss");
				} else if (date.length() == 18 && date.indexOf("/") == 1) { // d/MM/yyyy
																			// HH:mm:ss
					parsed = DateUtil.parseDate(date, "d/MM/yyyy HH:mm:ss");
				} else if (date.length() == 19) { // dd/MM/yyyy HH:mm:ss
					parsed = DateUtil.parseDate(date, "dd/MM/yyyy HH:mm:ss");
				} else if (date.length() >= 21) { // dd/MM/yyyy HH:mm:ss.S
					parsed = DateUtil.parseDate(date, "dd/MM/yyyy HH:mm:ss.S");
				} else {
					// caso a data seja invalida, retorna a data atual
					parsed = new Date();
				}
			}

		} catch (ParseException e) {
			logger.warn("Erro no parseDate: data: " + date + ", erro: " + e.getMessage());// +
																							// ",
																							// cause:
																							// "
																							// +
																							// e.getCause());
			parsed = new Date();
		}
		return parsed;
	}

	/**
	 * Formata uma data a partir de um formato customizado no Locale default
	 *
	 * @param date
	 *            - data a ser formatada
	 * @param format
	 *            - ver java.text.SimpleDateFormat sobre os formatos
	 * @return representa��o em String data a partir do formato
	 * @throws IllegalArgumentException
	 *             se date ou formato forem for nulos.
	 * @since 1.0
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			throw new IllegalArgumentException("Data não pode ser nula!");
		}
		if (format == null || format.equals("")) {
			throw new IllegalArgumentException("Formato não pode ser nulo!");
		}
		// Formata a data com o Locale Default (System Regional Settings)
		SimpleDateFormat customFormater = new SimpleDateFormat(format, DEFAULT_LOCALE);
		return customFormater.format(date);
	}

	/**
	 * Formata uma data a partir de um formato customizado no Locale default
	 *
	 * @param dateInString
	 *            - data String a ser formatada para Date
	 * @param format
	 *            - ver java.text.SimpleDateFormat sobre os formatos
	 * @return representa��o em String data a partir do formato
	 * @throws IllegalArgumentException
	 *             se date ou formato forem for nulos.
	 * @since 1.0
	 */
	public static Date stringParseDate(String dateInString, String format) {
		if ("".equals(dateInString)) {
			throw new IllegalArgumentException("Data não pode ser nula!");
		}
		if (format == null || format.equals("")) {
			throw new IllegalArgumentException("Formato não pode ser nulo!");
		}
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException ex) {
			java.util.logging.Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return date;
	}

	/**
	 * Formata uma data a partir de um formato customizado no Locale default
	 *
	 * @param date
	 *            - data a ser formatada em HH:mm
	 * @return representação em String data a partir do formato HH:mm
	 * @throws IllegalArgumentException
	 *             se date ou formato forem for nulos.
	 * @since 1.0
	 */
	public static String formatToStringHoraMin(Date date) {
		if (date == null) {
			return "";// throw new IllegalArgumentException("Data não pode ser
						// nula!");
		}
		// Formata a data com o Locale Default (System Regional Settings)
		SimpleDateFormat customFormater = new SimpleDateFormat("HH:mm", DEFAULT_LOCALE);
		return customFormater.format(date);
	}

	/**
	 * Formata uma data a partir de um formato customizado no Locale default
	 *
	 * @param date
	 *            - data a ser formatada
	 * @return
	 * @throws IllegalArgumentException
	 *             se date ou formato forem for nulos.
	 * @since 1.0
	 */
	public static String formatToString(Date date) {
		String format = "dd/MM/yyyy";
		if (date == null) {
			return "";
		}
		SimpleDateFormat customFormater = new SimpleDateFormat(format, DEFAULT_LOCALE);
		return customFormater.format(date);
	}

	/**
	 * Converte em data (java.util.Date) uma String usando o Locale default
	 *
	 * @param date
	 *            - objeto de data em formato String dd/MM/yyyy
	 * @param format
	 *            * @return objeto de Data
	 * @return Date
	 * @throws ParseException
	 *             se date n�o estiver no formato adequado
	 * @throws IllegalArgumentException
	 *             se date ou format for nulo.
	 * @since 1.0
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		if (date == null) {
			throw new IllegalArgumentException("Data não pode ser nula!");
		}
		if (format == null) {
			throw new IllegalArgumentException("Formato não pode ser nulo!");
		}
		// Formata a data com o Locale Default (SO Regional Settings)
		SimpleDateFormat customFormater = new SimpleDateFormat(format, DEFAULT_LOCALE);
		return customFormater.parse(date);
	}

	/**
	 * Converte uma String em Date usando um SimpleDataFormat
	 *
	 * @param sdf
	 *            - instancia de SimpleDateFormat
	 * @param data
	 *            - String de data
	 * @return date - convertida pelo SimpleDateFormat
	 * @since 1.0
	 */
	public static Date parseDate(SimpleDateFormat sdf, String data) {
		try {
			if ((data.equals("")) || (data.equals(" "))) {
				return null;
			}
			return sdf.parse(data);
		} catch (ParseException e) {
			logger.warn("Erro no parseDate: " + data + ", erro: " + e.getMessage());// +
																					// ",
																					// cause:
																					// "
																					// +
																					// e.getCause());
			return new Date();
		}
	}

	/**
	 * Formata uma data Date usando um SimpleDataFormat
	 *
	 * @param sdf
	 *            - instancia de SimpleDateFormat
	 * @param data
	 *            - instancia de Date
	 * @return String - formatada pelo SimpleDateFormat
	 * @since 1.0
	 */
	public static String formatDate(SimpleDateFormat sdf, Date data) {
		if (data != null) {
			return "'" + sdf.format(data) + "'";
		} else {
			return "";
		}
	}

	/**
	 * Formata uma data Date usando um SimpleDataFormat
	 *
	 * @param sdf
	 *            - instancia de SimpleDateFormat
	 * @param data
	 *            - instancia de Date
	 * @return String - formatada pelo SimpleDateFormat
	 * @since 1.0
	 */
	public static String formatDateOnTranslator(SimpleDateFormat sdf, Date data) {
		if (data != null) {
			return sdf.format(data);
		} else {
			return "";
		}
	}

	/**
	 * Retorna o numero de dias entre as duas datas
	 *
	 * @param data1
	 * @param data2
	 * @return numero de dias
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static int calculaNumeroDias(Date data1, Date data2) {
		if (data1 == null || data2 == null) {
			throw new IllegalArgumentException("Datas inválidas!");
		}
		// if ( data2.before( data1 ) ) {
		// throw new IllegalArgumentException("Data2 deve ser posterior a
		// data1!");
		// }
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data1);

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(data2);

		int days = 0;
		while (cal1.before(cal2)) {
			cal1.add(Calendar.DAY_OF_YEAR, 1);
			days++;
		}
		return days;
	}

	/**
	 * Retorna o data com hora adicionada
	 *
	 * @param data
	 * @param hora
	 * @return Date + hora adicionada
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static Date adicionaHoras(Date data, int hora) {
		if (data == null) {
			throw new IllegalArgumentException("Data inválida!");
		}
		if (hora <= 0) {
			throw new IllegalArgumentException("Hora deve ser maior a 0!");
		}
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data);

		cal1.add(Calendar.HOUR_OF_DAY, hora);
		return cal1.getTime();
	}

	/**
	 * Retorna o data com hora adicionada
	 *
	 * @param data
	 * @param minutos
	 * @return Date + hora adicionada
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static Date adicionaMinutos(Date data, int minutos) {
		if (data == null) {
			throw new IllegalArgumentException("Data inválida!");
		}
		if (minutos <= 0) {
			throw new IllegalArgumentException("Hora deve ser maior a 0!");
		}
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data);

		cal1.add(Calendar.MINUTE, minutos);
		return cal1.getTime();
	}

	/**
	 * Retorna o data com hora adicionada
	 *
	 * @param data
	 * @param minutos
	 * @return Date + hora adicionada
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static Date subtraiMinutos(Date data, int minutos) {
		if (data == null) {
			throw new IllegalArgumentException("Data inválida!");
		}
		if (minutos <= 0) {
			throw new IllegalArgumentException("Hora deve ser maior a 0!");
		}
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data);

		cal1.add(Calendar.MINUTE, -minutos);
		return cal1.getTime();
	}

	/**
	 * Retorna o numero de dias entre as duas datas
	 *
	 * @param data
	 * @param diasAfter
	 * @return Date com dia adicionado
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static Date adicionaDias(Date data, int diasAfter) {
		if (data == null) {
			throw new IllegalArgumentException("Data inválida!");
		}
		if (diasAfter <= 0) {
			throw new IllegalArgumentException("Dias deve ser posterior a data!");
		}
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data);

		cal1.add(Calendar.DAY_OF_YEAR, diasAfter);
		return cal1.getTime();
	}

	/**
	 * Retorna uma data com menos dias
	 *
	 * @param data
	 * @param diasBefore
	 * @return cal1.getTime() com menos dias
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static Date subtraiDias(Date data, int diasBefore) {
		if (data == null) {
			throw new IllegalArgumentException("Data inválida!");
		}
		if (diasBefore < 0) {
			throw new IllegalArgumentException("Dias \"diasBefore\"deve ser positivo!");
		}
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data);

		cal1.add(Calendar.DAY_OF_YEAR, -diasBefore);
		return cal1.getTime();
	}

	/**
	 * Retorna o numero de dias entre as duas datas
	 *
	 * @param data
	 * @param ano
	 * @return numero de anos
	 * @throws IllegalArgumentException
	 *             se os parametros nao forem respeitados
	 * @since 1.0
	 */
	public static Date adicionaAnos(Date data, int ano) {
		if (data == null) {
			throw new IllegalArgumentException("Datas inválidas!");
		}
		if (ano <= 0) {
			throw new IllegalArgumentException("O Ano deve ser posterior a data!");
		}
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(data);

		cal1.add(Calendar.YEAR, ano);
		return cal1.getTime();
	}

	/**
	 * Converte uma data (java.util.Date) em SQL DATE ( java.sql.Date )
	 *
	 * @param data
	 *            - inst�ncia de java.util.Date
	 * @return SQL-DATE - inst�ncia de java.sql.Date
	 * @since 1.0
	 * @throws IllegalArgumentException
	 *             se o data for nulo
	 *
	 */
	public static java.sql.Timestamp convertAllToSQL(java.util.Date data) {
		if (data == null) {
			throw new IllegalArgumentException("Impossivel converte, data esta nula");
		}
		return new java.sql.Timestamp(data.getTime());
	}

	/**
	 * Converte uma data (java.util.Date) em SQL DATE ( java.sql.Date )
	 *
	 * @param data
	 *            - instancia de java.util.Date
	 * @return SQL-DATE - instancia de java.sql.Date
	 * @since 1.0
	 * @throws IllegalArgumentException
	 *             se o data for nulo
	 *
	 */
	public static java.sql.Date convertToSQL(java.util.Date data) {
		if (data == null) {
			throw new IllegalArgumentException("Impossivel converte data nula");
		}
		return new java.sql.Date(data.getTime());
	}

	/**
	 * Subtrai em horas a Data Inicial da Data Final
	 *
	 * @param dataInicial
	 *            - objeto de data valido como horario inicial
	 * @param dataFinal
	 *            - objeto de data valido como horario final
	 * @param precisao
	 *            - numero de casas decimais para precisao de c�lculo
	 * @return BigDecimal com as horas de diferença ( arredondamento de acordo
	 *         com a precisao )
	 * @throws IllegalArgumentException
	 *             se alguma das datas for null
	 */
	public static BigDecimal subtraiHoras(Date dataInicial, Date dataFinal, int precisao) {
		if (dataInicial == null) {
			throw new IllegalArgumentException("Hora inicial nao pode ser nula");
		}
		if (dataFinal == null) {
			throw new IllegalArgumentException("Hora final nao pode ser nula");
		}
		if (precisao < 0) {
			throw new IllegalArgumentException("Precisão invalida, nao pode ser negativa");
		}
		double timeInicial = dataInicial.getTime();
		double timeFinal = dataFinal.getTime();

		BigDecimal init = new BigDecimal(timeInicial);
		BigDecimal end = new BigDecimal(timeFinal);
		BigDecimal diff = end.subtract(init);
		BigDecimal diffHours = diff.divide(new BigDecimal(1000 * 60 * 60), precisao, BigDecimal.ROUND_HALF_EVEN);

		return (diffHours);
	}

	/**
	 * Subtrai em dias a Data Inicial da Data Final
	 *
	 * @param dataInicial
	 *            - objeto de data valido como horario inicial
	 * @param dataFinal
	 *            - objeto de data valido como horario final
	 * @param precisao
	 *            - numero de casas decimais para precisao de c�lculo
	 * @return BigDecimal com os dias de diferen�a ( arredondamento de acordo
	 *         com a precisao )
	 * @throws IllegalArgumentException
	 *             se alguma das datas for null
	 */
	public static BigDecimal subtraiDias(Date dataInicial, Date dataFinal, int precisao) {
		BigDecimal horas = subtraiHoras(dataInicial, dataFinal, precisao);
		return horas.divide(new BigDecimal(24), precisao, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * Calcula uma data relativa a data incial dependendo do numero de dias e do
	 * tempo
	 *
	 * @param inicial
	 *            - data incial
	 * @param dias
	 *            - numero de dias ( positivo = futuro, negativo = passado )
	 * @return date
	 */
	public static Date calculaDataRelativa(Date inicial, int dias) {
		GregorianCalendar atual = new GregorianCalendar();
		atual.setTime(inicial);
		if (Math.abs(dias) > 30) {
			atual.roll(Calendar.DAY_OF_YEAR, dias);
		} else {
			atual.roll(Calendar.DATE, dias);
		}
		return new Date(atual.getTime().getTime());
	}

	/**
	 *
	 * @param dataInicial
	 * @param meses
	 * @return
	 */
	public static Date subtraiMeses(Date dataInicial, int meses) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(dataInicial);
		for (int i = 0; i < meses; i++) {
			calendar.add(Calendar.MONTH, -1);
		}
		return calendar.getTime();
	}

	/**
	 * Método que retorno a quantidade de dias para dado mês/ano Utiliza o
	 * método getCalendar para inicializar o calendário e assim recuperar o
	 * número de dias para o mês/ano indicado
	 *
	 * @param month
	 *            - mês que deseja-se saber o número de dias
	 * @param year
	 *            - ano que deseja-se saber o número de dias
	 * @return n�mero de dias para o mês/ano informado
	 */
	public static int getQuantidadeDias(String month, String year) {
		return getCalendar(month, year).getActualMaximum(Calendar.DATE);
	}

	/**
	 * Devolve uma lista de mes/ano baseadas nas datas de incio e fim
	 *
	 * @param inicio
	 *            mes/ano de inicio
	 * @param fim
	 *            mes/ano de fim
	 * @return List
	 */
	public static List<String> criaMesAno(String inicio, String fim) {
		List<String> datas = new ArrayList<String>();
		int anoInicial = Integer.parseInt(inicio.substring(inicio.indexOf("/") + 1, inicio.length()));
		int anoFinal = Integer.parseInt(fim.substring(fim.indexOf("/") + 1, fim.length()));
		int mesInicial = Integer.parseInt(inicio.substring(0, inicio.indexOf("/")));
		int mesFinal = Integer.parseInt(fim.substring(0, fim.indexOf("/")));
		do {
			do {
				datas.add(mesInicial + "/" + anoInicial);
				mesInicial++;
			} while ((anoInicial != anoFinal && mesInicial <= 12)
					|| (anoInicial == anoFinal && mesInicial <= mesFinal));
			mesInicial = 1;
			anoInicial++;
		} while (anoInicial <= anoFinal);
		return datas;
	}

	/**
	 * Método que retorno o objeto Calendar inicializando de acordo com os
	 * parâmetros informados
	 *
	 * @param month
	 *            - Mês com o qual o objeto deve ser inicializado (1..12)
	 * @param year
	 *            - Ano com o qual o objeto deve ser inicializado
	 * @return Objeto Calendar com a refer�ncia para o mês/ano passado como
	 *         argumento
	 */
	public static Calendar getCalendar(String month, String year) {
		int mes, ano;
		try {
			mes = Integer.parseInt(month) - 1;
			ano = Integer.parseInt(year);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Impossível converter mês/ano.");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(ano, mes, 1);
		return calendar;
	}

	/**
	 * Metodo que retorno um List com duas posicoes contendo a menor e a maior
	 * data para dado mes/ano
	 *
	 * @param month
	 *            - mes de refer�ncia
	 * @param year
	 *            - ano de refer�ncia
	 * @return list com duas posições contendo a menor e a maior data para dado
	 *         mes/ano list.get(0) : Menor data de dado mes/ano list.get(1) :
	 *         Maior data de dado mes/ano
	 */
	public static List<String> getMenorMaiorData(String month, String year) {
		Calendar calendar = getCalendar(month, year);

		String base = format(calendar.getTime(), "dd/MM/yyyy");
		String dataInicial = "01" + base.substring(2, base.length());
		String dataFinal = calendar.getActualMaximum(Calendar.DATE) + base.substring(2, base.length());

		List<String> retorno = new ArrayList<String>();
		retorno.add(0, dataInicial);
		retorno.add(1, dataFinal);
		return retorno;
	}

	/**
	 * Metodo que retorno um List com duas posicoes contendo o mes e o ano para
	 * data String de entrada no formado (dd/mm/aaaa)
	 *
	 * @param data
	 *            - String que representa a data no formado (dd/mm/aaaa)
	 * @return list com duas posicoes contendo o mes e o ano para dada data de
	 *         entrada list.get(0) : Mes list.get(1) : Ano
	 */
	public static List<String> getMesAno(String data) {
		String tData[] = data.split("/");
		if (tData.length != 3) {
			throw new IllegalArgumentException("Data inválida. dd/mm/aaaa");
		}

		List<String> retorno = new ArrayList<String>();
		retorno.add(0, tData[1]);
		retorno.add(1, tData[2]);
		return retorno;
	}

	/**
	 * Retorna o nome do mes referente a data informada
	 *
	 * @param date
	 *            - instancia de java.util.Date
	 * @return nome do mes
	 */
	public static String getNomeDoMes(Date date) {
		return new java.text.SimpleDateFormat("MMMMMMMMMMMMMM", new java.util.Locale("pt_BR")).format(date);
	}

	/**
	 * M�todo que recebe uma data e retorna a data que representa o primeiro dia
	 * para a semana atual da data passada como argumento
	 *
	 * @param data
	 * @param primeiroDiaDaSemana
	 * @return data que representa o primeir
	 */
	public static Date getPrimeiroDiaDaSemana(Date data, int primeiroDiaDaSemana) {
		if (data == null) {
			throw new IllegalArgumentException("A data não pode ser nula");
		}

		Date newDate = (Date) data.clone();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(newDate);

		calendar.setFirstDayOfWeek(primeiroDiaDaSemana);

		// Subtrai o dia corrente da semana (1..7) do primeiro dia da semana
		// (1..7)
		int diferencaDias = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();

		// Caso a diferen�a de dias esteja menor que zero, significa que o
		// primeiro dia da
		// semana est� � frente da data que foi passada como argumento
		// portanto soma-se 7 dias e estaremos nos referenciando � semana
		// correta
		diferencaDias = (diferencaDias < 0) ? (diferencaDias + 7) : diferencaDias;

		// O dia do m�s recebe o dia atual - diferen�a de dias da semana
		int day = calendar.get(Calendar.DATE) - diferencaDias;

		calendar.set(Calendar.DATE, day);
		newDate.setTime(calendar.getTimeInMillis());

		return newDate;
	}

	/**
	 * Método que recebe uma data e retorna a data que representa o primeiro dia
	 * para a semana atual da data passada como argumento
	 *
	 * @param date
	 * @param firstDayOfWeek
	 * @return data que representa o primeiro dia da semana referente a data
	 *         passada como argumento
	 */
	public static Date getPrimeiroDiaDaSemanaNoMesAtual(Date date, int firstDayOfWeek) {
		if (date == null) {
			throw new IllegalArgumentException("A data n�o pode ser nula");
		}

		Date newDate = (Date) date.clone();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(newDate);

		calendar.setFirstDayOfWeek(firstDayOfWeek);

		// Subtrai o dia corrente da semana (1..7) do primeiro dia da semana
		// (1..7)
		int diferencaDias = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();

		// Caso a diferen�a de dias esteja menor que zero, significa que o
		// primeiro dia da
		// semana est� � frente da data que foi passada como argumento
		// portanto soma-se 7 dias e estaremos nos referenciando � semana
		// correta
		diferencaDias = (diferencaDias < 0) ? (diferencaDias + 7) : diferencaDias;

		// O dia do m�s recebe o dia atual - diferen�a de dias da semana
		int day = calendar.get(Calendar.DATE) - diferencaDias;

		calendar.set(Calendar.DATE, day);
		newDate.setTime(calendar.getTimeInMillis());

		// Encontra a menor data do m�s
		Date lessDateOnAtualMonth = getPrimeiroDiaDoMes(date);

		// Caso o primeiro dia da semana seja anterior a primeira data do m�s
		// (dia 01)
		// � retornado o primeiro dia do m�s
		if (newDate.before(lessDateOnAtualMonth)) {
			return lessDateOnAtualMonth;
		} else {
			return newDate;
		}
	}

	/**
	 * Sobrecarga do m�todo getPrimeiroDiaDaSemana, onde o primeiro dia da
	 * semana � considerado segunda-feira
	 *
	 * @param date
	 * @return data que representa o primeiro dia da semana referente a data
	 *         passada como argumento
	 */
	public static Date getPrimeiroDiaDaSemana(Date date) {
		return DateUtil.getPrimeiroDiaDaSemana(date, Calendar.MONDAY);
	}

	/**
	 * Sobrecarga do m�todo getPrimeiroDiaDaSemana, onde o primeiro dia da
	 * semana � considerado segunda-feira
	 *
	 * @param date
	 * @return data que representa o primeiro dia da semana referente a data
	 *         passada como argumento
	 */
	public static Date getPrimeiroDiaDaSemanaNoMesAtual(Date date) {
		return getPrimeiroDiaDaSemanaNoMesAtual(date, Calendar.MONDAY);
	}

	/**
	 * Retorna o primeiro dia do mes
	 *
	 * @param date
	 * @return primeiro dia do mes da data informada
	 */
	public static Date getPrimeiroDiaDoMes(Date date) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.set(Calendar.DAY_OF_MONTH, 1);

		return calendar1.getTime();
	}

	/**
	 * Retorna a diferenca entre duas datas
	 *
	 * @param date1
	 * @param date2
	 * @param razao
	 *            ( 60 / 24 )
	 * @return diferenca
	 */
	public static long getDiferencaDatas(Date date1, Date date2, int razao) {
		long diferenca = (long) ((date2.getTime() - date1.getTime()) / razao);
		return Math.abs(diferenca);
	}

	/**
	 * @return o primeiro dia do ano corrente
	 */
	public static Date getPrimeiroDiaDoAno() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * Retorna a diferenca em dias entre as duas datas
	 *
	 * @param date1
	 *            - Data Inicial
	 * @param date2
	 *            - Data Final
	 * @return diferenca de dias entre uma data e outra
	 */
	public static long getDiferencaDatas(Date date1, Date date2) {
		return (long) ((date1.getTime() - date2.getTime()) / 86400000L);
	}

	/**
	 * Retorna o nome mes segundo o valor do mes
	 *
	 * @param value
	 * @return nome do mes informado
	 */
	public static String getNomeDoMes(String value) {
		String mesDesc = null;
		if ("01".equalsIgnoreCase(value)) {
			mesDesc = "Janeiro";
		} else if ("02".equalsIgnoreCase(value)) {
			mesDesc = "Fevereiro";
		} else if ("03".equalsIgnoreCase(value)) {
			mesDesc = "Março";
		} else if ("04".equalsIgnoreCase(value)) {
			mesDesc = "Abril";
		} else if ("05".equalsIgnoreCase(value)) {
			mesDesc = "Maio";
		} else if ("06".equalsIgnoreCase(value)) {
			mesDesc = "Junho";
		} else if ("07".equalsIgnoreCase(value)) {
			mesDesc = "Julho";
		} else if ("08".equalsIgnoreCase(value)) {
			mesDesc = "Agosto";
		} else if ("09".equalsIgnoreCase(value)) {
			mesDesc = "Setembro";
		} else if ("10".equalsIgnoreCase(value)) {
			mesDesc = "Outubro";
		} else if ("11".equalsIgnoreCase(value)) {
			mesDesc = "Novembro";
		} else if ("12".equalsIgnoreCase(value)) {
			mesDesc = "Dezembro";
		}
		return mesDesc;
	}

	/**
	 * Retorna o ultimo dia do mes
	 *
	 * @param date
	 * @return int
	 */
	public static int getUltimoDiaDoMes(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Retorna data hora final do mes completa DD/MM/YYYY 23:59:59
	 *
	 * @param date
	 * @return
	 */
	public static Date getDataFinalDoMes(Date date) {

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.set(Calendar.DAY_OF_MONTH, getUltimoDiaDoMes(date)); // zerando
																		// as
																		// horas,
																		// minuots
																		// e
																		// segundos..
		calendar1.set(Calendar.HOUR_OF_DAY, 23); // zerando as horas, minuots e
													// segundos..
		calendar1.set(Calendar.MINUTE, 59);
		calendar1.set(Calendar.SECOND, 59);
		calendar1.set(Calendar.MILLISECOND, 59);

		return calendar1.getTime();
	}

	/**
	 * Devolve um Date
	 *
	 * @param dateInicial
	 *            Data a ser inicialda
	 * @return Date calendar1.getTime() Data a ser inicialda;
	 */
	public static Date getHoraInicialDaData(Date dateInicial) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(dateInicial);
		calendar1.set(Calendar.HOUR_OF_DAY, 00); // zerando as horas, minuots e
													// segundos..
		calendar1.set(Calendar.MINUTE, 00);
		calendar1.set(Calendar.SECOND, 00);

		return calendar1.getTime();
	}

	/**
	 * Devolve um Date
	 *
	 * @param date
	 *            Data a ser finalizada
	 * @return Date calendar1.getTime() Data a ser finalizada;
	 */
	public static Date getHoraFinalDaData(Date date) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.set(Calendar.HOUR_OF_DAY, 23); // zerando as horas, minuots e
													// segundos..
		calendar1.set(Calendar.MINUTE, 59);
		calendar1.set(Calendar.SECOND, 59);

		return calendar1.getTime();
	}

	/**
	 * Devolve um Date
	 *
	 * @param dataIncial
	 * @param dataFinal
	 * @return boolean se data inicial e final for integral das 00:00 as 23:59;
	 */
	public static boolean isDiaInteiro(Date dataIncial, Date dataFinal) {
		if (dataIncial.equals(getHoraInicialDaData(dataIncial))) {
			if (dataFinal.equals(getHoraFinalDaData(dataFinal))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devolve um conjunto array de periodos, onde a posicao 0 e inicial e a
	 * posicao 1 e o final e a primeira linha � o periodo completo
	 *
	 * @param dataInicial
	 * @param dataFinal
	 * @param format
	 * @return String[][]
	 * @throws IllegalArgumentException
	 *             caso algum argumeto esteja incorreto
	 */
	public static String[][] criaPeriodos(Date dataInicial, Date dataFinal, String format) {

		String inicio = new SimpleDateFormat("MM/yyyy").format(dataInicial);
		String fim = new SimpleDateFormat("MM/yyyy").format(dataFinal);
		List<String> meses = criaMesAno(inicio, fim);

		String[][] periodos = null;

		if (meses.size() > 0) {
			periodos = new String[meses.size() + 1][2];
			try {
				periodos[0][0] = format(parseDate(inicio), format);
				Date end = parseDate(fim);
				end = getDataFinalDoMes(end);
				periodos[0][1] = format(end, format);
			} catch (ParseException e) {
				throw new IllegalArgumentException(
						"Impossivel criar meses para as datas " + dataInicial + " até " + dataFinal, e);
			}
			for (int i = 0; i < meses.size(); i++) {
				String data = meses.get(i).toString();
				try {
					Date date = parseDate(data);
					Date date2 = getDataFinalDoMes(date);
					periodos[i + 1][0] = format(date, format);
					periodos[i + 1][1] = format(date2, format);
				} catch (ParseException e) {
					throw new IllegalArgumentException(
							"Impossivel criar meses para as datas " + dataInicial + " até " + dataFinal, e);
				}
			}
		} else {
			throw new IllegalArgumentException(
					"Impossivel criar meses para as datas " + dataInicial + " até " + dataFinal);
		}

		return periodos;
	}

	/**
	 * Devolve um conjunto array de periodos, onde a posicao 0 e inicial e a
	 * posicao 1 e o final e a primeira linha � o periodo completo
	 *
	 * @param dataInicial
	 *            - MM/yyyy
	 * @param dataFinal
	 *            - MM/yyyy
	 * @param format
	 * @return String[][]
	 * @throws IllegalArgumentException
	 *             caso algum argumeto esteja incorreto
	 */
	public static String[][] criaPeriodos(String dataInicial, String dataFinal, String format) {

		String inicio = dataInicial;
		String fim = dataFinal;
		List<String> meses = criaMesAno(inicio, fim);

		String[][] periodos = null;

		if (meses.size() > 0) {
			periodos = new String[meses.size() + 1][2];
			try {
				periodos[0][0] = format(parseDate(inicio), format);
				Date end = parseDate(fim);
				end = getDataFinalDoMes(end);
				periodos[0][1] = format(end, format);
			} catch (ParseException e) {
				throw new IllegalArgumentException(
						"Impossivel criar meses para as datas " + dataInicial + " até " + dataFinal, e);
			}
			for (int i = 0; i < meses.size(); i++) {
				String data = meses.get(i).toString();
				try {
					Date date = parseDate(data);
					Date date2 = getDataFinalDoMes(date);
					periodos[i + 1][0] = format(date, format);
					periodos[i + 1][1] = format(date2, format);
				} catch (ParseException e) {
					throw new IllegalArgumentException(
							"Impossivel criar meses para as datas " + dataInicial + " até " + dataFinal, e);
				}
			}
		} else {
			throw new IllegalArgumentException(
					"Impossivel criar meses para as datas " + dataInicial + " até " + dataFinal);
		}

		return periodos;
	}

	public static List<String> criaMeses(String dataInicial, String dataFinal) throws Exception {
		List<String> meses = new ArrayList<String>();
		String[][] periodos = criaPeriodos(parseDate(dataInicial), parseDate(dataFinal), "MM/yyyy");
		for (int i = 1; i < periodos.length; i++) {
			meses.add(periodos[i][0]);
		}
		return meses;
	}

	/**
	 * Devolve um Date
	 *
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2 - MM/yyyy
	 * @return Date calendar1.getTime();
	 */
	public static Date substituiHoraMin(java.util.Date date1, java.util.Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		calendar1.setTime(date1);
		calendar2.setTime(date2);

		calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY)); // zerando
																					// as
																					// horas,
																					// minuots
																					// e
																					// segundos..
		calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);

		return calendar1.getTime();
	}

	/**
	 * Devolve um Date com segundos alterado conforme paramentro int
	 *
	 * @param date
	 *            java.util.Date()
	 * @param segundo
	 *            int -
	 * @return Date calendar1.getTime();
	 */
	public static Date substituiSegundos(java.util.Date date, int segundo) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.set(Calendar.SECOND, segundo);
		return calendar1.getTime();
	}

	/**
	 * Devolve um Date com segundos alterado conforme paramentro int
	 *
	 * @param date
	 *            java.util.Date()
	 * @param mileSegundos
	 *            int -
	 * @return Date calendar1.getTime();
	 */
	public static Date substituiMileSegundos(java.util.Date date, int mileSegundos) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar1.set(Calendar.MILLISECOND, mileSegundos);
		return calendar1.getTime();
	}

	/**
	 * Devolve um Date
	 *
	 *
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date - MM/yyyy
	 * @param seg
	 *            int
	 * @return Date calendar1.getTime();
	 */
	public static Date alterSecondForDate(java.util.Date date1, java.util.Date date2, int seg) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR));// calendar2.HOUR_OF_DAY);
																			// //zerando
																			// as
																			// horas,
																			// minuots
																			// e
																			// segundos..
		calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));// calendar2.MINUTE);
		calendar1.set(Calendar.SECOND, seg);

		return calendar1.getTime();
	}

	/**
	 * Devolve um Date
	 *
	 * @param data
	 *            Date para alterar hora, minutos e segundos
	 * @param hora
	 *            int para horas
	 * @param min
	 *            int para minutos
	 * @param seg
	 *            int para segundos
	 * @return Date calendar1.getTime() ;
	 */
	public static Date alterTimeForDate(java.util.Date data, int hora, int min, int seg) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);

		calendar.set(Calendar.HOUR_OF_DAY, hora); // zerando as horas, minuots e
													// segundos..
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, seg);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Devolve um Date
	 *
	 * @param data
	 *            Date para alterar hora, minutos e segundos
	 * @param milli
	 *            int para MILLISECOND
	 * @return Date calendar1.getTime() ;
	 */
	public static Date alterMilliSecondForDate(java.util.Date data, int milli) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.MILLISECOND, milli);

		return calendar.getTime();
	}

	public static String setDateManually(int ano, int mes, int dias, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Calendar calendar = new GregorianCalendar(ano, mes, dias);

		calendar.add(Calendar.YEAR, ano);
		calendar.add(Calendar.MONTH, mes);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return sdf.format(calendar.getTime());
	}

	/**
	 * gera data manual Devolve um Date
	 *
	 * @param ano
	 * @param mes
	 * @param dias
	 * @return Date calendar.getTime() ;
	 */
	public static Date setDateManually(int ano, int mes, int dias) {
		Calendar calendar = new GregorianCalendar(ano, mes, dias);
		calendar.add(Calendar.YEAR, ano);
		calendar.add(Calendar.MONTH, mes);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return calendar.getTime();
	}

	public static long divideHorasPorMinutos(String hora, int minutos) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		long result = (getMinutos(hora, formatter)) / minutos;
		return result;
	}

	public static String addOrSubHora(String hora, String hora2, boolean isAdd) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		long result;
		if (isAdd) {
			result = (getMinutos(hora, formatter) + getMinutos(hora2, formatter)) * 60 * 1000;
		} else {
			result = (getMinutos(hora, formatter) - getMinutos(hora2, formatter)) * 60 * 1000;
		}
		Date data = new Date(result);
		return formatter.format(data);
	}

	public static Date addOrSubHora(Date date, String hora, boolean isAdd) {
		String h = format(date, "HH:mm");
		h = addOrSubHora(h, hora, true);

		return stringParseDate(h, "HH:mm");
	}

	/**
	 * Retorna minutos de time formato HH:mm de um Date
	 *
	 * @param hora
	 * @param formatter
	 * @return Date calendar.getTime() ;
	 */
	public static long getMinutos(String hora, SimpleDateFormat formatter) {
		Date data;
		try {
			data = formatter.parse(hora);
		} catch (ParseException e) {
			return 0;
		}
		long minutos = data.getTime() / 1000 / 60;
		return minutos;
	}

	public static void main(String args[]) throws Exception {

		// vamos obter a data e hora atual
		Calendar agora = Calendar.getInstance();

		System.out.println("Semana do ano é: " + agora.get(Calendar.WEEK_OF_YEAR));
		System.out.println("Semana do ano é: " + agora.get(Calendar.DATE));
	}
}
