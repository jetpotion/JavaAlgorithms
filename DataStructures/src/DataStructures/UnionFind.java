/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author William Zhang
 * @param <V> Any type
 */
public class UnionFind  <V extends Comparable<V>>
{
    private LinkedList<V> set;
    private int rank;
    public UnionFind(V item)
    {
       this.set = new LinkedList<V>(Arrays.asList(set));
       rank = 1;
       
    }
  
    public V find()
    {
        return set.getFirst();
    }
    @Override
    public boolean equals(Object unionfind)
    {
        if(unionfind instanceof UnionFind)
        {
            UnionFind  translated= (UnionFind)unionfind;
            V finding = this.find();
            V  type = (V)   translated.find();
            return  finding.compareTo(type) == 1;
        }
        else
        {
            return false;
        }
    }
    public LinkedList<V> returnset()
    {
        return set;
    }
    public static UnionFind union(UnionFind tree1,UnionFind tree2)
    {
        /** This is assuming this is part of the same set because are checking if they are same parent**/
        if(tree1.equals(tree2))
        {
            return tree1;
        }
        else
        {
            int rankone = tree1.getRank();
            int ranktwo = tree2.getRank();
            /**Since ranks are equal **/
            if(rankone == ranktwo)
            {
                tree2.returnset().offerFirst(tree1.find());
                tree2.setRank(tree2.getRank()+1);
                return tree2;
            }
            else if(rankone > ranktwo)
            {
                tree1.returnset().offerFirst(tree2.find());
                tree1.setRank(tree1.getRank() +1);
            }
            else
            {
                
            }
            
        }
        return null;
    }
    public UnionFind makeset(V v)
    {
        return new UnionFind(v);
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.set);
        hash = 79 * hash + this.rank;
        return hash;
    }
    private int getRank()
    {
        return rank;
    }
    public void setRank(int v)
           
    {
        this.rank= v;
    }
}