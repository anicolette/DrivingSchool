ALTER TABLE anicolette.Employee DROP COLUMN IdNo CASCADE CONSTRAINTS;

DROP SEQUENCE emp_id_seq;

DROP TRIGGER emp_id_trigger;

DROP TABLE anicolette.Employee;

DROP TABLE anicolette.Role;

ALTER TABLE anicolette.Office DROP COLUMN OfficeNo CASCADE CONSTRAINTS;

DROP SEQUENCE office_id_seq;

DROP TRIGGER office_id_trigger;

DROP TABLE anicolette.Office;

DROP SEQUENCE car_id_seq;

DROP TRIGGER car_id_trigger;

ALTER TABLE anicolette.Car DROP COLUMN CarId CASCADE CONSTRAINTS;

DROP TABLE anicolette.Car;

DROP SEQUENCE ins_id_seq;

DROP TRIGGER ins_id_trigger;

ALTER TABLE anicolette.Inspection DROP COLUMN InsId CASCADE CONSTRAINTS;

DROP TABLE anicolette.Inspection;

DROP TABLE anicolette.Fault;

ALTER TABLE anicolette.Client DROP COLUMN ClientId CASCADE CONSTRAINTS;

DROP SEQUENCE client_id_seq;

DROP TRIGGER client_id_trigger;

DROP TABLE anicolette.Client;

DROP TABLE anicolette.Interview;

DROP TABLE anicolette.Need;

DROP SEQUENCE lesson_id_seq;

DROP TRIGGER lesson_id_trigger;

ALTER TABLE anicolette.Lesson DROP COLUMN LessonId CASCADE CONSTRAINTS;

DROP TABLE anicolette.Lesson;

DROP TABLE anicolette.Note;

ALTER TABLE anicolette.DrivingTest DROP COLUMN TestId CASCADE CONSTRAINTS;

DROP SEQUENCE test_id_seq;

DROP TRIGGER test_id_trigger;

DROP TABLE anicolette.DrivingTest;

DROP TABLE anicolette.Failure;
