package com.codean.mybatispesantren.config;

import com.codean.mybatispesantren.services.implementations.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*code ini implementasi methode dari doFilterInternal yang ada di dalam class
  OncePerRequestFilter khusus digunakan untuk
  memeriksa, memvalidasi, dan mengauthentication token JWT yang dikirimkan
  dalam header permintaan HTTP
*/

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String jwtToken = null;
        String username = null;

        //menambahkan bearer token skip kata bearer dan get token
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);
            try{
                username = jwtTokenUtil.getUserFromToken(jwtToken);
            } catch (IllegalArgumentException e){
                System.out.println("unable to get jwt token");
            } catch (ExpiredJwtException e){
                System.out.println("JWT token has expired");
            }
        } else {
            logger.warn("JWT token does not begin with Bearer String");
        }

        //validasi token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

            //jika token valid maka set authentication secara manual
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //config user telah ter authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }

        }

        filterChain.doFilter(request, response);
    }
}
