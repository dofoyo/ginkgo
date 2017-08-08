package com.rhb.ginkgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhb.ginkgo.repository.entity.TaskdetailEntity;

public interface TaskdetailRepository extends JpaRepository<TaskdetailEntity, String> {
	public List<TaskdetailEntity> findByIsrecallNotAndContentLike(int isrecall,String str);
}
