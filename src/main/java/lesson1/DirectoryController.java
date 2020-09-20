package lesson1;

import lesson1.domain.User;
import lesson1.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DirectoryController {
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<User> users = usersRepo.findAll();

        model.put("users", users);

        return "main";
    }

    @PostMapping("addUser")
    public String add(@RequestParam String firstName,@RequestParam String lastName,@RequestParam Integer age,@RequestParam double money,@RequestParam Integer phoneNumber,@RequestParam String eMail,
                      Map<String, Object> model) {
        User user = new User(firstName,lastName,age,money,phoneNumber,eMail);

        usersRepo.save(user);

        Iterable<User> users = usersRepo.findAll();

        model.put("users", users);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<User> users;

        if (filter != null && !filter.isEmpty()) {
            users = usersRepo.findByFirstName(filter);
        } else {
            users = usersRepo.findAll();
        }

        model.put("users", users);

        return "main";
    }
}
