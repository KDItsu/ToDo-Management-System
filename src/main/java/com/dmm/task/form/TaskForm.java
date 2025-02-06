package com.dmm.task.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TaskForm {
  private String title;
  private String text;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;
  private boolean done;
}

//前回の Tasksクラスとほぼ同じですが、
//Tasksクラスはデータベースへの連携用、TaskFormは画面との連携用、と覚えてください！