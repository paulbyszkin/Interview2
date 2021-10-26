package interview.santander;

import interview.santander.entities.AdjustedMarketData;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class MarketDataResource {

    private final ConcurrentMap<String, AdjustedMarketData> cache;

    public MarketDataResource(ConcurrentMap<String, AdjustedMarketData> cache) {
        this.cache = cache;
    }

    /*
    This should return type / Resource and get params, be annotated for REST exporter.
     */
    public void dummyEndpoint(String instrumentName) {
        Optional<AdjustedMarketData> data = Optional.ofNullable(cache.get(instrumentName));
        System.out.println(data.map(e-> String.format("Bid %s, Ask %s", e.adjustedBid(), e.adjustedAsk())).orElse("Not available"));
    }
}
