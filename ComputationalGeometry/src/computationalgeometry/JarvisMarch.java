/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computationalgeometry;

/**
 *
 * @author William Zhang
 */
import java.util.*;
import java.io.*;
import javafx.geometry.Point2D;
import javafx.scene.*;

public class JarvisMarch {
    private static Point2D [] points;
    public static void main(String []args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the file name");
        File file = new File(scanner.nextLine());
        Scanner filereader = new Scanner(file);
        points = new Point2D [Integer.parseInt(filereader.nextLine())];
        int x = 0;
        while(filereader.hasNextLine())
        {
            String line = filereader.nextLine();
            String replacedline = line.replaceAll("\\[","").replaceAll("\\]","").replaceAll("\n","");
            String [] potential = replacedline.split(",");
            points[x] = new Point2D(Double.parseDouble(potential[0]),Double.parseDouble(potential[1]));
            x++;
        }
        
        
    }
}
