/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package editor;

/**
 *
 * @author Pedro Luis
 */

import filter.*;
import doc.*;
import gramatica.*;

import javax.swing.plaf.BorderUIResource;
import javax.swing.event.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.FlowLayout;

import javax.swing.filechooser.FileFilter;

import java.io.*;

import java.util.ArrayList;
import sun.awt.image.ToolkitImage;

public class PanelVentanas extends JTabbedPane 
    implements ActionListener, ChangeListener
{
    private ArrayList <TextDocument> ventanas;
    private ArrayList <String> directorios;

    private JFileChooser files;

    private Integer tabCreado;

    private JPopupMenu menuContex;
    private JMenuItem fileContex[]; 

    private JMenuBar barraM;
    private JMenu menu[];
    private JMenuItem file[];
    private JMenuItem edit[];
    private JCheckBoxMenuItem view[];

    private JToolBar barraH;
    private JButton []button;
    
    private JPanel barraE;
    private JSlider zoom;
    private JTextField dir;

    private ImageIcon iconoPL;

    public PanelVentanas()
    {
    	this.initComponents();
    }

    private void initComponents()
    {
    	ventanas = new ArrayList <TextDocument> ();
    	directorios = new ArrayList <String> ();
    	files = new JFileChooser();files.setMultiSelectionEnabled(false);
        FileFilter defaultFF = files.getFileFilter();
        files.removeChoosableFileFilter(files.getFileFilter());
        files.addChoosableFileFilter(new TextFileFilter());
        files.addChoosableFileFilter(defaultFF);
        //files.setFileFilter(new TextFileFilter());
        iconoPL = new ImageIcon(this.getClass().getResource("/iconos/pl.gif"));
    	tabCreado = 0;
    	this.initMenuBar();
    	this.initToolBar();
        this.initStatusBar();
    	this.initMenuContex();
    	this.setComponentPopupMenu(this.getMenuContext());
        this.addChangeListener(this);
        
        this.newVentana();
    }
    
    /*******Init Bars*********/
    
    private void initMenuBar() //init Menu Bar
    {
    	barraM = new JMenuBar();

    	menu = new JMenu[4];

    	menu[0] = new JMenu("Archivo");
    	menu[0].setMnemonic('A');

    	file = new JMenuItem[6];

    	file[0] = new JMenuItem("Nuevo");file[0].setActionCommand("Nuevo");
    	file[0].setIcon(new ImageIcon(getClass().getResource("/iconos/New16.gif")));
    	file[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    	file[0].addActionListener(this);

    	file[1] = new JMenuItem("Abrir");file[1].setActionCommand("Abrir");
    	file[1].setIcon(new ImageIcon(getClass().getResource("/iconos/Open16.gif")));
    	file[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
    	file[1].addActionListener(this);

    	file[2] = new JMenuItem("Guardar");file[2].setActionCommand("Guardar");
        file[2].setIcon(new ImageIcon(getClass().getResource("/iconos/Save16.gif")));
    	file[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 
                InputEvent.CTRL_MASK));
    	file[2].addActionListener(this);

    	file[3] = new JMenuItem("Guardar como");file[3].setActionCommand("Guardar como");
        file[3].setIcon(new ImageIcon(getClass().getResource("/iconos/Save-as16.gif")));
    	file[3].addActionListener(this);

    	file[4] = new JMenuItem("Cerrar");file[4].setActionCommand("Cerrar");
        file[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, 
                InputEvent.CTRL_MASK));
    	file[4].setIcon(new ImageIcon(getClass().getResource("/iconos/Delete16.gif")));
    	file[4].addActionListener(this);

    	file[5] = new JMenuItem("Salir");file[5].setActionCommand("Salit");
    	file[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
                InputEvent.CTRL_MASK));
        file[5].setIcon(
                new ImageIcon(getClass().getResource("/iconos/Exit16.gif")));
    	file[5].addActionListener(this);

    	menu[1] = new JMenu("Editar");menu[1].setMnemonic('E');

    	edit = new JMenuItem[4];

    	edit[0] = new JMenuItem("Copiar");edit[0].setActionCommand("Copiar");
        edit[0].setIcon(new ImageIcon(getClass().getResource("/iconos/Copy16.gif")));
    	edit[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
    	edit[0].addActionListener(this);

    	edit[1] = new JMenuItem("Cortar");edit[1].setActionCommand("Cortar");
        edit[1].setIcon(new ImageIcon(getClass().getResource("/iconos/Cut16.gif")));
    	edit[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
    	edit[1].addActionListener(this);

    	edit[2] = new JMenuItem("Pegar");edit[2].setActionCommand("Pegar");
        edit[2].setIcon(new ImageIcon(getClass().getResource("/iconos/Paste16.gif")));
    	edit[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
    	edit[2].addActionListener(this);

        edit[3] = new JMenuItem("Seleccionar todo");edit[2].setActionCommand("Seleccionar todo");
    	edit[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
    	edit[3].addActionListener(this);

    	 menu[2] = new JMenu("Vistas");
         menu[2].setMnemonic('V');

         view = new JCheckBoxMenuItem [3];

         view[0] = new JCheckBoxMenuItem ("Menu Bar"); 
         view[0].setActionCommand("menu");
         view[0].setSelected(true);
         view[0].addActionListener(this);
         
         view[1] = new JCheckBoxMenuItem ("Tool Bar");
         view[1].setActionCommand("tool");
         view[1].setSelected(true);
         view[1].addActionListener(this);

         view[2] = new JCheckBoxMenuItem ("Status Bar");
         view[2].setActionCommand("status");
         view[2].setSelected(true);
         view[2].addActionListener(this);

         menu[3] = new JMenu("Ayuda");
         menu[3].setMnemonic('Y');
    }

    private void initToolBar() //Init ToolBar
    {
    	barraH = new JToolBar(JToolBar.HORIZONTAL);
    	barraH.setFloatable(false);

    	button = new JButton[6];
    	
    	button[0] = new JButton(new ImageIcon(getClass().getResource("/iconos/play.jpg")));
        button[0].setToolTipText("Compilar");
        button[0].setActionCommand("Compilar");
    	button[0].addActionListener(this);

    	button[1] = new JButton();button[1].setActionCommand("Nuevo");
    	button[1].setIcon(new ImageIcon(getClass().getResource("/iconos/New24.gif")));
    	button[1].setToolTipText("Nuevo");button[1].addActionListener(this);

    	button[2] = new JButton(new ImageIcon(getClass().getResource("/iconos/Open24.gif")));
        button[2].setToolTipText("Abrir");button[2].setActionCommand("Abrir");
    	button[2].addActionListener(this);

    	button[3] = new JButton(new ImageIcon(getClass().getResource("/iconos/Save24.gif")));
    	button[3].setToolTipText("Guardar");button[3].setActionCommand("Guardar");
        button[3].addActionListener(this);

    	button[4] = new JButton(new ImageIcon(getClass().getResource("/iconos/Save-as24.gif")));
        button[4].setToolTipText("Guardar como");button[4].setActionCommand("Guardar como");
        button[4].addActionListener(this);

    	button[5] = new JButton(new ImageIcon(getClass().getResource("/iconos/Delete24.gif")));
        button[5].setToolTipText("Cerrar");button[5].setActionCommand("Cerrar");
    	button[5].addActionListener(this);
    }

    private void initStatusBar() // Init StatusBar
    {
        barraE = new JPanel(new FlowLayout(FlowLayout.CENTER));
        barraE.setBorder(BorderUIResource.getRaisedBevelBorderUIResource());
        
        dir = new JTextField(); dir.setEditable(false);
        
        zoom = new JSlider(JSlider.HORIZONTAL, 15, 200, 15);
        zoom.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                if(getSelectedIndex()>=0)
                   ventanas.get(getSelectedIndex()).setLengthFont(zoom.getValue());
            }
        });
    }

    private void initMenuContex() //Init menuContext
    {

    	fileContex = new JMenuItem[3];

    	menuContex = new JPopupMenu();

    	fileContex[0] = new JMenuItem("Guardar");
        fileContex[0].setActionCommand("Guardar");
        fileContex[0].setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
    	fileContex[0].setIcon(
                new ImageIcon(getClass().getResource("/iconos/Save16.gif")));
        fileContex[0].addActionListener(this);

    	fileContex[1] = new JMenuItem("Guardar como");
        fileContex[1].setActionCommand("Guardar como");
        fileContex[1].setIcon(
                new ImageIcon(getClass().getResource("/iconos/Save-as16.gif")));
        fileContex[1].addActionListener(this);

    	fileContex[2] = new JMenuItem("Cerrar");
        fileContex[2].setActionCommand("Cerrar");
        fileContex[2].setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
    	fileContex[2].setIcon(
                new ImageIcon(getClass().getResource("/iconos/Delete16.gif")));
        fileContex[2].addActionListener(this);
    }

    public JMenuBar barraM()//Add menus to menuBar
    {
    	menu[0].add(file[0]);menu[0].add(file[1]);menu[0].add(new JSeparator());
    	menu[0].add(file[2]);menu[0].add(file[3]);menu[0].add(new JSeparator());
    	menu[0].add(file[4]);menu[0].add(new JSeparator());menu[0].add(file[5]);

    	menu[1].add(edit[0]);menu[1].add(edit[1]);menu[1].add(edit[2]);
        menu[1].add(new JSeparator());menu[1].add(edit[3]);

        //menu[2].add(view[0]);
        menu[2].add(view[1]);menu[2].add(view[2]);

    	barraM.add(menu[0]);barraM.add(menu[1]);
        barraM.add(menu[2]);barraM.add(menu[3]);
        
    	return barraM;
    }

    public JToolBar barraH() // Add Buttons to ToolBar
    {
    	barraH.add(button[0]);barraH.addSeparator();
        barraH.add(button[1]);barraH.addSeparator();
        barraH.add(button[2]);barraH.addSeparator();
    	barraH.add(button[3]);barraH.addSeparator();
        barraH.add(button[4]);barraH.addSeparator();
        barraH.add(button[5]);
    	return barraH; 
    }

    public JPanel barraE() //Add Components to StatusBa
    {
        barraE.add(new JLabel("Dir:"));barraE.add(dir);
        barraE.add(new JLabel("15"));barraE.add(zoom);
        barraE.add(new JLabel("100%"));
        return barraE;
    }
    
    private JPopupMenu getMenuContext()//Add menus to menuContext
    {
    	menuContex.add(fileContex[0]);menuContex.add(fileContex[1]);
    	menuContex.add(fileContex[2]);
    	return menuContex;
    }
    /****Operations with Tabs*/
    private void newVentana(String dir, String subtitle) //Creates new Tabs with name and a directory
    {
    	ventanas.add(new TextDocument(this));directorios.add(dir);
        ventanas.get(ventanas.size()-1).setState(true);
    	this.addTab(subtitle, iconoPL,
                new JScrollPane(ventanas.get(ventanas.size()-1)));
    }

    private void newVentana()//Creates new default tab.
    {
    	ventanas.add(new TextDocument(this));directorios.add("No saved");
    	this.addTab("Nuevo" + (tabCreado++) + "." + Constantes.EXT_FILES + "*", 
        iconoPL, new JScrollPane(ventanas.get(ventanas.size()-1)));
    }

    public void removeVentana(int pos)//Remove a tabs and directory
    {
    	this.removeTabAt(pos);
    	this.ventanas.remove(pos);
        this.directorios.remove(pos);
    }
    
    private void newSave(File fo, int select)//Save content to the tab in a file.
    {
        try
        {
                BufferedWriter bw = 
                        new BufferedWriter(new FileWriter(fo.getAbsolutePath()));
                String text = ventanas.get(select).getText();
                directorios.add(select, fo.getAbsolutePath());
                ventanas.get(select).setState(true);
                this.setTitleAt(select,fo.getName());
                file[2].setEnabled(false); button[2].setEnabled(false);
                bw.write(text);
                bw.close();
        }
        catch(IOException ex){
                System.out.println("Exception: "+ ex);
        }
    }
    private void save(int select)//Save the chages made to the file
    {
        try{
                String dirt = directorios.get(select);
                BufferedWriter bw = new BufferedWriter(new FileWriter(dirt));
                String text = ventanas.get(select).getText();
                ventanas.get(select).setState(true);
                file[2].setEnabled(false); button[2].setEnabled(false);
                bw.write(text);
                bw.close();
        }
        catch(IOException ex){
                System.out.println("Exception: "+ ex);
        }
    }
    
    @SuppressWarnings("static-access")
    private int close(int select)
    {
      String title = this.getTitleAt(select);
            if(ventanas.get(select).getText().length()==0
               || title.charAt(title.length()-1)!='*')
            {
               this.removeVentana(select);
               if(this.getTabCount() == 0)
                      System.exit(0);
               return -1;
            }
            
            int conf = JOptionPane.showConfirmDialog(this,
                    "Desea guardar los Cambios de \n"+
                    title.substring(0, title.length()-1),"Cerrar",1);
            if(conf==0)
            {
               if(directorios.get(select).compareTo("No saved")==0)
               {
                  int fileO = files.showSaveDialog(this);
                  if(fileO == files.APPROVE_OPTION)
                  {
                     File fo = new File(files.getSelectedFile().getAbsolutePath()
                             + "." + Constantes.EXT_FILES);
                     if(fo.isFile())
                     {
                        JOptionPane.showMessageDialog(this, 
                                "Fichero ya existe", "Save As Error", 0);
                        return -1;
                     }
                     this.newSave(fo, select);
                     if(this.getTabCount()==0)
                        System.exit(0);
                  }
               }
               else if(title.charAt(title.length()-1) == '*')
               {
                   this.save(select);
                   this.removeVentana(select);
                   if(this.getTabCount() == 0)
                      System.exit(0);
	          } 
             }
             else if(conf == 1)
             {
                 System.out.println("Conf: " + conf);
                this.removeVentana(select);  
                if(this.getTabCount() == 0)
                    System.exit(0);
             }
             else if(conf == 2){
                 return 2;
             }
            return -1;
    }
    
    public boolean notFileSaved()
    {
        if(getTabCount() == 0)
            return true;
        for (int i = getTabCount() - 1; i >= 0; i--) {
            if(close(i) == 2){
                return false;
            }
        }
        return true;
    }
    
    public void notSave(int select)//Indicated '*': File no saved
    {
        String title = this.getTitleAt(select);
        if(title.charAt(title.length()-1) == '*')
           return;
        title = title + "*";
        this.setTitleAt(select, title);
        file[2].setEnabled(true);
        button[2].setEnabled(true);
    }

    @SuppressWarnings("static-access")
    public void actionPerformed(ActionEvent e)
    {
	//buttons
        int select = this.getSelectedIndex();
        
	if(e.getActionCommand().compareTo("Nuevo") == 0)
	{
            this.newVentana();
            this.setSelectedIndex(this.getTabCount()-1);
	}
	else if(e.getActionCommand().compareTo("Abrir") == 0)
	{
            int fileI = files.showOpenDialog(this);
            if(fileI == files.APPROVE_OPTION)
            {
               File in = files.getSelectedFile();
               if(Adicional.existDirectory(directorios, in.getAbsolutePath())>=0)
               {
                   JOptionPane.showMessageDialog(this, "File is open" , "Open Error", 0);
                   return;
               }
               try
               {
                    FileReader fr = new FileReader(in);
                    BufferedReader br = new BufferedReader(fr);
                    this.newVentana(in.getAbsolutePath(),in.getName());
                    this.setTitleAt(this.getTabCount()-1,in.getName());
                    String line = br.readLine(),text = "";
                    while(line!=null)
                    {
                        text += line+"\n";
                        line = br.readLine();
                    }
                    ventanas.get(this.getTabCount()-1).setText(text);
                    ventanas.get(this.getTabCount()-1).setState(true);
                    file[2].setEnabled(false); 
                    button[3].setEnabled(false);
                    fr.close();
                    br.close();
		}
		catch(IOException ex)
		{
                    System.out.println("Exception: "+ ex);
		}
                this.setSelectedIndex(this.getTabCount()-1);
	     }
          }

	else if(e.getActionCommand().compareTo("Guardar")==0)
	{
            if(select<0)
               return;
            String title = this.getTitleAt(select);
            if(directorios.get(select).compareTo("No saved")==0)
            {
               int fileO = files.showSaveDialog(this);
               if(fileO == files.APPROVE_OPTION)
               {
                  File fo = new File(files.getSelectedFile().getAbsolutePath() + "." + Constantes.EXT_FILES);
                  if(fo.isFile())
                  {
                     JOptionPane.showMessageDialog(this, "File exists", "Save As Error", 0);
                     return;
                  }
                  this.newSave(fo,select);
               }
            }
            else if(title.charAt(title.length()-1)=='*')
            {
               this.save(select);
               this.setTitleAt(select, title.substring(0, title.length()-1));
            }
	}
	else if(e.getActionCommand().compareTo("Guardar como")==0)
	{
            if(select<0)
               return;
            int fs = files.showSaveDialog(this);
            if(fs==files.APPROVE_OPTION)
            {
                File fsave = new File(files.getSelectedFile().getAbsoluteFile()+"");
                if(fsave.isFile())
                {
                  JOptionPane.showMessageDialog(this, "File exists", "Save As Error", 0);
                  return;
                }
                try
                {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fsave.getAbsoluteFile()));
                    String text = ventanas.get(select).getText();
                    bw.write(text);
                    bw.close();
                }
                catch (IOException ex)
                {
                    System.out.println("Exception: "+ ex);
                }
            }
	}
        else if(e.getActionCommand().compareTo("Compilar")==0)
	{
            //solo toma la direccion del que se abria anteriormente
            File in = files.getSelectedFile();
           //Scanner_Parser.reconocedor_tokens.reconocedor_tokens(in);
         // Scanner_Parser.Driver.reconocer(in);
            MiniJavaParser.main(in);
            
         
           
	}
	else if(e.getActionCommand().compareTo("Cerrar")==0)
	{
            this.close(select);
	}
        else if(e.getActionCommand().compareTo("Copiar")==0)
        {
            if(ventanas.get(select).getSelectedText()!= null)
               ventanas.get(select).copy();
        }
        else if(e.getActionCommand().compareTo("Cortar")==0)
        {
            if(select>=0)
            {
               if(ventanas.get(select).getSelectedText()!= null)
               {
                  ventanas.get(select).cut();
                  ventanas.get(select).setState(true);
                  this.notSave(select);
               }
            }
        }
        else if(e.getActionCommand().compareTo("Pegar")==0)
        {
            if(select>=0)
            {
               ventanas.get(select).paste();
               ventanas.get(select).setState(true);
               this.notSave(select);
            }
        }
         else if(e.getActionCommand().compareTo("Seleccionar todo")==0)
        {
            if(select>=0)
            {
                if(ventanas.get(select).getText()!=null)
                   ventanas.get(select).selectAll();
            }
        }
	else if(e.getActionCommand().compareTo("Salir")==0)
	{
            if(notFileSaved())
                System.exit(0);
	}

        else if(e.getActionCommand().compareTo("menu")==0)
	{

            barraM.setVisible(view[0].isSelected());
	}

	else if(e.getActionCommand().compareTo("tool")==0)
	{
            barraH.setVisible(view[1].isSelected());
	}

	else if(e.getActionCommand().compareTo("status")==0)
	{
            barraE.setVisible(view[2].isSelected());
	}
    }

    public void stateChanged(ChangeEvent e)
    {
        int select = getSelectedIndex();
        if(select >=0)
        {
            file[2].setEnabled(!ventanas.get(select).getState());
            button[2].setEnabled(!ventanas.get(select).getState());
            
            dir.setText(directorios.get(select));
            
            zoom.setValue(ventanas.get(select).getLengthFont());
        }
        else
            zoom.setValue(15);
    }
    
    public void obtenerFilaColumna(){
        
    int select = getSelectedIndex();

    if(select >=0)
    {
        int pos = ventanas.get(getSelectedIndex()).getCaretPosition();
        javax.swing.text.Element map = ventanas.get(getSelectedIndex()).getDocument().getDefaultRootElement();
        int currLine = map.getElementIndex(pos);
        javax.swing.text.Element lineElement = map.getElement(currLine);
        int start = lineElement.getStartOffset();
        cont.setText("Fila: " + String.valueOf(currLine) + " Columna: " + String.valueOf(pos - start));

    }
    }
    
    
    	public String archA;
    	public JLabel cont = new JLabel("Fila: 0 Columna: 0    ");
}
