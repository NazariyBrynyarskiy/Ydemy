package pet.prjct.ydemy.ydemy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pet.prjct.ydemy.ydemy.service.SystemService;

@Controller
@RequestMapping("/systems")
public class SystemController {

    private SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/")
    public String getAdminPage() {
        return "vip/admins";
    }


    @PostMapping("/update-user-role")
    public String updateUserRole(@RequestParam("authority") String authority,
                                 @RequestParam("username") String username,
                                 Model theModel) {

        String message = systemService.updateUserRole(authority, username);
        theModel.addAttribute("message", message);

        return "vip/admins";
    }


}
