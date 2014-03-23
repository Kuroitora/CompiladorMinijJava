/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package editor;

/**
 *
 * @author Pedro Luis
 */
import doc.Constantes;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends JFrame 
{
    private PanelVentanas p = new PanelVentanas();
 
    @SuppressWarnings("LeakingThisInConstructor")
    public Principal(String title)
    {
    	super(title);
    	this.setSize(this.getScreen().width/2+200, 
                this.getScreen().height/2+200);
    	this.setLocation(this.getScreen().width/4-100, 
                this.getScreen().height/4-100);
    	this.setJMenuBar(p.barraM());
    	this.add(p.barraH(), BorderLayout.NORTH);
    	this.add(p, BorderLayout.CENTER);
        this.add(p.barraE(), BorderLayout.SOUTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(new JPanel(), BorderLayout.EAST);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                          this.getClass().getResource("/iconos/image.jpg")));
    	this.show(true);
        this.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we){
	       if(p.notFileSaved())
               System.exit(EXIT_ON_CLOSE);
	    }
        });
    } 

    private Dimension getScreen()
    {
    	return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String args[])
    {
    	new Principal(Constantes.TITLE_APP);
    }
}
