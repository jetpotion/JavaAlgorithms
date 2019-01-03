/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.Iterator;

/**
 *
 * @author William Zhang
 * @param <T> Parameter will be any type that will comparable
 */
public class CircularLinkedlist<T extends Comparable <T>> implements Iterator {
    private Node head;
    private Node cursor;
    private Node tail;
    private int size; 
    public CircularLinkedlist(T [] array)
    {
        
    }
    public void add(Node type)
    {
        /** Case 1  the list is empty **/
        if(head == null)
        {
            head =  type;
            tail = type;
            head.setNext(tail);
            head.setPrevious(tail);
            tail.setNext(head);
            tail.setPrevious(head);
            cursor = head ;
            size++;
        }
    }
    
    @Override
    public boolean hasNext() {
        return head == null;
    }

    @Override
    public Object next() {
        
        return cursor.getNext();
       
    }
   
}
