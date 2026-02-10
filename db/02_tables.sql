CREATE TABLE building (
    building_id SERIAL PRIMARY KEY,
    name         TEXT NOT NULL,
    address      TEXT NOT NULL UNIQUE
);

CREATE TABLE classroom (
    classroom_id SERIAL PRIMARY KEY,
    building_id  INTEGER NOT NULL,
    room_number  TEXT NOT NULL,

    CONSTRAINT uq_classroom UNIQUE (building_id, room_number),
    CONSTRAINT fk_classroom_building
        FOREIGN KEY (building_id)
        REFERENCES building (building_id)
);

CREATE TABLE teacher (
    teacher_id  SERIAL PRIMARY KEY,
    last_name   TEXT NOT NULL,
    first_name  TEXT NOT NULL,
    middle_name TEXT,
    position    TEXT NOT NULL
);

CREATE TABLE subject (
    subject_id SERIAL PRIMARY KEY,
    name       TEXT NOT NULL UNIQUE
);

CREATE TABLE specialties (
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE student_group (
    group_id      SERIAL PRIMARY KEY,
    group_name    TEXT NOT NULL UNIQUE,
    course        INTEGER NOT NULL CHECK (course BETWEEN 1 AND 6),
    specialty_id  INTEGER NOT NULL,

    CONSTRAINT fk_group_specialty
        FOREIGN KEY (specialty_id)
        REFERENCES specialties (id)
);

CREATE TABLE weekday (
    weekday_id SERIAL PRIMARY KEY,
    name       TEXT NOT NULL UNIQUE
);

CREATE TABLE lesson_time (
    lesson_time_id SERIAL PRIMARY KEY,
    lesson_number  INTEGER NOT NULL,
    time_start     TIME NOT NULL,
    time_end       TIME NOT NULL
);

CREATE TABLE schedule (
    schedule_id    SERIAL PRIMARY KEY,
    lesson_date    DATE NOT NULL,

    weekday_id     INTEGER NOT NULL,
    lesson_time_id INTEGER NOT NULL,
    group_id       INTEGER NOT NULL,
    subject_id     INTEGER NOT NULL,
    teacher_id     INTEGER NOT NULL,
    classroom_id   INTEGER NOT NULL,

    group_part     lesson_group_part NOT NULL,

    CONSTRAINT fk_schedule_weekday
        FOREIGN KEY (weekday_id)
        REFERENCES weekday (weekday_id),

    CONSTRAINT fk_schedule_lesson_time
        FOREIGN KEY (lesson_time_id)
        REFERENCES lesson_time (lesson_time_id),

    CONSTRAINT fk_schedule_group
        FOREIGN KEY (group_id)
        REFERENCES student_group (group_id),

    CONSTRAINT fk_schedule_subject
        FOREIGN KEY (subject_id)
        REFERENCES subject (subject_id),

    CONSTRAINT fk_schedule_teacher
        FOREIGN KEY (teacher_id)
        REFERENCES teacher (teacher_id),

    CONSTRAINT fk_schedule_classroom
        FOREIGN KEY (classroom_id)
        REFERENCES classroom (classroom_id)
);
