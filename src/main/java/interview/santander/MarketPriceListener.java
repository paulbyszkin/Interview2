package interview.santander;

import interview.santander.entities.AdjustedMarketData;
import interview.santander.entities.RawMarketData;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class MarketPriceListener {

    private final CommissionService commissionService;
    private final MarketDataParser marketDataParser;
    private final ConcurrentMap<String, AdjustedMarketData> cache;

    public MarketPriceListener(CommissionService commissionService, MarketDataParser marketDataParser, ConcurrentMap<String, AdjustedMarketData> cache) {
        this.commissionService = commissionService;
        this.marketDataParser = marketDataParser;
        this.cache = cache;
    }

    public void onMessage(String line){
        Optional<RawMarketData> rawMarketData = marketDataParser.parseMarketData(line);
        Optional<AdjustedMarketData> adjustedMarketData = rawMarketData.flatMap(commissionService::applyCommission);
        adjustedMarketData.ifPresent(e-> cache.put(e.rawMarketData().instrumentName(), e));
    }

}
