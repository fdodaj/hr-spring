package al.ikubinfo.hrmanagement.controller;


import al.ikubinfo.hrmanagement.dto.roledtos.RoleDto;
import al.ikubinfo.hrmanagement.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping()
    public ResponseEntity<List<RoleDto>> getRoles(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(roleService.getRole(pageNo, pageSize, sortBy),
                                    HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.addRole(roleDto));
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<RoleDto> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return  ResponseEntity.ok(roleService.getRoleById(id));
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.updateRole(roleDto));
    }
}


