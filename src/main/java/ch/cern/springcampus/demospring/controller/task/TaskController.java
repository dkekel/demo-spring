package ch.cern.springcampus.demospring.controller.task;

import ch.cern.springcampus.demospring.bean.Task;
import ch.cern.springcampus.demospring.bean.User;
import ch.cern.springcampus.demospring.dto.TaskForm;
import ch.cern.springcampus.demospring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        long incompleteCount = taskService.countIncompleteTasks(taskList);
        modelAndView.addObject("taskList", taskList);
        modelAndView.addObject("hideCompleted", false);
        modelAndView.addObject("incompleteCount", incompleteCount);
        return modelAndView;
    }

    @PostMapping("/list")
    public ModelAndView filteredList(final @RequestParam(defaultValue = "false") boolean hideCompleted) {
        ModelAndView modelAndView = new ModelAndView("tasks/list");
        List<Task> taskList = hideCompleted ? taskService.findAllTasksNotCompleted() : taskService.findAllTasks();
        long incompleteCount = taskService.countIncompleteTasks(taskList);
        modelAndView.addObject("taskList", taskList);
        modelAndView.addObject("hideCompleted", hideCompleted);
        modelAndView.addObject("incompleteCount", incompleteCount);
        return modelAndView;
    }

    @PostMapping("/add")
    public String addTask(final @ModelAttribute TaskForm taskForm, final @AuthenticationPrincipal User currentUser) {
        taskService.addNewTask(taskForm.getText(), currentUser);
        return "redirect:/task/list";
    }

    @PostMapping("/update")
    public String updateTask(final @ModelAttribute TaskForm taskForm) {
        String taskId = taskForm.getId();
        boolean taskDone = taskForm.isDone();
        taskService.updateTask(taskId, taskDone);
        return "redirect:/task/list";
    }

    @PostMapping("/delete")
    public String deleteTask(final @ModelAttribute TaskForm taskForm) {
        String taskId = taskForm.getId();
        taskService.deleteTask(taskId);
        return "redirect:/task/list";
    }
}
