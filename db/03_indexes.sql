-- Одна группа не может иметь две пары одновременно
CREATE UNIQUE INDEX uq_schedule_group_time
ON schedule (lesson_date, lesson_time_id, group_id, group_part);

-- В одной аудитории не может быть двух занятий одновременно
CREATE UNIQUE INDEX uq_schedule_classroom_time
ON schedule (lesson_date, lesson_time_id, classroom_id);
