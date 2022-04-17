package al.ikubinfo.hrmanagement.controller;

import al.ikubinfo.hrmanagement.dto.UserDto;
import al.ikubinfo.hrmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity("User deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
}