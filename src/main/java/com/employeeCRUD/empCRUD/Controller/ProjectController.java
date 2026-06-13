package com.employeeCRUD.empCRUD.Controller;

import com.employeeCRUD.empCRUD.Dto.ApiResponse;
import com.employeeCRUD.empCRUD.Entity.Project;
import com.employeeCRUD.empCRUD.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        List<Project> list= projectService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("success", list));
    }
@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> getProjectById(@PathVariable int id) {
    Project project = projectService.findById(id);
    if (project != null) {
        return ResponseEntity.ok(new ApiResponse<>("success", project));
    }
    else {
        return ResponseEntity.notFound().build();
    }
}
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable int id) {
    projectService.deleteById(id);
    return ResponseEntity.status(204).body(new ApiResponse<>("Project deleted", null));

}
@PutMapping("/{id}")
public ResponseEntity<ApiResponse<Project>> updateProjects(@PathVariable int id, @RequestBody Project project) {
        Project proj = projectService.findById(id);
        if (proj != null) {
            project.setId(id);
            Project updated = projectService.update(project);

            return ResponseEntity.ok(
                    new ApiResponse<>("Project updated successfully", updated)
            );
        }
        else  {
            return ResponseEntity.notFound().build();
        }
}
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> patchProject(
            @PathVariable int id,
            @RequestBody Project project) {

        Project updated =
                projectService.patchProject(id, project);

        if(updated == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new ApiResponse<>("Project patched successfully",
                        updated));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Project>> createProject(
            @RequestBody Project project) {

        Project saved = projectService.save(project);

        return ResponseEntity.status(201)
                .body(new ApiResponse<>("Project created", saved));
    }
}
