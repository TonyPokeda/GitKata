package web.service;



import web.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User findByUsername(String name);

    List<User> getAllUsers();

    void saveUser(User user, List<Long> roleIds);

    void deleteUser(Long id);

    void updateUser(Long id, User user,List<Long> roleIds);
}
