
package zw.org.zvandiri.security.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import zw.org.zvandiri.business.domain.UserRole;
import zw.org.zvandiri.security.UserService;


/**
 *
 * @author manatsachinyeruse@gmail.com
 */

@Component
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        //logger.info("Loading user record for user name: {}", userName);
        UserDetails userDetails = null;

        zw.org.zvandiri.business.domain.User user =  userService.findByUserName(userName);
        System.err.println("********************************************** "+user.toString());

        if (user != null) {
            String password = user.getPassword();
            Set<UserRole> roles = user.getUserRoles();
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (UserRole role : roles) {
                String roleName = role.getName();
                authorities.add(new SimpleGrantedAuthority(roleName));
            }
            userDetails = new User(userName, password, authorities);

        } else {
            // If username not found, throw exception

            throw new UsernameNotFoundException("User " + userName + " not found");

        }
        return userDetails;
    }
}