package sg.nus.iss.leaveapp.leave.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.leaveapp.leave.model.PublicHoliday;
import sg.nus.iss.leaveapp.leave.service.PublicHolidayService;

@RestController
@RequestMapping("/api/publichol")
public class PublicHolidayController {
    @Autowired
    private PublicHolidayService publicHolService;

    @GetMapping
    public ResponseEntity<List<PublicHoliday>> findAllPublicHol(){
        try {
            List<PublicHoliday> publicHol = new ArrayList<PublicHoliday>();
            publicHol = publicHolService.getPublicHolList();

            if (publicHol.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(publicHol, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicHoliday> findPublicHolById(@PathVariable(value="id") long id){
        Optional<PublicHoliday> publicHol = publicHolService.getPublicHolById(id);
        if(publicHol.isPresent()){
            return ResponseEntity.ok().body(publicHol.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<PublicHoliday> addPublicHol(@RequestBody PublicHoliday publicHol){
        boolean overlap = publicHolService.checkIfOverlap(publicHol);
        if (!overlap){
            publicHolService.savePublicHoliday(publicHol);
            return new ResponseEntity<>(publicHol,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<PublicHoliday> updatePublicHol(@RequestBody PublicHoliday publicHol){
        Optional<PublicHoliday> oldpublicHol = publicHolService.getPublicHolById(publicHol.getId());
        boolean success = publicHolService.updatePublicHoliday(oldpublicHol.get(), publicHol);
        if (success){
            return new ResponseEntity<>(publicHol,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

    @PostMapping("/delete")
    public ResponseEntity<PublicHoliday> deletePublicHol(@RequestBody PublicHoliday publicHol){
        boolean success = publicHolService.deletePublicHoliday(publicHol);
        if (success){
            return new ResponseEntity<>(publicHol,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

    
    
}
