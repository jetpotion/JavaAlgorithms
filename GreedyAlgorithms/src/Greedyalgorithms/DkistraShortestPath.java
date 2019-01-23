/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedyalgorithms;

/**
 *
 * @author William Zhang
 */
import DataStructures.FibonacciHeap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DkistraShortestPath extends Application {
  /** To be done later but a fibonacci heap is required for future reference. This will speed up run time from O(Vlog(E)) to O(E + Vlog(v)) **/
   /** The motivation behind this designed with a fibonacci heap is prevent the cardinality of the edges **/

    public static Point2D[] points;
      public static TextArea convexhulloutput;

    public static void main(String[] args){
        
         
        
          
        
        
        //    System.out.println(Arrays.toString(points));
       

        Application.launch(args);
        
    
}

    private static void djikstra(Group group, Point2D[] points) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Convex hull");
        Group group = new Group();
        Pane pane = new Pane(group);
        Button button = new Button("ConvexHull");
        Button newinput = new Button("New inputs");
        HBox buttontoolbars = new HBox(newinput,button);
        buttontoolbars.setAlignment(Pos.CENTER);
        buttontoolbars.setSpacing(10);
        loadPoints(group ,points, pane, button, newinput);
      //  pane.getChildren().add(button);
        HBox newHbox = new HBox();
         VBox box = new VBox();
         
        newHbox.getChildren().add(box);
        box.getChildren().add(buttontoolbars);
        
        newHbox.getChildren().add(pane);
        box.setAlignment(Pos.TOP_CENTER);
       
       
        box.getChildren().add(convexhulloutput);
        box.setSpacing(50);
        
       
        stage.setScene(new Scene(newHbox,1920,960));
       
       
        stage.show();
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
                djikstra(group, points);
            } catch (Exception ex) {
                Logger.getLogger(DkistraShortestPath.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
                
        );
        newinput.setOnAction((event)->{
            convexhulloutput.setText("");
            try {
                group.getChildren().clear();
                takeinputs();
                
                
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DkistraShortestPath.class.getName()).log(Level.SEVERE, null, ex);
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
}
