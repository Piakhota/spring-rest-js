package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //На этом костыле держится половина JS-а. Если знать это заранее, то со старта написал бы без него.
    //Переделывать не вытяну в моральном плане, уже тошнит от JS и всего, что с ним связано:
    //на эту задачу убил полтора месяца, при этом core и pp пролетел меньше чем за три.
    //Если фронт не так важен для джависта, просьба закрыть глаза на этот метод.

    //PS: в index.html добавил закомментированный код получения юзера с помощью таймлифа -
    // его бы сейчас использовал, если решать задачу с нуля.
    @GetMapping
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return ResponseEntity.ok().body((User) userService.loadUserByUsername(principal.getName()));
    }
}
