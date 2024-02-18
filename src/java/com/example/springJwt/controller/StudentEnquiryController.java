package com.example.springJwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springJwt.model.EnquiryUpdateResponse;
import com.example.springJwt.model.StatusEnum;
import com.example.springJwt.model.Students;
import com.example.springJwt.service.StudentEnquiryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enquiries")
public class StudentEnquiryController {

    @Autowired
    private StudentEnquiryService enquiryService;

    @GetMapping("/getStudents")
    public List<Students> getAllEnquiries() {
        return enquiryService.getAllEnquiries();
    }

//    @PostMapping("/createEnquiries")
//    public Students createEnquiry(@RequestBody Students enquiry) {
//        return enquiryService.createEnquiry(enquiry);
//    }
    @PostMapping("/createEnquiries")
    public ResponseEntity<Object> createEnquiry(@RequestBody Students enquiry) {
        try {
            Students createdEnquiry = enquiryService.createEnquiry(enquiry);
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Enquiry created successfully");
            response.put("data", createdEnquiry);
            // Return the response with HTTP status 200 OK
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            // Return the error message with HTTP status 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Object> getEnquiryById(@PathVariable Long id) {
        try {
            Students enquiry = enquiryService.getEnquiryById(id);
            return ResponseEntity.ok().body(enquiry);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Object> updateEnquiry(@PathVariable Long id, @RequestBody Students updatedEnquiry) {
        try {
            Students updatedStudent = enquiryService.updateEnquiry(id, updatedEnquiry);
            
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Enquiry updated successfully");
            response.put("data", updatedStudent);
            
            // Return the response with HTTP status 200 OK
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            // Return the error message with HTTP status 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/updatedeleteStudent/{id}")
    public ResponseEntity<Object> updateDeletedEnquiry(@PathVariable Long id) {
        try {
            EnquiryUpdateResponse response = enquiryService.updateDeletedEnquiry(id);
            return ResponseEntity.ok().body(response.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Object> deleteEnquiry(@PathVariable Long id) {
        try {
            String message = enquiryService.deleteEnquiry(id);
            return ResponseEntity.ok().body(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Students>> getStudentsByStatus(@PathVariable("status") String status) {
        StatusEnum statusEnum = StatusEnum.valueOf(status.toUpperCase());
        List<Students> students = enquiryService.getStudentsByStatus(statusEnum);
        return new ResponseEntity<>(students, HttpStatus.OK);
    } 
    
    // Endpoint to update status of a student inquiry
    @PutMapping("/status/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable("id") Long id, @RequestBody StatusEnum status) {
        try {
            Students updatedStudent = enquiryService.updateStatus(id, status);

            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Enquiry status updated successfully");
            response.put("data", updatedStudent);

            // Return the response with HTTP status 200 OK
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            // Return the error message with HTTP status 404 Not Found
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
}
}


