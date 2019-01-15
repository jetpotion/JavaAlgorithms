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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class JarvisMarch {
    private static Point2D [] points;
    public static TextArea convexhulloutput;
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
    public static void createConvexhull(Group pane, Point2D[] points)
    {
        
    }
    

    
    
      public static void loadPoints(Group group , Point2D[] pointX, Pane pane, Button button, Button newinput) {
        pane.setPrefSize(1000,900);

        for (Point2D point : pointX) {
            Circle circle = new Circle(point.getX(), point.getY(), 8, Paint.valueOf("#DC143C"));
            Tooltip tooltip = new Tooltip(circle.getCenterX() + "," + circle.getCenterY());
            Tooltip.install(circle, tooltip);

            group.getChildren().add(circle);

        }
        group.setTranslateX(pane.getPrefWidth()/2);
        group.setTranslateY(pane.getPrefHeight()/2);
        button.setOnAction((event)
                -> {

            try {
                createConvexhull(group, points);
            } catch (Exception ex) {
                Logger.getLogger(GrahamScan.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
                
        );
        newinput.setOnAction((event)->{
            convexhulloutput.setText("");
            try {
                group.getChildren().clear();
                takeinputs();
                
                
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GrahamScan.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Point2D point : points) {
                    Circle circle = new Circle(point.getX(), point.getY(), 8, Paint.valueOf("#DC143C"));
                    Tooltip tooltip = new Tooltip(circle.getCenterX() + "," + circle.getCenterY());
                    Tooltip.install(circle, tooltip);
                    group.getChildren().add(circle);
                }
        });
    }
     public static void takeinputs() throws FileNotFoundException
    {
        FileChooser  newinputs  = new FileChooser();
        
        newinputs.setInitialDirectory(new File("C:\\Users"));
        newinputs.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("TxT files", "*.txt"));
          
          File fileinput = newinputs.showOpenDialog(null);
         if(fileinput != null)
         {
          Scanner filereader = new Scanner(fileinput);
          Point2D[] polygoninput = new Point2D [Integer.parseInt(filereader.nextLine())]; int i = 0;
          while(filereader.hasNextLine()) { 
            String newline = filereader.nextLine(); 
            String replacedline  = newline.replaceAll("\\[","").replaceAll("\\]","").replaceAll("\n",""); 
            String [] potentialnumbers =  replacedline.split(","); 
             int [] numberargument = new int [2];
             for(int x = 0 ; x < potentialnumbers.length; x++) 
             { 
                 
                 numberargument[x] = Integer.parseInt( potentialnumbers[x]);
             }
            Point2D newPoint = new Point2D((double)numberargument[0],(double)numberargument[1]);
            polygoninput[i] = newPoint; i++;
          } 
         
        
          
        
          points  = Arrays.copyOf(polygoninput,polygoninput.length);
          
         }
    }
     
    public static void onelineadd(Group pane,Point2D startingpoint,Point2D endingpoint)
    {
      
  
            Timeline timeline = new Timeline(new KeyFrame(
             Duration.millis(500),
                event -> {

            Line convexhullline = new Line(startingpoint.getX(), startingpoint.getY(), endingpoint.getX(), endingpoint.getY());
            convexhullline.setStroke(Color.RED);
             pane.getChildren().add(convexhullline);
            
                     } 
                )
            ); 
            timeline.play();
            timeline.setOnFinished((event) ->timeline.play());
    }
}
