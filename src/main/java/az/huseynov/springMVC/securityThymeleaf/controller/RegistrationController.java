package az.huseynov.springMVC.securityThymeleaf.controller;

import az.huseynov.springMVC.securityThymeleaf.enitity.User;
import az.huseynov.springMVC.securityThymeleaf.model.WebUser;
import az.huseynov.springMVC.securityThymeleaf.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller()
public class RegistrationController {

    private Logger logger = Logger.getLogger(getClass().getName());
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("webUser", new WebUser());
        return "register";
    }

    @PostMapping("/register/processRegistration")
    public String processRegistration( @Valid @ModelAttribute("webUser") WebUser theWebUser,
                                      BindingResult bindingResult,
                                      HttpSession session, Model theModel) {

        String username = theWebUser.getUsername();
        logger.info("Processing registration form for: " + username);

        // form validation
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult + "\n\n\n");
            return "register";
        }

        // check the Database if username already exists
        User checkExistsUser = userService.findByUserName(username);
        theModel.addAttribute("theWebUser",theWebUser);
        if (checkExistsUser != null) {
            theModel.addAttribute("registrationErrorExists", "'"+username+"' already exists.");
            logger.warning("'"+username+"' already exists.");
            return "register";
        }

        // create user and store in the Database
        userService.save(theWebUser);
        logger.info("Successfully user create: "+username);

        // place user in the web http session for later use
        session.setAttribute("user",theWebUser);
        return "register-confirmation";
    }


}