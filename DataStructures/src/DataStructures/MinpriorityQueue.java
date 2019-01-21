/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 * A minpriority queue is a priority queue  that perform heap operation.  The heap acts like a abstract idea but we do not  choose to use the heap itself
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
    class  Item
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
  
    
}
