/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author William Zhang
 * @param <E>
 */
public class Node <E> {
    private E data;
    private E nextdata;
    private E  previousdata;
    public Node(E object)
    {
        data = object;
    }
    public E getData()
    {
       return data;
    }
    public void setData(E type)
    {
        this.data = type;
    }
    public  E getNext()
    {
        return nextdata;
    }
    public void setNext(E next)
    {
       this.nextdata = next;
    }
    public E getPrevious()
    {
        return previousdata;
    }
    public void setPrevious(E previous)
    {
        this.previousdata = previous;
    }
    @Override
    public String toString()
    {
       return data.toString();
    }
    
}
