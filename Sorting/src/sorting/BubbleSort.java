/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

/**
 *
 * @author William Zhang
 */
public class BubbleSort {
    public static void main(String []args )
    {
       
    }
    public static void BubbleSort( Comparable  [] array)
    {
        for(int i = 0; i < array.length -1; i++)
        {   boolean swap = false;
            for(int j = 0 ; j < array.length - i - 1;j++)
            {
                
               
                 if(array[j].compareTo(array[j+1])  > 0)
                 {
                     Comparable temp =  array[j];
                     array[j] = array[j+1];
                     array[j+1] = temp;
                     swap = true;
                 }
                
            }
             if(swap == false )
                     break;
        }
    }
    public static void BubbleSort(int [] array)
    {
       boolean swapped = true;
     while (swapped) {
            swapped = false;    
     for (int j = 1; j < array.length; j++) {
        if (array[j - 1] > array[j]) {
            int temp = array[j];
            array[j] = array[j - 1];
            array[j - 1] = temp;
            swapped = true;
      }
    }
  }
}
    }

