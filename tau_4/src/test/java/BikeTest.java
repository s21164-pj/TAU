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


}
