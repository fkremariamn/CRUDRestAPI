package com.employeeCRUD.empCRUD.Controller;

import com.employeeCRUD.empCRUD.Dto.ApiResponse;
import com.employeeCRUD.empCRUD.Entity.Job;
import com.employeeCRUD.empCRUD.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Job> list= jobService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("success", list));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable int id) {
        Job job = jobService.findById(id);
        if (job != null) {
            return ResponseEntity.ok(new ApiResponse<>("success", job));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(@PathVariable int id) {
        jobService.deleteById(id);
        return ResponseEntity.status(204).body(new ApiResponse<>("Job deleted", null));

    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Job>> updateJobs(@PathVariable int id, @RequestBody Job Job) {
        Job proj = jobService.findById(id);
        if (proj != null) {
            Job.setId(id);
            Job updated = jobService.update(Job);

            return ResponseEntity.ok(
                    new ApiResponse<>("Job updated successfully", updated)
            );
        }
        else  {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Job>> createJob(
            @RequestBody Job job) {

        Job saved = jobService.save(job);

        return ResponseEntity.status(201)
                .body(new ApiResponse<>("Job created", saved));
    }
}
