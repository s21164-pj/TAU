public class Bike {

    private Chain chain;
    private Rider rider;
    private Weather weather;

    private boolean isPedaling;

    public Bike(Chain chain, Rider rider, Weather weather, boolean isPedaling) {
        this.chain = chain;
        this.rider = rider;
        this.weather = weather;
        this.isPedaling = isPedaling;
    }

    public boolean isPedaling() {
        return isPedaling;
    }

    public void setPedaling(boolean pedaling) {
        isPedaling = pedaling;
    }

    public void prepareToRideInWet() {
        weather.rainy();
        chain.setDirty(true);


        if (weather.isGoodWeather() == false) {
            chain.lubeWet();
            cleanBike();
        } else {
            throw new IllegalStateException("sunny day why are you preparing for a rainy day");
        }

    }

    public void prepareToRideInDry() {
        weather.sunny();
        chain.setDirty(true);


        if (weather.isGoodWeather() == false) {
            chain.lubeDry();
            cleanBike();
        } else {
            throw new IllegalStateException("sunny day why are you preparing for a rainy day");
        }

    }

    public void getOnBike() {
        if (rider.isWearsProtective() == false) {
            throw new IllegalStateException("get dress properly");
        }

        if (chain.isDirty()) {
            throw new IllegalStateException("clean your bike!");
        }

        if (chain.getLubType() == "") {
            throw new IllegalStateException("lube your chain!");
        }

        start();
    }

    private void cleanBike() {
        if (chain.isDirty() == true) {
            chain.setDirty(false);
        }
    }

    public void start() {
        isPedaling = true;
    }

}
