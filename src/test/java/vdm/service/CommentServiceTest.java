package vdm.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import vdm.dto.CommentDTO;
import vdm.entity.Comment;
import vdm.entity.Task;
import vdm.repository.CommentRepository;
import vdm.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService = new CommentService();

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private Comment comment;

    @Mock
    private Task task;

    @Before
    public void setUp() throws Exception {

        when(comment.getComment()).thenReturn("commentTest");
        when(comment.getCommentId()).thenReturn(5);
        int taskId = 1;
        int commentId = 5;
        List<Comment> commentList = new ArrayList<>();
        when(task.getComments()).thenReturn(commentList);
        commentList.add(comment);
        task.setComments(commentList);
        when(taskRepository.getOne(taskId)).thenReturn(task);
        when(commentRepository.getOne(commentId)).thenReturn(comment);
    }

    @Test
    public void getCommentsOfTask() throws Exception {
        int taskId = 1;

        List<CommentDTO> resultList = commentService.getCommentsOfTask(taskId);

        assertEquals(resultList.get(0).getCommentId(),5);
    }


    @Test
    public void addComment() throws Exception {
        int taskId = 1;

        List<CommentDTO> resultList = commentService.addComment(taskId, comment);

        assertEquals(resultList.get(0).getCommentId(),5);
    }

    @Test
    public void deleteComment() throws Exception {
        int taskId = 1;
        int commentId = 2;

        List<CommentDTO> resultList = commentService.deleteComment(commentId,
                                                                   taskId);

        assertEquals(resultList.get(0).getCommentId(),5);
    }

    @Test
    public void updateComment() throws Exception {
        int taskId = 1;

        List<CommentDTO> resultList = commentService.updateComment(taskId,
                                                                   comment);

        assertEquals(resultList.get(0).getCommentId(),5);
    }

}