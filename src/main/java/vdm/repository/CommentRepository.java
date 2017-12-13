package vdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdm.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
