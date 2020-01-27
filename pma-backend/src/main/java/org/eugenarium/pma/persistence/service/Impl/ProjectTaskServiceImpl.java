package org.eugenarium.pma.persistence.service.Impl;

import org.eugenarium.pma.exceptions.ProjectNotFoundException;
import org.eugenarium.pma.persistence.domain.Backlog;
import org.eugenarium.pma.persistence.domain.ProjectTask;
import org.eugenarium.pma.persistence.repository.ProjectTaskRepository;
import org.eugenarium.pma.persistence.service.ProjectService;
import org.eugenarium.pma.persistence.service.ProjectTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectService projectService;

    public ProjectTaskServiceImpl(ProjectTaskRepository projectTaskRepository, ProjectService projectService) {
        this.projectService = projectService;
        this.projectTaskRepository = projectTaskRepository;
    }

    @Override
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
        System.out.println(backlog);
        projectTask.setBacklog(backlog);
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;

        backlog.setPTSequence(backlogSequence);

        projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if (projectTask.getStatus().equals("") || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        // Fix bug with priority in Spring Boot Server, needs to check null first
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }

        return projectTaskRepository.save(projectTask);
    }

    @Override
    public Iterable<ProjectTask> findBacklogById(String id, String username) {

        // Check if project exists
        projectService.findProjectByIdentifier(id, username);

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    @Override
    public ProjectTask findByProjectSequence(String backlogId, String projectTaskId, String username) {

        // Check if backlog exists
        projectService.findProjectByIdentifier(backlogId, username);

        // Check if project task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTaskId);

        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task '" + projectTaskId + "' not found");
        }

        // Check if backlog/project id corresponds the right project
        if (!projectTask.getProjectIdentifier().equals(backlogId)) {
            throw new ProjectNotFoundException("Project Task '" + projectTaskId + "' does not exist in project: '" +
                    backlogId);
        }

        return projectTask;
    }

    @Override
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId,
                                               String username) {
        ProjectTask projectTask = findByProjectSequence(backlogId, projectTaskId, username);
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    @Override
    public void deleteByProjectSequence(String backlogId, String projectTaskId, String username) {
        ProjectTask projectTask = findByProjectSequence(backlogId, projectTaskId, username);
        projectTaskRepository.delete(projectTask);
    }
}
