//package com.shadow.studentcoursemanagementsystem.controller;
//
//public class c {
//}
package com.shadow.studentcoursemanagementsystem.controller;


import com.shadow.studentcoursemanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {

    @Autowired
    private StudentService studentService;



    // 3. Show the login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 4. Redirect home to login
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}
