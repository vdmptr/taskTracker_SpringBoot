package vdm.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import vdm.entity.Comment;
import vdm.service.CommentService;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestCommentTest {

    @InjectMocks
    private RestComment restComment;

    @Mock
    private CommentService commentService;

    @Mock
    private Comment comment;

    @Test
    public void getCommentOfTask() throws Exception {
        int taskId = 1;
        restComment.getCommentOfTask(taskId);
        verify(commentService).getCommentsOfTask(taskId);
    }
    @Test
    public void updateComment() throws Exception {
        int taskId = 1;
        restComment.updateComment(taskId, comment);
        verify(commentService).updateComment(taskId, comment);
    }

    @Test
    public void deleteComment() throws Exception {
        int commentId = 1;
        int taskId = 1;
        restComment.deleteComment(commentId, taskId);
        verify(commentService).deleteComment(commentId, taskId);
    }

    @Test
    public void addComment() throws Exception {
        int taskId = 1;
        restComment.addComment(taskId, comment);
        verify(commentService).addComment(taskId, comment);
    }

}