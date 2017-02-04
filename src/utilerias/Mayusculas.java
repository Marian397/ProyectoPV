/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Nestor
 */
public class Mayusculas extends PlainDocument{
    
    @Override
    public void insertString(int inicio, String texto, AttributeSet atributos) throws BadLocationException{
        super.insertString(inicio, texto.toUpperCase(), atributos);
    }
}
