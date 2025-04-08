package com.example.SECURITY_L4.CONFIG.MANAGER;

import com.example.SECURITY_L4.CONFIG.FILTERS.ApiKeyFilter;
import com.example.SECURITY_L4.CONFIG.PROVIDER.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class CustomAuthenticatoinManager implements AuthenticationManager {

//    public CustomAuthenticationProvider provider;
    private final String key;
    public CustomAuthenticatoinManager(String key){
        this.key = key;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var provider = new CustomAuthenticationProvider(key);
        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        else{
            return authentication;
        }
    }
}
