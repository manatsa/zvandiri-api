package zw.org.zvandiri.security.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.repo.UserRepo;
import zw.org.zvandiri.security.UserService;

import javax.annotation.Resource;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepo userRepo;




    @Override
    public User findByUserName(String name) {
        return userRepo.findByUserName(name).get();
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        User user = findByUserName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof String) {
            String principal = (String) authentication.getPrincipal();
            if (principal.compareTo("anonymousUser") != 0) {
                return null;
            }
            return principal;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }


}
