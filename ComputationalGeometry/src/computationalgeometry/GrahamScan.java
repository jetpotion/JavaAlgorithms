/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computationalgeometry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @Email: William Zhang
 */
public class GrahamScan extends Application {

    /**
     * @param args the command line arguments
     */
    public static Point2D[] points;
    public static TextArea convexhulloutput;

    public static void main(String[] args) throws FileNotFoundException {
        
          Scanner scanner = new Scanner(System.in);
          System.out.println("What is the file name");       
          File fileinput = new File(scanner.nextLine());
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
         points = Arrays.copyOf( polygoninput,polygoninput.length);
        
          
        
        
        //    System.out.println(Arrays.toString(points));
       

        Application.launch(args);
        
    }

    public static Point2D returnlowestPoint(Point2D [] points) {
        Point2D lowest = points[0];
        for (int i = 1; i < points.length; i++) {

            Point2D temp = points[i];

            if (temp.getY() < lowest.getY() || (temp.getY() == lowest.getY() && temp.getX() < lowest.getX())) {
                lowest = temp;
            }
        }

        return lowest;

    }

    /**
     * to be done but create the lines on the pane itself *
     */

    public static Stack<Point2D> createConvexhull(Group pane, Point2D[] points) throws Exception {
        
        
        
        Arrays.sort(points,polarorder(points));
        Stack<Point2D> Convexhull = new Stack<>();
        Convexhull.push(points[0]);
        Convexhull.push(points[1]);
        Point2D firstPoint = Convexhull.firstElement();
        Point2D secondPoint  = Convexhull.lastElement();
        Line firstline = new Line(firstPoint.getX(),firstPoint.getY(),secondPoint.getX(),secondPoint.getY());
        firstline.setStroke(Color.RED);
        pane.getChildren().add(firstline);
        
                
        int y = 2;
        //What if the first 3 points are colinear
        if (orientation(nextoStack(Convexhull), Convexhull.peek(), points[2]) == 0) {
           Point2D removedPoint =  Convexhull.pop();
           Point2D previousPoint = Convexhull.peek();
           Line line = new Line(previousPoint.getX(),previousPoint.getY(),removedPoint.getX(),removedPoint.getY());
           slowlyremovelines(line,pane);
           Point2D addedPoint = points[2];
            Convexhull.push(addedPoint);
            onelineadd(pane,previousPoint,addedPoint);
            y++;
        //incase in the fact that the 3rd point is counter clock wise
        } else if (orientation(nextoStack(Convexhull), Convexhull.peek(), points[2]) < 0) {
            y++;
        }
        //case in the fact the third point is a point on the convexhull
        else {
            Convexhull.push(points[2]);
            onelineadd(pane,nextoStack(Convexhull), Convexhull.peek());
            determinePointOnHull(Convexhull,pane);
            y++;
        }
        for (int x = y; x < points.length; x++) {
          
            while (orientation(nextoStack(Convexhull), Convexhull.peek(), points[x]) < 0) {
                Point2D removed = Convexhull.pop();
                Point2D previousPoint = Convexhull.peek();
                Line line = new Line(previousPoint.getX(),previousPoint.getY(), removed.getX(),removed.getY());
                slowlyremovelines(line,pane);
                 determinePointOnHull(Convexhull,pane);
                
            }
            if (orientation(nextoStack(Convexhull), Convexhull.peek(), points[x]) == 0) {
                Point2D removedPoint =  Convexhull.pop();
                Point2D previousPoint = Convexhull.peek();
                Line line = new Line(previousPoint.getX(),previousPoint.getY(),removedPoint.getX(),removedPoint.getY());
                slowlyremovelines(line,pane);
                Point2D addedPoint = points[x];
                Convexhull.push(addedPoint);
                onelineadd(pane,previousPoint,addedPoint);
              
                 determinePointOnHull(Convexhull,pane);
            } else if (orientation(nextoStack(Convexhull), Convexhull.peek(), points[x]) == 1) {
                Convexhull.push(points[x]);
                onelineadd(pane,nextoStack(Convexhull),Convexhull.peek());
                determinePointOnHull(Convexhull,pane);
            }

        }
  
            
            
            
            
            
            
            
            Point2D lastpoint = Convexhull.lastElement();
            Point2D firstpoint = Convexhull.firstElement();
            Line hullline = new Line(lastpoint.getX(), lastpoint.getY(), firstpoint.getX(), firstpoint.getY());
            hullline.setStroke(Color.RED);
            pane.getChildren().add(hullline);
        
        
        
        
        
       
       
        String[] newconvexhull = new String [Convexhull.size()];
        int x = 0;
        for(Point2D point : Convexhull)
        {
           newconvexhull[x] = point.toString();
           x++;
           
        }
        StringBuilder stringbuild = new StringBuilder();
        for(String strings: newconvexhull)
        {
               stringbuild.append(strings + "\n");
        }
        convexhulloutput.setText(Convexhull.size() + "\n " +  stringbuild.toString());
         
        return Convexhull;

    }
    /**Gets the point next to the stack
     * @param points The current points on the stack **/
    
