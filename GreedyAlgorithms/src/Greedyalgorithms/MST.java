/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedyalgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.FileChooser;


/**
 *
 * @author William Zhang
 */
public class MST {
/** Implement some form  Delauney Triangulation to speed up runtime. Remember this is a euclidean minimum spanning tree and thus the graph cannot be gurantee to be planar. **/
 /**Delauney triangulation will snip the tree until it is planar  and thus limit  the number of edges to be less than 3v-6 where V is the 
  * number of edges **/
     public static void takeinputs() throws FileNotFoundException
    {
        FileChooser  newinputs  = new FileChooser();
        
        newinputs.setInitialDirectory(new File("C:\\Users"));
        newinputs.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("TxT files", "*.txt"));
          
          File fileinput = newinputs.showOpenDialog(null);
          if(fileinput != null)
          {
              Scanner scanner = new Scanner(fileinput);
              /** This will dump the first 2 lines because the size and the edges dont matter **/
              scanner.nextLine();
              scanner.nextLine();
              while(scanner.hasNextLine())
              {
                  
              }
              
             
          }
    }
     
}
