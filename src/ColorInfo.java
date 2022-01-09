//Need to call setValue before calling setHue
public class ColorInfo {
    public double hue;
    public double saturation;
    public double value;
    public double max;
    public double min;
    public double range;

    /**
     * Constructor for ColorInfo
     *
     * @param startHue
     *            = starting hue value
     * @param startSaturation
     *            = starting saturation value
     * @param startValue
     *            = starting value value
     */
    public ColorInfo(double startHue, double startSaturation,
            double startValue) {
        this.hue = startHue;
        this.saturation = startSaturation;
        this.value = startValue;
    }

    /**
     * Calculates the Hue part of HSV as an angle from RGB
     *
     * @param redValue
     *            = red RGB value in range[0,1]
     * @param greenValue
     *            = green RGB value in range [0,1]
     * @param blueValue
     *            = blue RGB value in range [0,1]
     */
    public void setHue(double redValue, double greenValue, double blueValue) {
        this.findMin(redValue, greenValue, blueValue);
        this.range = this.max - this.min;
        if (this.range - 0 < 0.0001) {
            this.hue = 0;
        } else if (Math.abs(this.value - redValue) < 0.0001) {
            this.hue = 60 * ((greenValue - blueValue) / this.range);
        } else if (Math.abs(this.value - greenValue) < 0.0001) {
            this.hue = 60 * (2 + (blueValue - redValue) / this.range);
        } else if (Math.abs(this.value - blueValue) < 0.0001) {
            this.hue = 60 * (4 + (redValue - greenValue) / this.range);
        } else {
            System.out.println("Error with setHue method");
        }

    }

    /**
     * Calculates the Saturation part of HSV from RGB
     */
    public void setSaturation() {
        //If value = 0, saturation = 0
        if (Math.abs(this.value - 0) < 0.0001) {
            this.saturation = 0.0;
        } else {
            this.saturation = this.range / this.value;
        }
    }

    /**
     * Calculates the Value part of HSV from RGB by finding max RGB value
     */
    public void setValue(double redValue, double greenValue, double blueValue) {
        this.findMax(redValue, greenValue, blueValue);
        this.value = this.max;
    }

    /**
     * Calculates max(R,G,B)
     *
     * @param redValue
     *            = red RGB value in range[0,1]
     * @param greenValue
     *            = green RGB value in range [0,1]
     * @param blueValue
     *            = blue RGB value in range [0,1]
     */
    public void findMax(double redValue, double greenValue, double blueValue) {
        if (redValue >= greenValue && redValue >= blueValue) {
            this.max = redValue;
        } else if (greenValue > redValue && greenValue > blueValue) {
            this.max = greenValue;
        } else {
            this.max = blueValue;
        }
    }

    /**
     * Calculates min(R,G,B)
     *
     * @param redValue
     *            = red RGB value in range[0,1]
     * @param greenValue
     *            = green RGB value in range [0,1]
     * @param blueValue
     *            = blue RGB value in range [0,1]
     */
    public void findMin(double redValue, double greenValue, double blueValue) {
        if (redValue <= greenValue && redValue <= blueValue) {
            this.min = redValue;
        } else if (greenValue < redValue && greenValue < blueValue) {
            this.min = greenValue;
        } else {
            this.min = blueValue;
        }
    }

}
