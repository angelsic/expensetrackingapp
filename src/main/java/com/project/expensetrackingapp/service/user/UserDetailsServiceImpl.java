package com.project.expensetrackingapp.service.user;

import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Manage User Detail Service Implementation
 * @author Angel Sic
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDatabaseStrategy userDatabaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("userStrategy") UserDatabaseStrategy userDatabaseStrategy){
        this.userDatabaseStrategy = userDatabaseStrategy;
    }
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Inside loadUserByUsername");
        User user = userDatabaseStrategy.findByUsername(username);
        if(user == null){
            logger.error("Username not found: " + username);
            throw new UsernameNotFound();
        }
        logger.info("User Authenticated Successfully");
        return new CustomUserDetail(user);
    }
}
