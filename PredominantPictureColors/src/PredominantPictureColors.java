import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class PredominantPictureColors {

    /**
     * Takes in an array of RGB values and returns the name of the most common
     * color
     *
     * @param pixelColors
     *            = array of RGB values of each pixel to be analyzed
     * @return name of most common color
     */
    public static String findPredominantColor(int[] pixelColors) {
        int numBlack = 0, numWhite = 0, numGrey = 0, numRed = 0, numOrange = 0;
        int numYellow = 0, numYellowGreen = 0, numGreen = 0, numCyanGreen = 0;
        int numCyan = 0, numBlue = 0, numPurple = 0, numMagenta = 0;

        //Loop through the array of RGB values, convert RGB to HSV, and determine which color the pixel is
        for (int i = 0; i < pixelColors.length; i += 3) {
            ColorInfo currentColor = new ColorInfo(0.0, 0.0, 0.0);

            //Convert RGB values from being in range [0,255] to [0,1]
            double convertedRed = pixelColors[i] / 255.0;
            double convertedGreen = pixelColors[i + 1] / 255.0;
            double convertedBlue = pixelColors[i + 2] / 255.0;
            currentColor.setValue(convertedRed, convertedGreen, convertedBlue);
            currentColor.setHue(convertedRed, convertedGreen, convertedBlue);
            currentColor.setSaturation();

            //to deal with possible negative values for hue, if less than 360 add 360 to it
            if (currentColor.hue < 0) {
                currentColor.hue += 360;
            }

            //Determine which color the pixel is based on saturation, value, and hue
            if (currentColor.value < 1 / 16.0) {
                numBlack++;
            } else if (currentColor.saturation < 1 / 8.0
                    && currentColor.value > 7 / 8.0) {
                numWhite++;
            } else if (currentColor.saturation < 1 / 8.0) {
                numGrey++;
            } else if ((currentColor.hue >= 0 && currentColor.hue < 15)
                    || (currentColor.hue >= 336)) {
                numRed++;
            } else if (currentColor.hue >= 15 && currentColor.hue < 56) {
                numOrange++;
            } else if (currentColor.hue >= 56 && currentColor.hue < 66) {
                numYellow++;
            } else if (currentColor.hue >= 66 && currentColor.hue < 110) {
                numYellowGreen++;
            } else if (currentColor.hue >= 110 && currentColor.hue < 150) {
                numGreen++;
            } else if (currentColor.hue >= 150 && currentColor.hue < 170) {
                numCyanGreen++;
            } else if (currentColor.hue >= 170 && currentColor.hue < 194) {
                numCyan++;
            } else if (currentColor.hue >= 194 && currentColor.hue < 264) {
                numBlue++;
            } else if (currentColor.hue >= 264 && currentColor.hue < 290) {
                numPurple++;
            } else if (currentColor.hue >= 290 && currentColor.hue < 336) {
                numMagenta++;
            } else {
                System.out.println("hue: " + currentColor.hue);
                System.out
                        .println("Error with for loop in findPredominantColor");
            }
        }

        //Find which color has the most pixels of that color and return it as a string
        if (numBlack > numWhite && numBlack > numGrey && numBlack > numRed
                && numBlack > numOrange && numBlack > numYellow
                && numBlack > numYellowGreen && numBlack > numGreen
                && numBlack > numCyanGreen && numBlack > numCyan
                && numBlack > numBlue && numBlack > numPurple
                && numBlack > numMagenta) {
            return "black";
        } else if (numWhite > numBlack && numWhite > numGrey
                && numWhite > numRed && numWhite > numOrange
                && numWhite > numYellow && numWhite > numYellowGreen
                && numWhite > numGreen && numWhite > numCyanGreen
                && numWhite > numCyan && numWhite > numBlue
                && numWhite > numPurple && numWhite > numMagenta) {
            return "white";
        } else if (numGrey > numBlack && numGrey > numWhite && numGrey > numRed
                && numGrey > numOrange && numGrey > numYellow
                && numGrey > numYellowGreen && numGrey > numGreen
                && numGrey > numCyanGreen && numGrey > numCyan
                && numGrey > numBlue && numGrey > numPurple
                && numGrey > numMagenta) {
            return "grey";
        } else if (numRed > numBlack && numRed > numWhite && numRed > numGrey
                && numRed > numOrange && numRed > numYellow
                && numRed > numYellowGreen && numRed > numGreen
                && numRed > numCyanGreen && numRed > numCyan && numRed > numBlue
                && numRed > numPurple && numRed > numMagenta) {
            return "red";
        } else if (numOrange > numBlack && numOrange > numWhite
                && numOrange > numGrey && numOrange > numRed
                && numOrange > numYellow && numOrange > numYellowGreen
                && numOrange > numGreen && numOrange > numCyanGreen
                && numOrange > numCyan && numOrange > numBlue
                && numOrange > numPurple && numOrange > numMagenta) {
            return "orange";
        } else if (numYellow > numBlack && numYellow > numWhite
                && numYellow > numGrey && numYellow > numRed
                && numYellow > numOrange && numYellow > numYellowGreen
                && numYellow > numGreen && numYellow > numCyanGreen
                && numYellow > numCyan && numYellow > numBlue
                && numYellow > numPurple && numYellow > numMagenta) {
            return "yellow";
        } else if (numYellowGreen > numBlack && numYellowGreen > numWhite
                && numYellowGreen > numGrey && numYellowGreen > numRed
                && numYellowGreen > numOrange && numYellowGreen > numYellow
                && numYellowGreen > numGreen && numYellowGreen > numCyanGreen
                && numYellowGreen > numCyan && numYellowGreen > numBlue
                && numYellowGreen > numPurple && numYellowGreen > numMagenta) {
            return "yellow green";
        } else if (numGreen > numBlack && numGreen > numWhite
                && numGreen > numGrey && numGreen > numRed
                && numGreen > numOrange && numGreen > numYellow
                && numGreen > numYellowGreen && numGreen > numCyanGreen
                && numGreen > numCyan && numGreen > numBlue
                && numGreen > numPurple && numGreen > numMagenta) {
            return "green";
        } else if (numCyanGreen > numBlack && numCyanGreen > numWhite
                && numCyanGreen > numGrey && numCyanGreen > numRed
                && numCyanGreen > numOrange && numCyanGreen > numYellow
                && numCyanGreen > numYellowGreen && numCyanGreen > numGreen
                && numCyanGreen > numCyan && numCyanGreen > numBlue
                && numCyanGreen > numPurple && numCyanGreen > numMagenta) {
            return "cyan green";
        } else if (numCyan > numBlack && numCyan > numWhite && numCyan > numGrey
                && numCyan > numRed && numCyan > numOrange
                && numCyan > numYellow && numCyan > numYellowGreen
                && numCyan > numGreen && numCyan > numCyanGreen
                && numCyan > numBlue && numCyan > numPurple
                && numCyan > numMagenta) {
            return "cyan";
        } else if (numBlue > numBlack && numBlue > numWhite && numBlue > numGrey
                && numBlue > numRed && numBlue > numOrange
                && numBlue > numYellow && numBlue > numYellowGreen
                && numBlue > numGreen && numBlue > numCyanGreen
                && numBlue > numCyan && numBlue > numPurple
                && numBlue > numMagenta) {
            return "blue";
        } else if (numPurple > numBlack && numPurple > numWhite
                && numPurple > numGrey && numPurple > numRed
                && numPurple > numOrange && numPurple > numYellow
                && numPurple > numYellowGreen && numPurple > numGreen
                && numPurple > numCyanGreen && numPurple > numCyan
                && numPurple > numBlue && numPurple > numMagenta) {
            return "purple";
        } else if (numMagenta > numBlack && numMagenta > numWhite
                && numMagenta > numGrey && numMagenta > numRed
                && numMagenta > numOrange && numMagenta > numYellow
                && numMagenta > numYellowGreen && numMagenta > numGreen
                && numMagenta > numCyanGreen && numMagenta > numCyan
                && numMagenta > numBlue && numMagenta > numPurple) {
            return "magenta";
        } else {
            return "There is no single most common color, at least 2 colors are codominant";
        }
    }

    public static void main(String[] args) throws IOException {
        //Create scanner object and image and file objects
        Scanner in = new Scanner(System.in);
        BufferedImage userImage = null;

        //Ask user for image file to read
        System.out.println("Enter image file: ");
        String fileName = in.nextLine();

        //Try to read and store the image
        try {
            userImage = ImageIO.read(new File("test_images/" + fileName)); //location of picture is in test_images folder
        } catch (IOException e) {
            System.out.println("Ruh roh, error: " + e);
        }

        //Find and store width and height of image
        int imageWidth = userImage.getWidth();
        int imageHeight = userImage.getHeight();

        //Create array to hold the colors of each pixel
        int[] pixelColors = new int[imageHeight * imageWidth * 3]; //multiply by 3 to hold red, green, and blue values for each pixel
        int arrayIndex = 0;

        //Loop through the picture and check the color of each pixel
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                int pixelColor = userImage.getRGB(x, y);

                //Calculating the red, green, and blue values using shifting

                //playing around with stackoverflow code to see what it means/does
                int redValue = (pixelColor & 0x00ff0000) >> 16;
                int greenValue = (pixelColor & 0x0000ff00) >> 8;
                int blueValue = pixelColor & 0x000000ff;

                //Store color values in array
                pixelColors[arrayIndex] = redValue;
                arrayIndex++;
                pixelColors[arrayIndex] = greenValue;
                arrayIndex++;
                pixelColors[arrayIndex] = blueValue;
                arrayIndex++;
            }
        }

        //Find the most predominant color
        String color = findPredominantColor(pixelColors);

        //If there is a dominant color, print out the color
        if (color.length() < 20) {
            System.out.println("The most common color is " + color);
        }
        //Otherwise the method call returns a long text about there being no dominant color
        else {
            System.out.println(color);
        }

        //Close input stream
        in.close();
    }

}
