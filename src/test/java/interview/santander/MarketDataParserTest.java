package interview.santander;

import interview.santander.entities.RawMarketData;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MarketDataParserTest {

    @Test
    public void parse_market_data() {
        /*
            original csv data from task was untrimmed, I am assuming that we have quality feed with trimmed data

            from doc: 106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
            assumption: 106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001
         */

        Optional<RawMarketData> actual = new MarketDataParser().parseMarketData("106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001");
        Optional<RawMarketData> expected = Optional.of(new RawMarketData("106", "EUR/USD", 1.1000, 1.2000, 1591005661001L));

        assertEquals(expected, actual);
    }

    @Test
    public void return_empty_on_out_of_bounds_exception() {

        //Could use either or rethrow but assuming that feed is high quality and LOG + None will be enough to figure out occasional hick-ups.
        Optional<RawMarketData> actual = new MarketDataParser().parseMarketData("106,EUR/USD,1.1000,1.2000");
        assertEquals(Optional.empty(), actual);
    }
}