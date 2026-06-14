package com.employeeCRUD.empCRUD.DAO;

import com.employeeCRUD.empCRUD.Entity.Job;


import java.util.List;

public interface JobDAO {

List<Job> findAll();

    Job findById(Integer id);

    Job save(Job job);
    void deleteById(Integer id);
    Job update(Job job);

}

