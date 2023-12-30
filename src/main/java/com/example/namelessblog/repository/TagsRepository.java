package com.example.namelessblog.repository;

import com.example.namelessblog.domain.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {

}
