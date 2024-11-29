package by.it_academy.jd2.schedulerservice.service.feign.client;

import by.it_academy.jd2.commonlib.dto.OperationFeignDto;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.feign.dto.AccountInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "account", url = "${service.account}")
public interface IAccountClient {

    @GetMapping("/info/account/{uuid}/user/{uuid_user}")
    ResponseEntity<AccountInfoDto> findAccountByIdAndUserId(@PathVariable("uuid") UUID id,
                                                            @PathVariable("uuid_user") UUID userId);

    @PostMapping("info/account/operation")
    ResponseEntity<UUID> createOperation(@RequestBody OperationFeignDto feignDto);
}
