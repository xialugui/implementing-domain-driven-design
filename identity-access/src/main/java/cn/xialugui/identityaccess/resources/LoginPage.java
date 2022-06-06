package cn.xialugui.identityaccess.resources;

import cn.xialugui.identityaccess.application.permission.CreatePermissionCommand;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginPage {
    @GetMapping("/login")
    String login() {
        return "redirect:http://localhost:24006/login.html";
    }
}
