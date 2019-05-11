package us.jordan.week9.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    @RequestMapping("/teams")
    public List<Team> getThanks() {
        return teamService.getAllTeams();
    }

    @RequestMapping("/team/{id}")
    public Team getTeam(@PathVariable String id) {
        return teamService.getTeam(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/teams")
    public List<Team> postTeams() {
        return teamService.getAllTeams();
    }

    // add a general team
    @RequestMapping(method= RequestMethod.GET, value="/teams")
    public List<Team> getTeams() {
        teamService.addTeam(new Team("Packers", "Green Bay"));
        return teamService.getAllTeams();
    }

    // add a specific team
    @RequestMapping(method= RequestMethod.GET, value="/teams/{id}+{id2}")
        public List<Team> getTeams(@PathVariable String id, @PathVariable String id2) {
        teamService.addTeam(new Team(id, id2));
        return teamService.getAllTeams();
    }
}
