package com.activity.Activity.Services;
import com.activity.Activity.Model.User;
import com.activity.Activity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService implements BaseService<User> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User create(User entity) {
        return userRepository.save(entity);
    }
    @Override
    public User update(Long id, User entity) {
        User user=getById(id);
        if (user!=null){
            user.setName(entity.getName());
            user.setSurname(entity.getSurname());
            userRepository.save(user);
        }
        return null;
    }
    @Override
    public boolean delete(Long id) {
        User user = getById(id);
        if(user!=null){
            userRepository.delete(user);
            return true;
        }
        else{
            return false;
        }
    }
    public User findByEmail(String Email){
        System.out.println("Gelen Email: "+Email);
        User findUser=null;
        for (User users:userRepository.findAll()){
            if(users.getEmail().equals(Email)){
                findUser=users;
                break;
            }
        }
        return findUser;
    }
}
