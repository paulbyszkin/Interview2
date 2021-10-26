package interview.santander.entities;

public record AdjustedMarketData(RawMarketData rawMarketData, double adjustedBid,
                                 double adjustedAsk) {
}
