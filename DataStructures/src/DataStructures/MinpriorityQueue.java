/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.Arrays;

/**
 * A minpriority queue is a priority queue  that perform heap operation.
 * The heap acts like a abstract idea but we do not  choose to use the heap itself.
 * @author William Zhang
 * Type can extend any comparable type
 */
public class MinpriorityQueue < T  extends Comparable<T>> {

    /**
     *
     * @param args
     */
    private Item [] heap;
    private int size;
    class  Item implements Comparable
    {
        private T item;
        private int priority;
        public Item(T type,int priority)
        {
           this.item = type;
           this.priority = priority;
        }
        public int getPriority()
        {
            return this.priority;
        }

        @Override
        public int compareTo(Object mon) {
           Item item = (Item)mon;
          if(this.priority < item.getPriority())
          {
              return -1;
          }
          else if(this.priority > item.getPriority())
          {
              return 1;
          }
          else
          {
              return 0;
          }
        }
        
    }
    /** Construct  a empty priority queue **/
    public MinpriorityQueue()
    {
        heap =(Item []) new Comparable[2];
        size = 0;
        
    }
    /** Double the  size the array if necessary **/
     private void doubleSize()
   {
      Item  [] old = heap;
      heap = (Item []) new Comparable[heap.length * 2];
      System.arraycopy(old, 1, heap, 1, size);
   }
     /** insert(T type,int priority) is the method where we insert objects into the array.
      * 
      * @param type the object itself
      * @param priority for which the item itself is inserted
      */
    public void insert(T type,int priority)
    {
         Item x = new Item(type,priority);
         if(size == heap.length - 1) doubleSize();

      //Insert a new item to the end of the array
         int pos = ++size;

      //Percolate up
        for(; pos > 1 && x.getPriority() < heap[pos/2].getPriority() ; pos = pos/2 )
             heap[pos] = heap[pos/2];

         heap[pos] = x;
    }
    private void percolatingDown(int k)
   {
      Item tmp = heap[k];
      int leftchild;

      for(; 2*k <= size; k = leftchild)
      {
         leftchild = 2*k;
        int  rightchild = 2*k + 1;
         if(leftchild != size &&  heap[leftchild].getPriority()  > heap[rightchild].getPriority())
             leftchild++;

         if(tmp.getPriority() > heap[leftchild].getPriority())
             heap[k] = heap[leftchild];
         else
                break;
      }
      heap[k] = tmp;
   }
    /** extracts the item with minimum priority ** 
     * 
     * @return
     * @throws RuntimeException 
     */
    public Item deleteMin() throws RuntimeException
   {
      if (size == 0) throw new RuntimeException("This is  empty heap");
      Item min = heap[1];
      heap[1] = heap[size--];
      percolatingDown(1);
      return min;
              }
  
     public void ChangeKey(Item oldkey, Item newKey)
   {
        
       /** standard binary search   of oldkey to check to see if the index exist otherwise**/
         int  i = Arrays.binarySearch(heap,oldkey);
         if(i < size || i > 0)
         {
             /** Check to see if the value  of newkey is less than the newkey **/
             if(oldkey.compareTo(newKey) > 0)
             {
                  if(i == 1)
                      /** if old value was in the root and value was even less than this is the new global minimum **/
                      heap[i] = newKey;
                  /**All we need to do is percolate up to restore heap**/
                  else 
                  {
                      heap[i] = newKey;
                      for(; i > 1 && heap[i].compareTo(heap[i/2])< 0; i= i/2)
                      {
                          heap[i] =  heap[i/2];
                      }
                  }
             }
              /**This is accounting for the value of the newkey is greater than the oldkey **/
             else 
             {
                 if(i == 1)
                 {   heap[i] = newKey; 
                     percolatingDown(1);}
                 else 
                 {
                     heap[i] = newKey;
                     percolatingDown(i);
                 }
             }
  
         }
         else 
         {
             throw new IndexOutOfBoundsException("Oldkey does not exist ");
         }
   }
}
