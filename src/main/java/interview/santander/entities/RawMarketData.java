package interview.santander.entities;

public record RawMarketData(String id, String instrumentName, double bid, double ask,
                            long timestamp){

}
