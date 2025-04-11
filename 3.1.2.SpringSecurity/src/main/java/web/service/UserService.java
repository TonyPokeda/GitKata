package web.service;



import web.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User findByUsername(String name);

    List<User> getAllUsers();

    User saveUser(User user);

    void deleteUser(Long id);

    void updateUser(Long id, User user,List<Long> roleIds);
}
