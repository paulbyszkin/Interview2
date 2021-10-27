package interview.santander.entities;

import java.util.Objects;

public final class RawMarketData {
    private final String id;
    private final String instrumentName;
    private final double bid;
    private final double ask;
    private final long timestamp;

    public RawMarketData(String id, String instrumentName, double bid, double ask,
                         long timestamp) {
        this.id = id;
        this.instrumentName = instrumentName;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    public String instrumentName() {
        return instrumentName;
    }

    public double bid() {
        return bid;
    }

    public double ask() {
        return ask;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        RawMarketData that = (RawMarketData) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.instrumentName, that.instrumentName) &&
                Double.doubleToLongBits(this.bid) == Double.doubleToLongBits(that.bid) &&
                Double.doubleToLongBits(this.ask) == Double.doubleToLongBits(that.ask) &&
                this.timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instrumentName, bid, ask, timestamp);
    }

    @Override
    public String toString() {
        return "RawMarketData[" +
                "id=" + id + ", " +
                "instrumentName=" + instrumentName + ", " +
                "bid=" + bid + ", " +
                "ask=" + ask + ", " +
                "timestamp=" + timestamp + ']';
    }


}
