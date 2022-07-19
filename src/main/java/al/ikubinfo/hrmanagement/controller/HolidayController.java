package al.ikubinfo.hrmanagement.controller;

import al.ikubinfo.hrmanagement.dto.HolidayDto;
import al.ikubinfo.hrmanagement.services.HolidayService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping(path = "/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;


    @GetMapping("/all")
    public ResponseEntity<List<HolidayDto>> getHoliday() {
        return ResponseEntity.ok(holidayService.getHoliday());
    }


    @PostMapping("/add")
    public ResponseEntity<HolidayDto> addHoliday(@RequestBody HolidayDto holidayDto) {
        return ResponseEntity.ok(holidayService.addHoliday(holidayDto));
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<HolidayDto> deleteHoliday(@PathVariable("id") Long id) {
        holidayService.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<HolidayDto> updateHoliday(@RequestBody HolidayDto holidayDto) {
        return ResponseEntity.ok(holidayService.updateHoliday(holidayDto));
    }
}