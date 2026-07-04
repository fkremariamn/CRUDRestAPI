package com.employeeCRUD.empCRUD.Controller;

import com.employeeCRUD.empCRUD.Dto.ApiResponse;
import com.employeeCRUD.empCRUD.Entity.Job;
import com.employeeCRUD.empCRUD.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs() {
        List<Job> list = jobService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable int id) {
        Job job = jobService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>("Success", job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(@PathVariable int id) {
        jobService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>("Job deleted successfully", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> updateJobs(@PathVariable int id, @RequestBody Job job) {
        job.setId(id);
        Job updated = jobService.update(job);
        return ResponseEntity.ok(new ApiResponse<>("Job updated successfully", updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> patchJob(@PathVariable int id, @RequestBody Job job) {
        Job updated = jobService.patchJob(id, job);
        return ResponseEntity.ok(new ApiResponse<>("Job patched successfully", updated));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Job>> createJob(@RequestBody Job job) {
        Job saved = jobService.save(job);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Job created successfully", saved));
    }
}