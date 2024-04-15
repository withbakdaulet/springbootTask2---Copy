package kz.bitlab.techorda.springbootTask2.controller;

import kz.bitlab.techorda.springbootTask2.entities.ApplicationReq;
import kz.bitlab.techorda.springbootTask2.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/app")
public class HomeController {
    @Autowired
    AppRepository appRepository;

    @GetMapping(value = "/")
    public String getIndex(Model modal){
        List<ApplicationReq> applicationRequestList = appRepository.findAll();
        modal.addAttribute("applicationRequestList", applicationRequestList);
        return "index";
    }
    @GetMapping(value = "/add_Req")
    public String addRequest(){
        return "addReq";
    }
    @PostMapping(value = "/add_Req")
    public String addRequest(@RequestParam(name="user_name") String name,
                             @RequestParam(name="course") String course,
                             @RequestParam(name="phone") String phone,
                             @RequestParam(name="comment") String comment){
        ApplicationReq app = new ApplicationReq();
        app.setHandled(false);
        app.setUserName(name);
        app.setCourseName(course);
        app.setPhone(phone);
        app.setCommentary(comment);

        appRepository.save(app);

        return "redirect:/app/";
    }
    @GetMapping(value = "/details/{id}")
    public String detailsRequest(@PathVariable(name="id") Long id,
                                 Model model){
        ApplicationReq app = appRepository.findById(id).orElseThrow(null);
        model.addAttribute("app", app);
        List<ApplicationReq> applicationRequestList = appRepository.findAll();
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "details";
    }
    @PostMapping(value = "/update_app")
    public String updateApp(@RequestParam(name="user_name") String name,
                            @RequestParam(name="course") String course,
                            @RequestParam(name="phone") String phone,
                            @RequestParam(name="comment") String comment,
                            @RequestParam(name = "id") Long id){
        ApplicationReq app = new ApplicationReq();
        app.setId(id);
        app.setUserName(name);
        app.setCourseName(course);
        app.setPhone(phone);
        app.setCommentary(comment);
        app.setHandled(true);
        appRepository.save(app);

        return "redirect:/app/";
    }
    @PostMapping(value="/delete_app")
    public String deleteApp(@RequestParam(name = "app_id") Long id){
        appRepository.deleteById(id);
        return "redirect:/app/";
    }
    @GetMapping(value = "/new_app")
    public String newApp(Model model){
        List<ApplicationReq> applicationRequestList = appRepository.findAll();
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "newApplication";
    }
    @GetMapping(value = "/handle_app")
    public String handleApp(Model model){
        List<ApplicationReq> applicationRequestList = appRepository.findAll();
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "handleApplication";
    }
}