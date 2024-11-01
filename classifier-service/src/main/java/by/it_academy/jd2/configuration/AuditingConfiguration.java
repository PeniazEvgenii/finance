package by.it_academy.jd2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("test");

//        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
//                .filter(principal -> principal instanceof UserDetails)
//                .map(principal -> (UserDetails) principal)
//                .map(UserDetails::getUsername);

    }
}
