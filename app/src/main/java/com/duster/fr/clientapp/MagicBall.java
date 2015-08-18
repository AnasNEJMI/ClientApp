package com.duster.fr.clientapp;

/**
 * Created by Anas on 18/08/2015.
 */
public class MagicBall {

        private String color;
        private String size;
        private String shape;


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
                int value=0;
                switch (color) {
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
                int value=0;
                switch (color) {
                    case "Red":
                        value = 10;
                        break;
                    case "Green":
                        value = 30;
                        break;
                    case "Blue":
                        value = 50;
                        break;
                }

                return value;

            }
}
