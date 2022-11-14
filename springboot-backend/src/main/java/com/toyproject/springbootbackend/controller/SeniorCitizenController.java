package com.toyproject.springbootbackend.controller;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.service.ChildService;
import com.toyproject.springbootbackend.service.SeniorCitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SeniorCitizenController {

    @Autowired
    private SeniorCitizenService seniorCitizenService;

    @GetMapping("/senior_management")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        SeniorCitizen senior = new SeniorCitizen();
        model.addAttribute("senior", senior);
        return "senior_management";
    }

    @GetMapping("/senior_registration")
    public String register(Model model) {
        SeniorCitizen senior = new SeniorCitizen();
        model.addAttribute("senior", senior);
        return "senior_registration";
    }

    // handler method to handle child registration form submit request
    @PostMapping("/senior_register/save")
    public String registration(@ModelAttribute("senior") SeniorCitizen senior,
                               BindingResult result,
                               Model model){
        SeniorCitizen existingSenior = seniorCitizenService.findSeniorByEmail(senior.getEmail());
        SeniorCitizen seniorAge = seniorCitizenService.findSeniorByAge(senior.getAge());

        if(existingSenior != null && existingSenior.getEmail() != null && !existingSenior.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if(seniorAge != null && (existingSenior.getAge() <60 ||  existingSenior.getAge()>90)){
            result.rejectValue("age",null,"The senior has to be 60 to 90 years old");
        }

        if(result.hasErrors()){
            model.addAttribute("senior", senior);
            return "/senior_registration";
        }

        // calls crimecalculator to calculate safety rank
        crimeCalculator(senior);

        seniorCitizenService.saveSeniorCitizen(senior);
        System.out.println("Registration succesful for Senior: "+senior.toString());
        return "redirect:/senior_registration?success";
    }

    @PostMapping("/senior_login/save")
    public String login(@ModelAttribute("senior") SeniorCitizen senior,
                        BindingResult result,
                        Model model){

       SeniorCitizen existingSenior = seniorCitizenService.findSeniorByEmail(senior.getEmail());

//        if(existingSenior != null && existingSenior.getEmail() != null && !existingSenior.getEmail().isEmpty()){
//           return "seniorCitizens";
//        }
//        if(existingSenior == null){
//            result.rejectValue("email", null,
//                    "There is no account registered with the email");
//        }
//        if(existingSenior != null && existingSenior.getPassword() != senior.getPassword()){
//            result.rejectValue("password", "Password is incorrect");
//        }
//        if(result.hasErrors()){
//            model.addAttribute("senior", senior);
//            return "/senior_login";
//        }

        return "redirect:/seniorCitizens";
    }


    // handler method to handle login request
    @GetMapping("/senior_login")
    public String login(){
        return "senior_login";
    }

    // get all SeniorCitizens
    @GetMapping("/seniorCitizens")
    public String getAllSeniorCitizens(Model model) {
        List<SeniorCitizen> seniorCitizens = seniorCitizenService.getAllSeniorCitizens();
        model.addAttribute("seniorCitizens", seniorCitizens);
        return "seniorCitizens";
    }

    // add a new senior citizen
    @PostMapping("/seniorRegistered")
    public String addSeniorCitizen(SeniorCitizen seniorCitizen) {
        seniorCitizenService.saveSeniorCitizen(seniorCitizen);
        return "senior_register_success";
    }

    // get a senior citizen by id
    @GetMapping("/findSenior")
    public SeniorCitizen getSeniorCitizenById(Integer id) {
        return seniorCitizenService.getSeniorCitizenById(id);
    }

    // update an existing senior citizen
    @PostMapping("updateSenior")
    public SeniorCitizen updateSeniorCitizen(SeniorCitizen SeniorCitizen) {
        return seniorCitizenService.saveSeniorCitizen(SeniorCitizen);
    }

    // delete an existing senior citizen

    public void deleteSeniorCitizenById(Integer id) {
        seniorCitizenService.deleteSeniorCitizenById(id);
    }

    public List seniorCitizenNames (SeniorCitizenService senior) {
        List<SeniorCitizen> seniorCitizens = senior.getAllSeniorCitizens();
        List seniorNames = seniorCitizens.stream()
                .filter(cust->cust.getFirstName()!=null && cust.getLastName()!=null)
                .map(cust->String.format("%s%s",cust.getFirstName(),cust.getLastName()))
                .collect(Collectors.toList());
//        seniorNames.forEach(seniorName -> System.out.println(seniorName));
        return seniorNames;
    }


    public int crimeCalculator (SeniorCitizen seniorCitizen) {
        int level = 1;
            if(seniorCitizen.getEspionage().booleanValue()){
                level=level+5;
            }
            if(seniorCitizen.getFelony().booleanValue()){
                level=level+4;
            }
            if(seniorCitizen.getSoliciting().booleanValue()){
                level=level+3;
            }
            if(seniorCitizen.getMisdemeanor().booleanValue()){
                level=level+2;
            }
            if(seniorCitizen.getOffence().booleanValue()){
                level=level+1;
            }

            seniorCitizen.setSafetyLevel(level);

        return level;
    }
}

