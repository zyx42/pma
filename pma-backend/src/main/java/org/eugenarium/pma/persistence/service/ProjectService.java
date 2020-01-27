package org.eugenarium.pma.persistence.service;

import org.eugenarium.pma.persistence.domain.Project;

public interface ProjectService {

    Project saveOrUpdateProject(Project project, String username);
    Project findProjectByIdentifier(String projectId, String username);
    Iterable<Project> findAllProject(String username);
    void deleteProjectByIdentifier(String projectId, String username);
}
