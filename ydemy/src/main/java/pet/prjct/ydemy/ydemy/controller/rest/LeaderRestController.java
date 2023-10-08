package pet.prjct.ydemy.ydemy.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pet.prjct.ydemy.ydemy.model.entity.User;
import pet.prjct.ydemy.ydemy.service.LeaderService;

@RestController
@RequestMapping("/leaders")
public class LeaderRestController {

    private LeaderService leaderService;

    @Autowired
    public LeaderRestController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllCommand() {
        return leaderService.findAllUsers();
    }
}
