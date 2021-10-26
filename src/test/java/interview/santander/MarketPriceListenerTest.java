package interview.santander;

import interview.santander.entities.AdjustedMarketData;
import interview.santander.entities.RawMarketData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MarketPriceListenerTest {

    private CommissionService commissionService;
    private MarketDataParser marketDataParser;
    private ConcurrentMap<String, AdjustedMarketData> expected;
    private ConcurrentMap<String, AdjustedMarketData> actual;
    private MarketPriceListener marketPriceListener;

    public static final String LINE = "106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001";
    private final RawMarketData rawMarketData = new RawMarketData("106", "EUR/USD", 1.1000, 1.2000, 1591005661001L);
    private final AdjustedMarketData adjustedMarketData = new AdjustedMarketData(rawMarketData, 13, 15);

    @BeforeEach
    void setUp() {
        commissionService = mock(CommissionService.class);
        marketDataParser = mock(MarketDataParser.class);
        expected = new ConcurrentHashMap<>();
        actual = new ConcurrentHashMap<>();
        marketPriceListener = new MarketPriceListener(commissionService, marketDataParser, actual);
        when(marketDataParser.parseMarketData(LINE)).thenReturn(Optional.of(rawMarketData));
        when(commissionService.applyCommission(rawMarketData)).thenReturn(Optional.of(adjustedMarketData));
        expected.put(adjustedMarketData.rawMarketData().instrumentName(), adjustedMarketData);
    }

    @Test
    void add_to_cache() {
        marketPriceListener.onMessage(LINE);

        assertEquals(expected, actual);
    }

    @Test
    void override_cache() {
        String LINE1 = "106,EUR/USD,1.3000,1.2000,01-06-2020 12:01:01:001";
        RawMarketData rawMarketData1 = new RawMarketData("106", "EUR/USD", 1.3000, 1.2000, 1591005661001L);
        AdjustedMarketData adjustedMarketData1 = new AdjustedMarketData(rawMarketData1, 13, 15);

        when(marketDataParser.parseMarketData(LINE1)).thenReturn(Optional.of(rawMarketData1));
        when(commissionService.applyCommission(rawMarketData1)).thenReturn(Optional.of(adjustedMarketData1));

        marketPriceListener.onMessage(LINE);
        marketPriceListener.onMessage(LINE1);

        expected.put(adjustedMarketData1.rawMarketData().instrumentName(), adjustedMarketData1);
        assertEquals(expected, actual);
    }

}