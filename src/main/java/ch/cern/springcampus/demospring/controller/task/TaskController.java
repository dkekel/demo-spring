package ch.cern.springcampus.demospring.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("tasks/list");
        List<String> taskList =
                Arrays.asList("Set-up the Environment", "Create a project", "Define web-pages templates");
        modelAndView.addObject("taskList", taskList);
        return modelAndView;
    }
}
