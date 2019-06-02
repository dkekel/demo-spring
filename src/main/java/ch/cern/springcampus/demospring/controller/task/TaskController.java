package ch.cern.springcampus.demospring.controller.task;

import ch.cern.springcampus.demospring.bean.Task;
import ch.cern.springcampus.demospring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/task")
public class TaskController {

    private transient final TaskService taskService;

    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = Objects.requireNonNull(taskService);
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("tasks/list");
        List<Task> taskList = taskService.findAllTasks();
        modelAndView.addObject("taskList", taskList);
        return modelAndView;
    }
}
