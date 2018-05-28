package com.gm.api.constant;

public class Triangle {

    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    public Triangle() {
    }

    public Triangle(double side1, double side2, double side3) {
        if (side1 + side2 <= side3 || side2 + side3 <= side1 || side1 + side3 <= side2) {
            throw new IllegalTriangleException();
        }

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }


    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    public double getArea() {
        double s = 1 / 2 * (this.getSide1() + this.getSide2() + this.getSide3());
        double area = Math.sqrt(s * (s - this.getSide1()) * (s - this.getSide2()) * (s - this.getSide3()));
        return area;
    }

    @Override
    public String toString() {
        return "Triangle{" +
            "side1=" + side1 +
            ", side2=" + side2 +
            ", side3=" + side3 +
            '}';
    }
}
