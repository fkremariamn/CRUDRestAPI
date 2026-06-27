package com.employeeCRUD.empCRUD.Service;

import com.employeeCRUD.empCRUD.DAO.JobDAOImpl;
import com.employeeCRUD.empCRUD.Entity.Job;
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
        return jobImp.findById(id);
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
        return jobImp.patchJob(id, partialJob);
    }
}