package com.duster.fr.clientapp;



public class MagicBall {
    private String color;
    private String size;
    private String shape;



    public MagicBall(){
        this.color="Green";
        this.size="10";
        this.shape="Square";
    }

    public MagicBall(String c, String s, String sh){

        this.color=c;
        this.size=s;
        this.shape=sh;

    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public float getColorValue()
    {
        float value=0;;
        switch (this.getColor()) {
            case "Red":
                value = 1;
                break;
            case "Green":
                value = 2;
                break;
            case "Blue":
                value = 3;
                break;
        }

        return value;

    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getSize()
    {
        return size;
    }

    public float getSizeValue()
    {
        int value = Integer.parseInt(size);
        return value;
    }


    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public String getShape()
    {
        return shape;
    }

    public float getShapeValue()
    {
        int value = 0;

        switch (this.getShape()) {
            case "Circle":
                value = 10;
                break;
            case "Square":
                value = 30;
                break;
            case "Triangle":
                value = 50;
                break;
        }

        return value;

    }
}