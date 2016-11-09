package br.com.modulo.core.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class UpOrLowerSize extends PlainDocument {

	private static final long serialVersionUID = 1L;

	public static boolean LOWER_CASE = false;
	public static boolean UP_CASE = true;
	private int iMaxLength;
	private boolean isEnable;
	private boolean upOrLower;

	/**
	 * @param maxlen
	 *            tamanho maximo da String
	 * @param isUp
	 *            UpOrLowerSize.LOWER_CASE converte em minusculas e
	 *            UpOrLowerSize.UP_CASE em maiusculas
	 */
	public UpOrLowerSize(int maxlen, boolean isUp) {
		super();
		this.iMaxLength = maxlen;
		this.isEnable = true;
		this.upOrLower = isUp;
	}

	/**
	 * @param maxlen
	 *            tamanho maximo da String
	 */
	public UpOrLowerSize(int maxlen) {
		super();
		this.iMaxLength = maxlen;
		this.isEnable = false;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}

		if (isEnable) {
			if (upOrLower) {// maiusculo ??
				str = str.toUpperCase();
			} else {
				str = str.toLowerCase();
			}
		}

		if (iMaxLength <= 0) { // aceitara qualquer no. de caracteres
			super.insertString(offset, str, attr);
			return;
		}

		int ilen = (getLength() + str.length());
		if (ilen <= iMaxLength) { // se o comprimento final for menor...
			super.insertString(offset, str, attr); // ...aceita str
		} else {
			if (getLength() == iMaxLength) {
				return; // nada a fazer
			}
			String newStr = str.substring(0, (iMaxLength - getLength()));
			super.insertString(offset, newStr, attr);
		}
	}
}