package com.employeeCRUD.empCRUD.Service;

import com.employeeCRUD.empCRUD.DAO.JobDAOImpl;
import com.employeeCRUD.empCRUD.Entity.Job;
import com.employeeCRUD.empCRUD.Exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    JobDAOImpl jobImp;

    @Autowired
    public JobService(JobDAOImpl jobImp) {
        this.jobImp = jobImp;
    }

    public List<Job> findAll() {
        return jobImp.findAll();
    }

    public Job findById(int id) {

        Job job = jobImp.findById(id);

        if (job == null) {
            throw new ResourceNotFoundException(
                    "Job with ID " + id + " not found");
        }

        return job;
    }

    @Transactional
    public Job save(Job job) {
        return jobImp.save(job);
    }

    @Transactional
    public void deleteById(int id) {
        jobImp.deleteById(id);
    }

    @Transactional
    public Job update(Job job) {
        return jobImp.update(job);
    }

    @Transactional
    public Job patchJob(int id, Job partialJob) {

        Job existing = jobImp.findById(id);

        if (existing == null) {
            throw new ResourceNotFoundException(
                    "Job with ID " + id + " not found");
        }

        return jobImp.patchJob(id, partialJob);
    }
}