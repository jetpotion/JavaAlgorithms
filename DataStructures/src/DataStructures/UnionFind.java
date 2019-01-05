/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author William Zhang
 */
public class UnionFind{
  
   private UnionFind parent;
   private Vertex Data;
   public UnionFind()
   {
       
   }
   
   public UnionFind (Vertex X)
   {
       parent =  new UnionFind();
       Data = X;
   }
   public UnionFind getParent()
   {
       return parent;
   }
   public Vertex getData()
   {
       return Data   ;
   }
   public Vertex find(UnionFind data)
   {
       if(Data.equals(data.getData()))
       {
           return Data;
       }
       else
       {
            return find(data.getParent());
       }
   }
  
}
