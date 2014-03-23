/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;

/**
 *
 * @author Pedro Luis
 */

import doc.Constantes;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TextFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        if(f.isDirectory())
           return true;
        String name = f.getName();
        int i = name.lastIndexOf(".");
        if(i>0 && i<name.length()-1)
        {
            String type = name.substring(i+1).toLowerCase();
            if(type.equals(Constantes.EXT_FILES))
               return true;
        }
        return false;
    }

    @Override
    public String getDescription()
    {
        return Constantes.DESC_EXT;
    }
}
