package by.it_academy.jd2.service.exchange.client;

import by.it_academy.jd2.service.exchange.dto.RateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchangeRateClient", url = "${api.access.url}")
public interface ExchangeRateClient {

    @GetMapping("/live")
    RateDto getExchangeRate(@RequestParam("base") String base,
                            @RequestParam("symbols") String target,
                            @RequestParam("access_key") String accessKey);
}
