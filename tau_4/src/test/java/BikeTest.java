import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BikeTest {

    private Bike bike;
    private Chain chain;
    private Rider rider;
    private Weather weather;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        chain = Mockito.mock(Chain.class);
        rider = Mockito.mock(Rider.class);
        weather = Mockito.mock(Weather.class);

        bike = new Bike(chain, rider, weather,false);
    }

    @Test
    public void isGoodWeatherTest() {
        when(weather.isGoodWeather()).thenReturn(true);
        assertEquals(true, weather.isGoodWeather());

        when(weather.isGoodWeather()).thenReturn(false);
        assertEquals(false, weather.isGoodWeather());
    }

    @Test
    public void cleanChainLubeTest() {
        when(chain.getLubType()).thenReturn("wet");
        assertEquals(false, chain.isDirty());

        when(chain.getLubType()).thenReturn("dry");
        assertEquals(false, chain.isDirty());
    }

    @Test
    public void startTest() {
        when(rider.isWearsProtective()).thenReturn(true);
        when(chain.isDirty()).thenReturn(false);
        when(chain.getLubType()).thenReturn("dry");
        bike.start();
    }

    public void startNoProtectiveTest() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("get dress properly");
        when(rider.isWearsProtective()).thenReturn(false);
        bike.start();
    }

    public void startDirtyBikeTest() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("clean your bike!");
        when(chain.isDirty()).thenReturn(true);
        bike.start();
    }

    public void startNoLubeTest() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("lube your chain!");
        when(chain.getLubType()).thenReturn("");
        bike.start();
    }

    @Test
    public void startPedalingTest() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("already pedaling");
        when(chain.isDirty()).thenReturn(false);
        when(rider.isWearsProtective()).thenReturn(true);
        bike.start();
    }

    @Test
    public void getOnBikeDryTest() {
        when(weather.isGoodWeather()).thenReturn(true);
        bike.prepareToRideInDry();
        bike.getOnBike();
    }

    @Test
    public void getOnBikeWetTest() {
        when(weather.isGoodWeather()).thenReturn(false);
        bike.prepareToRideInWet();
        bike.getOnBike();
    }
}
