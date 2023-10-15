package com.cstore.domain.user.signup;

import com.cstore.dao.user.UserDao;
import com.cstore.dao.user.address.UserAddressDao;
import com.cstore.dao.user.contact.UserContactDao;
import com.cstore.model.user.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {
    /*private final PasswordEncoder passwordEncoder;
    private final UserAddressDao userAddressDao;
    private final UserContactDao userContactDao;
    private final UserDao userDao;

    public RegisteredUser registerUser(RegistrationRequest registrationRequest) {
        RegisteredUser registeredUser = new RegisteredUser();
        User user = new User();

        user.setRole(Role.GUEST_CUST);
        user = userDao.saveUser(user);

        registeredUser.setUserId(user.getUserId());
        registeredUser.setFirstName(registrationRequest.getFirstName());
        registeredUser.setLastName(registrationRequest.getLastName());
        registeredUser.setEmail(registrationRequest.getEmail());
        registeredUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));


        registeredUser = userDao.saveRegisteredUser(registeredUser);

        for (UserAddress userAddress : registrationRequest.getAddresses()) {
            userAddress.setUser(user);
            userAddressDao.save(userAddress);
        }

        for (Integer telephoneNumber : registrationRequest.getTelephoneNumbers()) {
            UserContactId userContactId = new UserContactId();

            userContactId.setUserId(user.getUserId());
            userContactId.setTelephoneNumber(telephoneNumber);

            userContactDao.save(new UserContact(userContactId, user));
        }

        return registeredUser;
    }*/
}
