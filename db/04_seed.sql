INSERT INTO weekday (weekday_id, name) VALUES
(1, 'Понедельник'),
(2, 'Вторник'),
(3, 'Среда'),
(4, 'Четверг'),
(5, 'Пятница'),
(6, 'Суббота');

INSERT INTO lesson_time (lesson_number, time_start, time_end) VALUES
(1, '08:30', '10:00'),
(2, '10:10', '11:40'),
(3, '12:10', '13:40'),
(4, '13:50', '15:20'),
(5, '15:30', '17:00'),
(6, '17:10', '18:40');

INSERT INTO specialties (name) VALUES
('Информационные системы и программирование'),
('Авиационные приборы и комплексы');

INSERT INTO building (name, address) VALUES
('Корпус А', 'г. Новосибирск, ул. Авиационная, 1'),
('Корпус Б', 'г. Новосибирск, ул. Авиационная, 2');

INSERT INTO classroom (building_id, room_number) VALUES
(1, '101'),
(1, '102'),
(2, '201'),
(2, '202');

INSERT INTO subject (name) VALUES
('Математика'),
('Программирование'),
('Физика'),
('Базы данных');

INSERT INTO teacher (last_name, first_name, middle_name, position) VALUES
('Иванов', 'Иван', 'Иванович', 'Преподаватель'),
('Петров', 'Пётр', 'Петрович', 'Старший преподаватель');

INSERT INTO student_group (group_name, course, specialty_id) VALUES
('ИС-12', 1, 1),
('ИС-22', 2, 1);
