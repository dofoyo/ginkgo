package com.rhb.ginkgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhb.ginkgo.repository.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, String> {

}
