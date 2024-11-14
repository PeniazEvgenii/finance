package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.dto.UserToken;
import by.it_academy.jd2.controller.util.JwtTokenHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTH_TYPE = "Bearer ";

    private final JwtTokenHandler jwtTokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(!hasText(authHeader) || !authHeader.startsWith(AUTH_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(AUTH_TYPE.length()).trim();

        if(!jwtTokenHandler.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional.of(token)
                .map(jwtTokenHandler::getUser)
                .ifPresent(user -> setUpAuthentication(request, user));

        filterChain.doFilter(request, response);
    }

    private void setUpAuthentication(HttpServletRequest request, UserToken user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
