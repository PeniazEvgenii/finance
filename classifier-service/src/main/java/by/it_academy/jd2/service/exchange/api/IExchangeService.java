package by.it_academy.jd2.service.exchange.api;

import java.math.BigDecimal;
import java.util.Map;

public interface IExchangeService {

    Map<String, BigDecimal> getExchangeRate(String base, String target);

    BigDecimal getCurrencyConversionRate(String fromCurrency, String toCurrency);
}
