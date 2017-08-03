package com.rhb.ginkgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhb.ginkgo.repository.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {

}
