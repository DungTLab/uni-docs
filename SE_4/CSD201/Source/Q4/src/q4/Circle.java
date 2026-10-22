/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q4;

import java.util.Map;
/**
 *
 * @author LENOVO
 */
public class Circle extends Point{
    private double radius;

    public Circle() {
        super(0, 0);
        this.radius = 1;
    }

    public Circle(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
 
    public double getDiameter(){
        return radius*2;
    }
    
    public double getCircumference(){
        return 2*Math.PI*radius;
    }
    
    public double getArea(){
        return Math.PI*radius*radius;
    }

    @Override
    public String toString() {
        return String.format("Circle{Center{x=%d,y=%d},radius=%.2f,diameter=%.2f,circumference=%.2f,area=%.2f}", getX(), getY(), radius, getDiameter(), getCircumference(), getArea());
    }
}
