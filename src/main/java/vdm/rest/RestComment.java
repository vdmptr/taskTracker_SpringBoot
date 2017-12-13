package vdm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vdm.entity.Comment;
import vdm.service.CommentService;
import vdm.dto.CommentDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class RestComment {

    private CommentService commentService;

    @Autowired
    RestComment(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping(value = "/{taskId}",
            produces = "application/json")
    public List<CommentDTO> getCommentOfTask(@PathVariable int taskId){
        return commentService.getCommentsOfTask(taskId);
    }

    @PutMapping(value = "/{taskId}",
            produces = "application/json" )
    public List<CommentDTO> updateComment(@PathVariable int taskId,
                                          @RequestBody Comment comment){
        return commentService.updateComment(taskId, comment);
    }

    @DeleteMapping(value = "/{commentId}/{taskId}",
            produces = "application/json")
    public List<CommentDTO> deleteComment(@PathVariable int commentId,
                              @PathVariable int taskId){
        return commentService.deleteComment(commentId,taskId);
    }

    @PostMapping(value = "/{taskId}",
            produces = "application/json")
    public List<CommentDTO> addComment(@PathVariable int taskId,
                                       @RequestBody Comment comment){
        return commentService.addComment(taskId, comment);
    }



}
