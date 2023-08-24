package pet.prjct.ydemy.ydemy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pet.prjct.ydemy.ydemy.service.LeaderService;

@Controller
@RequestMapping("/leaders")
public class LeaderController {

    private LeaderService leaderService;

    @Autowired
    public LeaderController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }

    @GetMapping("/")
    public String getManagerPage(Model theModel) {
        return "vip/managers";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String username,
                         Model theModel) {

        String result = "User '" + username + "' was not found.";
        if (leaderService.findByUsername(username).isPresent()) {
            result = leaderService.findByUsername(username).get().toString();
        }

        theModel.addAttribute("user", result);

        return "vip/managers";
    }
}
