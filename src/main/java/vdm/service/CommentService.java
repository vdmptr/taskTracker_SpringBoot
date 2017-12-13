package vdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vdm.entity.Comment;
import vdm.entity.Task;
import vdm.repository.CommentRepository;
import vdm.repository.TaskRepository;
import vdm.dto.CommentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentService {

    private CommentRepository commentRepository;
    private TaskRepository taskRepository;

    public CommentService() {
    }

    @Autowired
    CommentService(CommentRepository commentRepository,
                   TaskRepository taskRepository){
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public List<CommentDTO> getCommentsOfTask(int taskId){
        Task persistenceTask = taskRepository.getOne(taskId);
        return convertComments(persistenceTask.getComments());

    }

    @Transactional
    public List<CommentDTO> addComment(int taskId, Comment comment){
        Task persistenceTask = taskRepository.getOne(taskId);
        comment.setTask(persistenceTask);
        commentRepository.saveAndFlush(comment);
        persistenceTask = taskRepository.getOne(taskId);
        return convertComments(persistenceTask.getComments());
    }

    @Transactional
    public List<CommentDTO> deleteComment(int commentId, int taskId){
        commentRepository.delete(commentId);
        commentRepository.flush();
        Task persistenceTask = taskRepository.getOne(taskId);
        List<Comment> list = persistenceTask.getComments();
        return   convertComments(list);
    }



    @Transactional
    public List<CommentDTO> updateComment(int taskId, Comment comment){
        Comment persistenceComment = commentRepository.getOne(comment.getCommentId());
        persistenceComment.setComment(comment.getComment());
        commentRepository.saveAndFlush(persistenceComment);
        Task persistenceTask = taskRepository.getOne(taskId);
        return  convertComments(persistenceTask.getComments());
    }

     private List<CommentDTO> convertComments(List<Comment> comments){
        return comments.stream()
                       .map((c) -> {
                           CommentDTO commentDTO = new CommentDTO();
                           commentDTO.setComment(c.getComment());
                           commentDTO.setCommentId(c.getCommentId());
                           return commentDTO;
                       })
                       .collect(Collectors.toList());
    }

}
