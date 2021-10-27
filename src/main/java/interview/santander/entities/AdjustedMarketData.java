package interview.santander.entities;

import java.util.Objects;

public final class AdjustedMarketData {
    private final RawMarketData rawMarketData;
    private final double adjustedBid;
    private final double adjustedAsk;

    public AdjustedMarketData(RawMarketData rawMarketData, double adjustedBid,
                              double adjustedAsk) {
        this.rawMarketData = rawMarketData;
        this.adjustedBid = adjustedBid;
        this.adjustedAsk = adjustedAsk;
    }

    public RawMarketData rawMarketData() {
        return rawMarketData;
    }

    public double adjustedBid() {
        return adjustedBid;
    }

    public double adjustedAsk() {
        return adjustedAsk;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        AdjustedMarketData that = (AdjustedMarketData) obj;
        return Objects.equals(this.rawMarketData, that.rawMarketData) &&
                Double.doubleToLongBits(this.adjustedBid) == Double.doubleToLongBits(that.adjustedBid) &&
                Double.doubleToLongBits(this.adjustedAsk) == Double.doubleToLongBits(that.adjustedAsk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawMarketData, adjustedBid, adjustedAsk);
    }

    @Override
    public String toString() {
        return "AdjustedMarketData[" +
                "rawMarketData=" + rawMarketData + ", " +
                "adjustedBid=" + adjustedBid + ", " +
                "adjustedAsk=" + adjustedAsk + ']';
    }

}
