package ru.xtim.prts.sandbox;

public class MyFirstProgram {

   public static void main(String[] args) {
	   Point p1=new Point(3,4);
	   Point p2=new Point(4,5);
       System.out.println("Результат вычисления расстояние между точками A("+p1.x+","+p1.y+") и B("+p2.x+","+p2.y+") ="+distance(p1,p2));

       System.out.println("Результат вычисления расстояние между точками A("+p1.x+","+p1.y+") и B("+p2.x+","+p2.y+") ="+p2.distance(p1));

   } 	

   public static double distance(Point p1, Point p2){
       return Math.sqrt( Math.pow((p2.x-p1.x),2)+ Math.pow((p2.y-p1.y),2));
   }
}
