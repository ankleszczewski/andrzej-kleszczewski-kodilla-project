package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskMapperTest {
    @Test
    public void testMapToTask(){
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(100l, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals((Long)100l, task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(101l, "its a title", "its a contnent");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals((Long)101l, taskDto.getId());
        assertEquals("its a title", taskDto.getTitle());
        assertEquals("its a contnent", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(10l, "task", "content"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, taskDtoList.size());
        assertEquals((Long)10l, taskDtoList.get(0).getId());
        assertEquals("task", taskDtoList.get(0).getTitle());
        assertEquals("content", taskDtoList.get(0).getContent());
    }
}
