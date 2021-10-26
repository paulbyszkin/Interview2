package interview.santander;

import interview.santander.entities.RawMarketData;

import java.text.SimpleDateFormat;
import java.util.Optional;

/*
Parser is doing quite heavy lifting, could be moved out of listener loop. Exceptions can be heavy on poor quality feeds.
 */

public class MarketDataParser {

    // Not thread safe
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");

    public Optional<RawMarketData> parseMarketData(String line) {
        String[] tokens = line.split(",");
        try{
            return Optional.of(new RawMarketData(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), simpleDateFormat.parse(tokens[4]).getTime()));
        }catch (Exception e) {
            //LOG error
            return Optional.empty();
        }

    }
}
