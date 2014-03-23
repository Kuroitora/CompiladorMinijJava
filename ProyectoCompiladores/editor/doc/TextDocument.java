/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package doc;

/**
 *
 * @author Pedro Luis
 */

import editor.PanelVentanas;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.*;
import javax.swing.*;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class TextDocument extends JTextArea 
    implements CaretListener, ActionListener, KeyListener
{
    private JPopupMenu menuContex; //Creamos un menu contextual
    private JMenuItem edit[]; 
    private Integer lengthFont;//Tamaño de fuente
    private boolean state;//Estado [Guardado/No guardado] del fichero abierto
    private PanelVentanas parent;
    
    public TextDocument(PanelVentanas parent)
    {
        super();
        this.initTexDocument();
        this.parent = parent;
    }

    private void initTexDocument()
    {
        this.lengthFont = 15;
        this.state = false;
        this.setMargin(new Insets(25, 25, 25, 25));
        this.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, lengthFont));
        this.initMenuContext();
        this.setComponentPopupMenu(menuContex);
        this.addKeyListener(this);
        this.addCaretListener(this);
    }

    private void initMenuContext()
    {
        menuContex = new JPopupMenu();
        menuContex.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        edit = new JMenuItem[4];

        edit[0] = new JMenuItem("Copiar");
        edit[0].setActionCommand("Copiar");
        edit[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        edit[0].setIcon(new ImageIcon(getClass().getResource("/iconos/Copy16.gif")));
        edit[0].addActionListener(this);

        edit[1] = new JMenuItem("Cortar");
        edit[1].setActionCommand("Cortar");
        edit[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        edit[1].setIcon(new ImageIcon(getClass().getResource("/iconos/Cut16.gif")));
        edit[1].addActionListener(this);

        edit[2] = new JMenuItem("Pegar");
        edit[2].setActionCommand("Pegar");
        edit[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        edit[2].setIcon(new ImageIcon(getClass().getResource("/iconos/Paste16.gif")));
        edit[2].addActionListener(this);

        edit[3] = new JMenuItem("Seleccionar todo");
        edit[3].setActionCommand("Seleccionar todo");
        edit[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        edit[3].addActionListener(this);

        menuContex.add(edit[0]); menuContex.add(edit[1]); menuContex.add(edit[2]);
        menuContex.addSeparator();menuContex.add(edit[3]);
    }

    public void setLengthFont(Integer lengthFont)
    {
        this.lengthFont = lengthFont;
        this.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, lengthFont));
    }

    public Integer getLengthFont()
    {
        return lengthFont;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }

    public boolean getState()
    {
        return state;
    }
    
    private boolean isEdited(KeyEvent e)
    {
        return !e.isActionKey() && !e.isAltDown() &&
               !e.isAltGraphDown() && !e.isControlDown() &&
               !e.isShiftDown();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().compareTo("Copiar")==0)
        {
            if(this.getSelectedText()!=null)
               this.copy();
        }
        else if(e.getActionCommand().compareTo("Cortar")==0)
        {
            if(this.getSelectedText()!=null)
            {
                this.cut(); state = false;
            }
        }
        else if(e.getActionCommand().compareTo("Pegar")==0)
        {
            this.paste(); state = false;
        }
        else if(e.getActionCommand().compareTo("Seleccionar todo")==0)
        {
            this.selectAll();
        }
    }

      public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
       if(this.state && this.isEdited(e))
       {
           this.state = false;
           parent.notSave(parent.getSelectedIndex());
           //System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
       }
    }

    public void keyReleased(KeyEvent e)
    {
    }
    
    public void caretUpdate(CaretEvent e) 
    {
    	//parent.obtenerFilaColumna();
        //System.out.println("Pos: "+this.getLineCount());
    }
}
