package org.eugenarium.pma.persistence.service;

import org.eugenarium.pma.persistence.domain.User;

public interface UserService {

    User saveUser(User newUser);
}
