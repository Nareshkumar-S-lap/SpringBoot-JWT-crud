package com.example.springJwt.service;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.springJwt.model.EnquiryUpdateResponse;
import com.example.springJwt.model.StatusEnum;
import com.example.springJwt.model.Students;
import com.example.springJwt.repository.StudentEnquiryRepository;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StudentEnquiryService {

	@Autowired
	private StudentEnquiryRepository enquiryRepository;

	public List<Students> getAllEnquiries() {
		List<Students> allStudents = enquiryRepository.findAll();
		return allStudents.stream().filter(student -> !student.isDeleted()) // Filter out deleted students
				.collect(Collectors.toList());
	}

	// Regular expression for validating email format
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

	public Students createEnquiry(Students enquiry) {
		if (enquiry.getEmail() == null || enquiry.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty");
		}
		if (!isValidEmail(enquiry.getEmail())) {
			throw new IllegalArgumentException("Invalid email format: " + enquiry.getEmail());
		}

		// Check if name already exists
		if (enquiryRepository.existsByName(enquiry.getName())) {
			throw new IllegalArgumentException("Name already exists: " + enquiry.getName());
		}

		// Check if email already exists
		if (enquiryRepository.existsByEmail(enquiry.getEmail())) {
			throw new IllegalArgumentException("Email already exists: " + enquiry.getEmail());
		}

		return enquiryRepository.save(enquiry);
	}

	private static boolean isValidEmail(String email) {
		return pattern.matcher(email).matches();
	}

	public Students getEnquiryById(Long id) {
		return enquiryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + id));
	}

	 public Students updateEnquiry(Long id, Students updatedEnquiry) {
	        // Validate the updated enquiry data before updating
	        validateEnquiryData(updatedEnquiry);

	        Students existingEnquiry = getEnquiryById(id);

	        // Update only if the enquiry is not deleted
	        if (!existingEnquiry.isDeleted()) {
	            existingEnquiry.setName(updatedEnquiry.getName());
	            existingEnquiry.setEmail(updatedEnquiry.getEmail());
	            existingEnquiry.setPhone(updatedEnquiry.getPhone());
	            existingEnquiry.setMessage(updatedEnquiry.getMessage());
	            existingEnquiry.setStatus(updatedEnquiry.getStatus());
	            return enquiryRepository.save(existingEnquiry);
	        } else {
	            throw new RuntimeException("Unable to update enquiry with id " + id + " as it is already deleted");
	        }
	    }

	    private void validateEnquiryData(Students enquiry) {
	        if (!StringUtils.hasText(enquiry.getName())) {
	            throw new IllegalArgumentException("Name cannot be null or empty");
	        }
	        if (!StringUtils.hasText(enquiry.getEmail())) {
	            throw new IllegalArgumentException("Email cannot be null or empty");
	        }
	        if (!StringUtils.hasText(enquiry.getPhone())) {
	            throw new IllegalArgumentException("Phone cannot be null or empty");
	        }
	        // You can add more validation rules as needed
	    }

	public String deleteEnquiry(Long id) {
		Students existingEnquiry = getEnquiryById(id);
		if (!existingEnquiry.isDeleted()) {
			existingEnquiry.setDeleted(true);
			enquiryRepository.save(existingEnquiry);
			return "Enquiry with ID " + id + " has been soft deleted successfully";
		} else {
			throw new RuntimeException("Enquiry with id " + id + " is already deleted");
		}
	}

	private boolean isValidEnquiry(Students enquiry) {
		return !StringUtils.isEmpty(enquiry.getName()) && !StringUtils.isEmpty(enquiry.getEmail())
				&& !StringUtils.isEmpty(enquiry.getPhone());
	}

	public EnquiryUpdateResponse updateDeletedEnquiry(Long id) {
		Students existingEnquiry = getEnquiryById(id);

		// Update only if the enquiry is deleted
		if (existingEnquiry.isDeleted()) {
			existingEnquiry.setDeleted(false); // Set student as active
		} else {
			return new EnquiryUpdateResponse("Enquiry with ID " + id + " is not deleted", null);
		}

		Students updatedEnquiry = enquiryRepository.save(existingEnquiry);
		return new EnquiryUpdateResponse("Enquiry with ID " + id + " has been successfully updated", updatedEnquiry);
	}

	public List<Students> getStudentsByStatus(StatusEnum status) {
		return enquiryRepository.findByStatus(status);
	}

	public Students updateStatus(Long id, StatusEnum status) {
		Students student = enquiryRepository.findById(id).orElse(null);
		if (student != null) {
			student.setStatus(status);
			return enquiryRepository.save(student);
		} else {
			// Handle case where student with the given ID is not found
			throw new RuntimeException("Student not found with ID: " + id);
		}
	}

}
