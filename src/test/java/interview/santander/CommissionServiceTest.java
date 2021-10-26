package interview.santander;

import interview.santander.entities.AdjustedMarketData;
import interview.santander.entities.RawMarketData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommissionServiceTest {

    private final RawMarketData rawMarketData = new RawMarketData("106", "EUR/USD", 1.1000, 1.2000, 1591005661001L);

    @Test
    void should_apply_commission() {
        //Could use Guava map builder
        Map<String, Double> commissions = new HashMap<>();
        commissions.put("EUR/USD", 0.1d);
        double adjustedBid = rawMarketData.bid() - rawMarketData.bid() * 0.1d;
        double adjustedAsk = rawMarketData.ask() + rawMarketData.ask() * 0.1d;
        Optional<AdjustedMarketData> result = new CommissionService(commissions).applyCommission(rawMarketData);
        Optional<AdjustedMarketData> expected = Optional.of(new AdjustedMarketData(rawMarketData, adjustedBid, adjustedAsk));
        assertEquals(expected, result);
    }

    @Test
    void return_empty_on_missing_config() {
        Optional<AdjustedMarketData> result = new CommissionService(new HashMap<>()).applyCommission(rawMarketData);
        assertEquals(Optional.empty(), result);
    }
}