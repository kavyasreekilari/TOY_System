package com.toyproject.springbootbackend.controller;

import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.service.ChildService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("child")
public class ChildController {

//    private DSLContext dslContext;

    @Autowired
    private ChildService childService;

    @GetMapping("/child_management")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        Child child = new Child();
//        System.out.println("Child Management is called");
        model.addAttribute("child", child);
        return "child_management";
    }

    // get all children
    @GetMapping("/children")
    public String children(Model model) {
        List<Child> children = childService.getAllChildren();
        model.addAttribute("children", children);
        return "children";
    }

    @GetMapping("/child_registration")
    public String register(Model model) {
        Child child = new Child();
        model.addAttribute("child", child);
        return "child_registration";
    }

    // handler method to handle child registration form submit request
    @PostMapping("/child_register/save")
    public String registration(@ModelAttribute("child") Child child,
                               BindingResult result,
                               Model model){

        Child existingChild = childService.findChildByEmail(child.getEmail());

        if(existingChild != null && existingChild.getEmail() != null && !existingChild.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if(existingChild != null && (existingChild.getAge() <5 ||  existingChild.getAge()>10)){
            result.rejectValue("age",null,"The child has to be 5 to 10 years old");
        }

        if(result.hasErrors()){
            model.addAttribute("child", child);
            return "/child_registration";
        }
        childService.saveChild(child);
        System.out.println("Registration succesful for Child: "+child.toString());
        return "redirect:/child_registration?success";
    }

    // handler method to handle login request
    @GetMapping("/child_login")
    public String login(){
        return "child_login";
    }

    @PostMapping("/child_login_success")
    public Child savelogin(@ModelAttribute("child") Child child,
                           BindingResult result,
                           Model model) {
        Child existingChild = childService.findChildByEmail(child.getEmail());
        return existingChild;
    }

    @PostMapping("/child_login/save")
    public String login(@ModelAttribute("child") Child child,
                        BindingResult result,
                        Model model){
        return "redirect:/children";
    }

    @GetMapping("/children/edit/{id}")
    public Child getchildById(Integer id) {
        return childService.getChildById(id);
    }

    @PostMapping("/children/{id}")
    public String updatechild(@PathVariable Integer id, Child child) {

        Child existingChild = childService.getChildById(id);
        existingChild.setID(id);
        existingChild.setFirstName(child.getFirstName());
        existingChild.setLastName(child.getLastName());
        existingChild.setEmail(child.getEmail());

        // save updated child object
        childService.updateChild(existingChild);
        return "redirect:/children";
    }

    @GetMapping("/children/{id}")
    public String deletechildById(Integer id) {
        childService.deleteChildById(id);
        return "redirect:/children";
    }

    public List childrenNames (ChildService child) {
        List<Child> children = child.getAllChildren();
        List childNames = children.stream()
                .filter(cust->cust.getFirstName()!=null && cust.getLastName()!=null)
                .map(cust->String.format("%s%s",cust.getFirstName(),cust.getLastName()))
                .collect(Collectors.toList());
//        childNames.forEach(childName -> System.out.println(childName));
        return childNames;
    }

}
