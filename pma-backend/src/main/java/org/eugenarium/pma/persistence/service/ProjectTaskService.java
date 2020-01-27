package org.eugenarium.pma.persistence.service;

import org.eugenarium.pma.persistence.domain.ProjectTask;

public interface ProjectTaskService {

    ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username);
    ProjectTask findByProjectSequence(String backlogId, String projectTaskId, String username);
    ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId,
                                        String projectTaskId, String username);
    void deleteByProjectSequence(String backlogId, String projectTaskId, String username);
    Iterable<ProjectTask> findBacklogById(String id, String username);
}
