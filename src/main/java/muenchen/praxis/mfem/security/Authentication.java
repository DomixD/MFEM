package muenchen.praxis.mfem.security;

import muenchen.praxis.mfem.entities.RoleAccess;
import muenchen.praxis.mfem.persistence.RepoRoleAccess;
import muenchen.praxis.mfem.persistence.RepoUser;
import muenchen.praxis.mfem.services.MFEMServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.HashMap;

@Configuration
@EnableWebSecurity
public class Authentication extends WebSecurityConfigurerAdapter {

    @Autowired
    private RepoUser repoUser;
    @Autowired
    private MFEMServiceImpl custom;

    @Autowired
    RepoRoleAccess repoRoleAccess;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    private static HashMap<String, RoleAccess> roleMapping = new HashMap<>();
    private static int userID;

    public void init(AuthenticationManagerBuilder auth) throws Exception {
        // use our CustomUserDetailsService to authentificate user
        //auth.userDetailsService(custom).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/quest/**", "/answer/**", "/cat/**", "/classi/**" ,"/metric/**", "/req/**", "/user/**","/roles/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/**", "/roles/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        // initialize the role permission mapping
        repoRoleAccess.findAll().forEach(role -> {
            roleMapping.put(role.getRole(), role);
        });
    }

    public static boolean hasPermission(AccessType accessType) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getAuthorities().stream().anyMatch(auth -> {
            RoleAccess role = roleMapping.get(auth.getAuthority());
            switch (accessType) {
                case READ_ACCESS:
                    return role.isReadAccess();
                case CREATE_CLASSI:
                    return role.isCreateClassi();
                case CREATE_REQ:
                    return role.isCreateReq();
                case CREATE_QUEST:
                    return role.isCreateQuest();
                case CREATE_METRIC:
                    return role.isCreateMetric();
                case CREATE_CAT:
                    return role.isCreateCategory();
                case EVALUATION:
                    return role.isEvaluation();
                case ADMIN_ACCESS:
                    return role.isAdminAccess();
            }
            return false;
        });
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userId) {
        userID = userId;
    }
}
