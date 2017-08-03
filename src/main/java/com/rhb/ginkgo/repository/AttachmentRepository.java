package com.rhb.ginkgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhb.ginkgo.repository.entity.AttachmentEntity;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, String> {

}