    public static Point2D nextoStack(Stack<Point2D> points) {
        Stack<Point2D> firststack = new Stack<>();
        firststack.addAll(points);
        firststack.pop();
        return firststack.peek();

    } 
    /**
     * This method make sures that the line is being removed slowly
     * @param line the line to which we are removing from
     * @param Pane the group pane that we are removing from
     */
    public static void slowlyremovelines(Line line, Group Pane)
    {
            if(Pane.getChildren().contains(line))
            {
                Timeline timeline = new Timeline(new KeyFrame(
             Duration.millis(500),
                event -> {
            
             Pane.getChildren().remove(line);
            
                     } 
                )
            ); 
            timeline.play();
            timeline.setOnFinished((event) ->timeline.play());
    
            }
    }
    /** This method checks if point is on the hull and mark it blue but this will not affect the run time as this is just 
     * a method checker to see the points on the screen
     * @param Convexhull is the current Convexhull 
     * @param pane is the group that the points are being add too **/ 
    public static void determinePointOnHull(Stack<Point2D>Convexhull, Group pane)
    {
         for (Iterator<Node> it = pane.getChildren().iterator(); it.hasNext();) {
            Node node = it.next();
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                Point2D point = new Point2D(circle.getCenterX(), circle.getCenterY());
                if (Convexhull.contains(point)) {
                    Tooltip.install(circle, new Tooltip(point.toString() + "Convexhull status:True"));
                    circle.setFill(Color.BLUE);
                } 
                else {
                    Tooltip.install(circle, new Tooltip(point.toString() + "Convexhullstatus: False"));
                    circle.setFill(Color.RED);
                }

            }
            
            
        }
    }

    /**
     * @param a is the point next to stack 
     * @param b the top of the stack
     * @param c  the next item that is the candidate for the stack
     */
    public static int orientation(Point2D a, Point2D b, Point2D c) {
        long area2 = (long) ((((long) b.getX() - a.getX()) * ((long) c.getY() - a.getY()))
                - (((long) b.getY() - a.getY()) * ((long) c.getX() - a.getX())));
        if (area2 < 0) {
            return -1;
        } else if (area2 > 0) {
            return +1;
        } else {
            return 0;
        }

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
        convexhulloutput = new  TextArea("Output");
       
        box.getChildren().add(convexhulloutput);
        box.setSpacing(50);
        
       
        stage.setScene(new Scene(newHbox,1920,960));
       
       
        stage.show();
    }
   

    /**
     * assume points are already sorted by polar angle * 
     */
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
    public  static Comparator <Point2D> polarorder(Point2D[] points )
    {
         Comparator<Point2D> comparable = (Point2D a, Point2D b) -> {
            Point2D lowest = returnlowestPoint(points);
            if (a == b || a.equals(b)) {
                return 0;
            }

            // use longs to guard against int-underflow
            double thetaA = Math.atan2((long) a.getY() - lowest.getY(), (long) a.getX() - lowest.getX());
            double thetaB = Math.atan2((long) b.getY() - lowest.getY(), (long) b.getX() - lowest.getX());

            if (thetaA < thetaB) {
                return -1;
            } else if (thetaA > thetaB) {
                return 1;
            } else {

                double distanceA = Math.sqrt((((long) lowest.getX() - a.getX()) * ((long) lowest.getX() - a.getX()))
                        + (((long) lowest.getY() - a.getY()) * ((long) lowest.getY() - a.getY())));
                double distanceB = Math.sqrt((((long) lowest.getX() - b.getX()) * ((long) lowest.getX() - b.getX()))
                        + (((long) lowest.getY() - b.getY()) * ((long) lowest.getY() - b.getY())));

                if (distanceA < distanceB) {
                    return -1;
                } else if (distanceA > distanceB) {
                    return 1;
                } else {
                    return 0;
                }
            }

        };
        return comparable;
    }
    public static void takeinputs() throws FileNotFoundException
    {
        FileChooser  newinputs  = new FileChooser();
        
        newinputs.setInitialDirectory(new File("C:\\Users"));
        newinputs.setSelectedExtensionFilter(new ExtensionFilter("TxT files", "*.txt"));
          
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