package com.activity.Activity.Services;

import com.activity.Activity.Model.Activity;
import com.activity.Activity.Model.User;
import com.activity.Activity.Repository.ActivityRepository;
import com.activity.Activity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ActivityService implements BaseService<Activity>{
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public Activity create(Activity entity) {
        return activityRepository.save(entity);
    }

    @Override
    public Activity update(Long id, Activity entity) {
        Activity activity=getById(id);
        if(activity!=null){
            activity.setName(entity.getName());
            activity.setDescription(entity.getDescription());
            activity.setAssignedStudents(entity.getAssignedStudents());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Activity activity=getById(id);
        if(activity!=null){
            activityRepository.delete(activity);
            return true;
        }
        else{
            return false;
        }
    }

    public Activity addStudentAct(Long studentid,Long activityid){
        List<User> userList=new ArrayList<>();

        User user=userRepository.getById(studentid);
        Activity activity=activityRepository.getById(activityid);

        userList=activity.getAssignedStudents();
        userList.add(user);
        activity.setAssignedStudents(userList);

        activityRepository.save(activity);
        return activity;
    }

    public void printStudents(Long Id) {
        Activity activity=activityRepository.getById(Id);
        List<User> userList=activity.getAssignedStudents();
        System.out.println("Aktivite adı: "+ activity.getName());
        System.out.println("Aktivite tanımı: "+ activity.getDescription());
        for (User user : userList) {
            System.out.println("---------------------------");
            System.out.println("Öğrenci ID: " + user.getUserId());
            System.out.println("Öğrenci Adı - Soyadı: " + user.getName() + user.getSurname());
            System.out.println("---------------------------");
        }
    }
}
