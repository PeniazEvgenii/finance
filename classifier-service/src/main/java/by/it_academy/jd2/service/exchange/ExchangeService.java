package by.it_academy.jd2.service.exchange;

import by.it_academy.jd2.service.exchange.api.IExchangeService;
import by.it_academy.jd2.service.exchange.client.ExchangeRateClient;
import by.it_academy.jd2.service.exchange.dto.RateDto;
import by.it_academy.jd2.service.exchange.exception.ExchangeRateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService implements IExchangeService {

    private static final String USD = "USD";

    private final ExchangeRateClient exchangeRateClient;

    @Value("${api.access.key}")
    private final String apiKey;

    public Map<String, BigDecimal> getExchangeRate(String base, String target) {
        String currencyPair = base + target;

        RateDto rateDto = exchangeRateClient.getExchangeRate(base, target, apiKey);

        if (rateDto == null || !rateDto.isSuccess() || rateDto.getQuotes() == null) {
            throw new ExchangeRateException("Failed to fetch exchange rate for " + base + " to " + target);
        }

        return rateDto.getQuotes();
    }

    @Override
    public BigDecimal getCurrencyConversionRate(String fromCurrency, String toCurrency) {
        Map<String, BigDecimal> rates = getExchangeRate(fromCurrency, toCurrency);

        //на данном ресурсе конвертация вся парами USD*
        rates.putIfAbsent(USD + USD, BigDecimal.ONE);
        String fromCurrencyPair = USD + fromCurrency;
        String toCurrencyPair = USD + toCurrency;

        BigDecimal fromCurrencyRate = rates.get(fromCurrencyPair);
        BigDecimal toCurrencyRate = rates.get(toCurrencyPair);

        if (fromCurrencyRate == null || toCurrencyRate == null) {
            throw new ExchangeRateException("Error get exchange rate");
        }

        return toCurrencyRate.divide(fromCurrencyRate, 6, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }

}
