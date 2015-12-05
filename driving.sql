CREATE TABLE anicolette.Employee(
	IdNo INT NOT NULL, PRIMARY KEY(IdNo),
	firstName VARCHAR(50),
	middleName VARCHAR(50),
	lastName VARCHAR(50),	
	ssn INT,
	Salary INT,
	OfficeNo INT
);

CREATE SEQUENCE emp_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER emp_id_trigger
	BEFORE INSERT ON anicolette.Employee
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT emp_id_seq.nextval INTO :NEW.IdNo FROM DUAL;
	END;
	/	

CREATE TABLE anicolette.Role(
	empId INT, 
	CONSTRAINT fk_empKey FOREIGN KEY (empId) REFERENCES anicolette.Employee(IdNo) ON DELETE CASCADE,
	role VARCHAR(50),
	PRIMARY KEY (empId, role)
);

CREATE TABLE anicolette.Office(
	OfficeNo INT, PRIMARY KEY(OfficeNo),
	mgrId INT, 
	CONSTRAINT fk_mgrKey FOREIGN KEY(mgrId) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	city VARCHAR(50)
);

CREATE SEQUENCE office_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER office_id_trigger
	BEFORE INSERT ON anicolette.Office
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT emp_id_seq.nextval INTO :NEW.OfficeNo FROM DUAL;
	END;
	/	

ALTER TABLE anicolette.Employee
	ADD CONSTRAINT fk_OfficeKey
	FOREIGN KEY (OfficeNo) REFERENCES anicolette.Office(OfficeNo) 
	ON DELETE SET NULL;
	
CREATE TABLE anicolette.Car(
	CarId INT, PRIMARY KEY(CarId),
	LPlate VARCHAR(10)
);

CREATE SEQUENCE car_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER car_id_trigger
	BEFORE INSERT ON anicolette.Car
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT car_id_seq.nextval INTO :NEW.CarId FROM DUAL;
	END;
	/	

CREATE TABLE anicolette.Inspection(
	InsId INT, PRIMARY KEY(InsId),
	instDate TIMESTAMP,
	Inspector INT, 
	CONSTRAINT fk_insKey FOREIGN KEY(Inspector) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	CarId INT,
	CONSTRAINT fk_carKey FOREIGN KEY(CarId) REFERENCES anicolette.Car(CarId) ON DELETE SET NULL
);

CREATE SEQUENCE ins_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER ins_id_trigger
	BEFORE INSERT ON anicolette.Inspection
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT ins_id_seq.nextval INTO :NEW.InsId FROM DUAL;
	END;
	/

CREATE TABLE anicolette.Fault(
	InsId INT,
	CONSTRAINT fk_inspectionKey FOREIGN KEY(InsId) REFERENCES anicolette.Inspection(InsId) ON DELETE SET NULL,
	Description VARCHAR(500) NOT NULL,
	PRIMARY KEY(InsId, Description)
);

CREATE TABLE anicolette.Client(
	ClientId INT, PRIMARY KEY(ClientId),
	firstName VARCHAR(50),
	middleName VARCHAR(50),
	lastName VARCHAR(50),
	registered INT,
	CONSTRAINT fk_officeregKey FOREIGN KEY(registered) REFERENCES anicolette.Office(OfficeNo) ON DELETE SET NULL,
	dob DATE,
	ssn INT,
	provisionNum INT,
	assignedInstructor INT,
	CONSTRAINT fk_assignedinsKey FOREIGN KEY(assignedInstructor) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	requestedInstructor INT,
	CONSTRAINT fk_requestedinKey FOREIGN KEY(requestedInstructor) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL
);

CREATE SEQUENCE client_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER client_id_trigger
	BEFORE INSERT ON anicolette.Client
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT ins_id_seq.nextval INTO :NEW.ClientId FROM DUAL;
	END;
	/

CREATE TABLE anicolette.Interview(
	Interviewer INT,
	CONSTRAINT fk_interviewerKey FOREIGN KEY(Interviewer) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	time TIMESTAMP,
	Client INT,
	CONSTRAINT fk_interviewedClient FOREIGN KEY(Client) REFERENCES anicolette.Client(ClientId) ON DELETE SET NULL,
	PRIMARY KEY(Interviewer, time)
);	

CREATE TABLE anicolette.Need(
	Client INT, 
	CONSTRAINT fk_clientneedKey FOREIGN KEY(Client) REFERENCES anicolette.Client(ClientId) ON DELETE CASCADE,
	Instructor INT,
	CONSTRAINT fk_instneedKey FOREIGN KEY(Instructor) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	Description VARCHAR(500),
	PRIMARY KEY(Client, Instructor, Description)
);

CREATE TABLE anicolette.Lesson(
	LessonId INT, PRIMARY KEY(LessonId),
	Client INT,
	CONSTRAINT fk_clientlessonKey FOREIGN KEY(Client) REFERENCES anicolette.Client(ClientId) ON DELETE SET NULL,
	Instructor INT,
	CONSTRAINT fk_instlessonKey FOREIGN KEY(Instructor) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	time TIMESTAMP,
	isBlocked CHAR
);

CREATE SEQUENCE lesson_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER lesson_id_trigger
	BEFORE INSERT ON anicolette.Lesson
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT ins_id_seq.nextval INTO :NEW.LessonId FROM DUAL;
	END;
	/

CREATE TABLE anicolette.Note(
	LessonId INT,
	CONSTRAINT fk_notelessonKey FOREIGN KEY(LessonId) REFERENCES anicolette.Lesson(LessonId) ON DELETE CASCADE,
	Note VARCHAR(500),
	PRIMARY KEY(LessonId, Note)	
);

CREATE TABLE anicolette.DrivingTest(
	TestId INT, PRIMARY KEY(TestId),
	InstructorId INT,
	CONSTRAINT fk_testInst FOREIGN KEY(InstructorId) REFERENCES anicolette.Employee(IdNo) ON DELETE SET NULL,
	Client INT,
	CONSTRAINT fk_testClient FOREIGN KEY(Client) REFERENCES anicolette.Client(ClientId) ON DELETE CASCADE,
	time TIMESTAMP,
	testType CHAR NOT NULL,
	pass CHAR NOT NULL
);

CREATE SEQUENCE test_id_seq
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER test_id_trigger
	BEFORE INSERT ON anicolette.DrivingTest
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
	SELECT ins_id_seq.nextval INTO :NEW.TestId FROM DUAL;
	END;
	/

CREATE TABLE anicolette.Failure(
	TestId INT, 
	CONSTRAINT fk_failuretest FOREIGN KEY(TestId) REFERENCES anicolette.DrivingTest(TestId) ON DELETE CASCADE,
	Description VARCHAR(500),
	PRIMARY KEY(TestId, Description)
);
