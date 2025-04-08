package com.example.SECURITY_L4.CONFIG.PROVIDER;

import com.example.SECURITY_L4.CONFIG.AUTHENTICATIONS.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Data
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final String key;

    public CustomAuthenticationProvider(String key){
        this.key = key;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication auth =  (ApiKeyAuthentication) authentication;
        if(key.equals(auth.getKey())){
            auth.setAuthenticated(true);
            return auth;
        }
        throw new BadCredentialsException("Oh No");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
