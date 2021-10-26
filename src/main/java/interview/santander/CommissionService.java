package interview.santander;

import interview.santander.entities.AdjustedMarketData;
import interview.santander.entities.RawMarketData;

import java.util.Map;
import java.util.Optional;

public class CommissionService {

    //trivial commission config, in real world it will be based on currency pair, side, volume, bucket to which customer belongs etc.
    private final Map<String, Double> commissions;

    public CommissionService(Map<String, Double> commissions) {
        this.commissions = commissions;
    }

    public Optional<AdjustedMarketData> applyCommission(RawMarketData rawMarketData) {
        //symmetric spread, assuming commissions is not null, could enforce via invariant in constructor
        Optional<Double> commission = Optional.ofNullable(commissions.get(rawMarketData.instrumentName()));
        
        return commission.map(e->{
            double adjustedBid = rawMarketData.bid() - rawMarketData.bid() * e;
            double adjustedAsk = rawMarketData.ask() + rawMarketData.ask() * e;
            return new AdjustedMarketData(rawMarketData, adjustedBid, adjustedAsk);
        });
    }
}
