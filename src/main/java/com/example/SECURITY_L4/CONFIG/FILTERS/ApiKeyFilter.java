package com.example.SECURITY_L4.CONFIG.FILTERS;

import com.example.SECURITY_L4.CONFIG.AUTHENTICATIONS.ApiKeyAuthentication;
import com.example.SECURITY_L4.CONFIG.MANAGER.CustomAuthenticatoinManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Data
@NoArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private String key;

    public ApiKeyFilter(String key) {
        this.key = key;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticatoinManager manager = new CustomAuthenticatoinManager(key);
        var requestkey = request.getHeader("x-api-key");

        if(requestkey == null || "null".equals(requestkey)){
            filterChain.doFilter(request,response);
        }

        var auth = new ApiKeyAuthentication(requestkey);

        try{
            var a = manager.authenticate(auth);
            if (a.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }


}
