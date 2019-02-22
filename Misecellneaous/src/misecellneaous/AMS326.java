/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misecellneaous;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author William Zhang
 */
public class AMS326 {
    public static void main(String []args)
    {
        double[][] Matrix = new double [1024][1024];
        Random rand = new Random(); 
    for (int i = 0; i < 1024; i++) {     
        for (int j = 0; j < 1024; j++) {
            double random = -2 + rand.nextDouble() * (2 - (-2));
             Matrix[i][j] = Math.abs(random);
        }

    }
        double [][]Matrix2 = new double[1024][1024];
         for (int i = 0; i < 1024; i++) {     
        for (int j = 0; j < 1024; j++) {
            double random = -2 + rand.nextDouble() * (2 - (-2));
             Matrix2[i][j] = Math.abs(random);
        }
       double [][] newmatrix = matrixMultiply(Matrix,Matrix2);
       
    }
    
    }
    public static  double  [][] matrixMultiply(double [][] matrix1 , double[][]matrix2)
    {
        double [][] result = new double[matrix1.length][matrix2[0].length];

/* Loop through each and get product, then sum up and store the value */
     for (int i = 0; i < matrix1.length; i++) { 
         for (int j = 0; j < matrix2[0].length; j++) { 
             for (int k = 0; k < matrix1[0].length; k++) { 
            result[i][j] += matrix1[i][k] * matrix2[k][j];
        }
    }
}
    return result;
    }
    
}
