/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author William Zhang
 * @param <T>
 */
public class MaxHeap < T extends Comparable <T>>  {
      private static final int CAPACITY = 2;

   private int size;            // Number of elements in heap
   private T[] heap;     // The heap array

   public MaxHeap()
   {
      size = 0;
      heap = (T[]) new Comparable[CAPACITY];
   }

 /**
  * Construct the binary heap given an array of items.
  */
   public MaxHeap(T[] array)
   {
      size = array.length;
      heap = (T[]) new Comparable[array.length+1];

      System.arraycopy(array, 0, heap, 1, array.length);

      buildHeap();
   }
 
   private void buildHeap()
   {
      for (int k = size/2; k > 0; k--)
      {
         percolatingDown(k);
      }
   }
   private void percolatingDown(int k)
   {
      T tmp = heap[k];
      int leftchild;

      for(; 2*k <= size; k = leftchild)
      {
         leftchild = 2*k;
        int  rightchild = 2*k + 1;
         if(leftchild != size &&
            heap[leftchild].compareTo(heap[rightchild]) > 0)leftchild++;

         if(tmp.compareTo(heap[leftchild])  >   0)  heap[k] = heap[leftchild];
         else
                break;
      }
      heap[k] = tmp;
   }

 /**
  *  Sorts a given array of items.
  */
   public void heapSort(T[] array)
   {
      size = array.length;
      heap = (T[]) new Comparable[size+1];
      System.arraycopy(array, 0, heap, 1, size);
      buildHeap();

      for (int i = size; i > 0; i--)
      {
         T tmp = heap[i]; //move top item to the end of the heap array
         heap[i] = heap[1];
         heap[1] = tmp;
         size--;
         percolatingDown(1);
      }
      for(int k = 0; k < heap.length-1; k++)
         array[k] = heap[heap.length - 1 - k];
   }

 /**
  * Deletes the top item
  */
   public T deleteMax() throws RuntimeException
   {
      if (size == 0) throw new RuntimeException("This is  empty heap");
      T min = heap[1];
      heap[1] = heap[size--];
      percolatingDown(1);
      return min;
	}

 /**
  * Inserts a new item
  */
   public void insert(T x)
   {
      if(size == heap.length - 1) doubleSize();

      //Insert a new item to the end of the array
      int pos = ++size;

      //Percolate up
      for(; pos > 1 && x.compareTo(heap[pos/2]) < 0; pos = pos/2 )
         heap[pos] = heap[pos/2];

      heap[pos] = x;
   }
   private void doubleSize()
   {
      T [] old = heap;
      heap = (T []) new Comparable[heap.length * 2];
      System.arraycopy(old, 1, heap, 1, size);
   }

   public String toString()
   {
      String output = "";
      for(int k = 1; k <= size; k++) output += heap[k]+" ";
      return output ;
   }
    public static void main(String [] args) throws Exception
    {
         Integer[] a = {3,2,1,7,8,4,10,16,12};
         MaxHeap<Integer> minheap = new MaxHeap(a);
        System.out.println(minheap.deleteMax());
        System.out.println(minheap.toString());
    
         
    }
}
