package br.com.gok.starwarsapi.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SimpleCORSFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc)
      throws ServletException, IOException {
    String origin = hsr.getHeader("origin");
    origin = (origin == null || origin.equals("")) ? "null" : origin;
    hsr1.addHeader("Access-Control-Allow-Origin", origin);
    hsr1.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, DELETE, PATCH, HEAD, OPTIONS");
    hsr1.addHeader("Access-Control-Allow-Credentials", "true");
    hsr1.addHeader("Access-Control-Allow-Headers",
        "Authorization, origin, client_id, access_token, content-type, accept, key, x-requested-with, access-token, content-disposition, token, grant_type, gtype");
    hsr1.addHeader("Access-Control-Expose-Headers",
            "Authorization, origin, client_id, access_token, content-type, accept, key, x-requested-with, access-token, content-disposition, token, grant_type, gtype");
    fc.doFilter(hsr, hsr1);
  }

}