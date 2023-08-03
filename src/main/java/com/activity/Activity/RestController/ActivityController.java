package com.activity.Activity.RestController;
import com.activity.Activity.Model.Activity;
import com.activity.Activity.Services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @GetMapping("")
    public List<Activity> getAllActivity(){return activityService.getAll();}
    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id){return activityService.getById(id);}
    @PostMapping("/add")
    public Activity createActivity(@RequestBody Activity entity){return activityService.create(entity);}
    @PostMapping("/delete/{id}")
    public Boolean deleteActivity(@PathVariable Long id){return activityService.delete(id);}
    @PutMapping("/update/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity entity) {
        return activityService.update(id, entity);
    }
    @PostMapping("/{activityId}/students/{studentId}")
    public Activity addStudentToActivity(@PathVariable Long activityId, @PathVariable Long studentId) {
        return activityService.addStudentAct(studentId, activityId);
    }
    @GetMapping("students/{id}")
    public void getStudentsAct(@PathVariable Long id){ activityService.printStudents(id);}
}
