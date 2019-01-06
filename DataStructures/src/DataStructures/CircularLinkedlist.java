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
    /**
     * AddHead will add  the data before the head and not after;
     * @param type any comparable type 
     */
    public void addAtHead(T type)
    {

        if(head == null)
        {
            
            Node type1 = new Node(type);
            head =  type1;
            tail = type1;
            head.setNext(tail);
            head.setPrevious(tail);
            tail.setNext(head);
            tail.setPrevious(head);
            size++;
        }
        /**The case for when the linkedlist is off size 1 **/
        
        else if(size == 1)
        {
            Node newtype = new Node(type);
            newtype.setNext(head);
            newtype.setPrevious(tail);
            head = newtype;
            tail.setPrevious(head);
            tail.setNext(head);
            size++;
        }
        else
        {
            Node newtype = new Node(type );
            newtype.setNext(head);
            newtype.setPrevious(tail);
            head.setPrevious(newtype);
            head = newtype;
            tail.setNext(head);
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
