public class Weather {

    private boolean isGoodWeather;

    public boolean isGoodWeather() {
        return isGoodWeather;
    }

    public void sunny() {
        isGoodWeather = true;
    }

    public void rainy() {
        isGoodWeather = false;
    }
}
