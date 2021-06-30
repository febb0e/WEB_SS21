package de.th.koeln.web.excercise.today.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findAllByOrderByIdDesc();
}
