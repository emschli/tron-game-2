package haw.vs.common;

import javafx.scene.paint.Color;

/**
 * @author Daniel Sarnow (daniel.sarnow@haw-hamburg.de)
 * @version 0.1
 */
public class ViewUtility {

    /**
     * Returns the three-byte hexadecimal representation of the specified color as a string with a leading # sign.
     *
     * @param color color to be converted
     * @return three-byte hexadecimal representation, e.g. #3b5baa
     */
    public static String getHexTriplet(Color color){
        String r = String.format("%x", (int)(color.getRed()*255));
        String g = String.format("%x", (int)(color.getGreen()*255));
        String b = String.format("%x", (int)(color.getBlue()*255));

        return "#" + r + g + b;
    }
}