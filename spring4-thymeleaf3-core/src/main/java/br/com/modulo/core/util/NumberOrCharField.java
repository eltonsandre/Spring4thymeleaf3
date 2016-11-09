package br.com.modulo.core.util;

import static java.lang.String.valueOf;
import static java.lang.System.err;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberOrCharField extends PlainDocument {

    public static boolean NUMBER = true;
    public static boolean CARACTER = false;
    private static final long serialVersionUID = 1L;
    private final int iMaxLength;
    private final boolean numberOrChar;

    public NumberOrCharField(int iMaxLength, boolean numberOrChar) {
        super();
        this.numberOrChar = numberOrChar;
        this.iMaxLength = iMaxLength;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        try {
            if (numberOrChar) {
                for (int i = 0; i < str.length(); i++) {
                    if (!ValidaCampo.numero(valueOf(str.charAt(i)))) {
                        str = str.replaceAll(valueOf(str.charAt(i)), ".".equals(valueOf(str.charAt(i))) ? "." : "");
                    }
                }
            } else {
                for (int i = 0; i < str.length(); i++) {
                    if (!ValidaCampo.nome(valueOf(str.charAt(i)))) {
                        str = str.replaceAll(valueOf(str.charAt(i)), ".".equals(valueOf(str.charAt(i))) ? "." : "");
                    }
                }
            }
        } catch (java.util.regex.PatternSyntaxException pse) {
            err.println("Erro: NumeberOrCharField: " + pse.getMessage() + " \nCause: " + pse.getCause());
        }
        if (iMaxLength <= 0) {// aceitara qualquer no. de caracteres
            super.insertString(offset, str, attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength) {// se o comprimento final for menor...
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
