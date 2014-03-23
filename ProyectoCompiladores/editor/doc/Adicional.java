/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doc;

/**
 *
 * @author Pedro Luis
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Adicional 
{
    public static int existDirectory(ArrayList <String> dirs, String dir)
    {
        ArrayList <String> aux = new ArrayList <String> ();
        Iterator <String> it = dirs.iterator();
        while(it.hasNext())
            aux.add(it.next());
        Collections.sort(aux);
        return Collections.binarySearch(aux, dir);
    }
}
