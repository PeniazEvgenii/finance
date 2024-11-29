package by.it_academy.jd2.service.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class RateDto {

    boolean success;

    private String source;

    private Map<String, BigDecimal> quotes;

}
