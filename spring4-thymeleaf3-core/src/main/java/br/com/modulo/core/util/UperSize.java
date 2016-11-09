package br.com.modulo.core.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

 public class UperSize extends PlainDocument {
     private static final long serialVersionUID = 1L;
     private final int iMaxLength;
     private final Boolean toUperCase;

    public UperSize(int maxlen, Boolean upCase) {
         super();
         iMaxLength = maxlen;
         toUperCase = upCase;
     }

     @Override
    public void insertString(int offset, String str, AttributeSet attr)
             throws BadLocationException {
         if (str == null)
            return;

       if (toUperCase) // maiusculo ??
            str = str.toUpperCase();

       if (iMaxLength <= 0) // aceitara qualquer no. de caracteres
         {
            super.insertString(offset, str, attr);
             return;
         }

        int ilen = (getLength() + str.length());
         if (ilen <= iMaxLength) // se o comprimento final for menor...
             super.insertString(offset, str, attr); // ...aceita str
         else {
            if (getLength() == iMaxLength)
                return; // nada a fazer
           String newStr = str.substring(0, (iMaxLength - getLength()));

            super.insertString(offset, newStr, attr);
        }
     }
}