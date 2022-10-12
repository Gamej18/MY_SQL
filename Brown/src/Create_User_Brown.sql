use university;

CREATE USER 'Brown'@'localhost'  IDENTIFIED BY 'brown123';
GRANT SELECT (dept_name, building) ON university.department TO 'Brown'@'localhost';
GRANT INSERT, DELETE, SELECT ON university.course TO 'Brown'@'localhost';
GRANT UPDATE (title) ON university.course TO 'Brown'@'localhost';
GRANT INSERT, DELETE, SELECT ON university.prereq TO 'Brown'@'localhost';


-- DROP USER 'Brown'@'localhost';
-- Didn't know if I should drop the users.