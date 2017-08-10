package com.rhb.ginkgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rhb.ginkgo.repository.entity.TaskdetailEntity;

public interface TaskdetailRepository extends JpaRepository<TaskdetailEntity, String> {
	
	@Query("select t from TaskdetailEntity t where t.isrecall!=2 and t.text_content=?")
	public List<TaskdetailEntity> findByIsrecallNotAndTextcontent(String str);
}
