package web.dao;

import web.model.User;


import java.util.List;

public interface UserDao {

   public List<web.model.User> getAllUsers();

   public web.model.User getUserById(int id);

   public void save(web.model.User user);

   public void update(web.model.User user);

   public void delete(int id);


}
