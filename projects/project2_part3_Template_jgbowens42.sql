-- use university;
#1 -----------------------------------------------

-- select (instructor.name), min(t1.year), t1.semester
-- from instructor natural join teaches as t1, teaches as t2
-- where instructor.id = t1.id
-- group by name, semester, t1.year;

#2 -----------------------------------------------

-- select section.sec_id, count(distinct takes.id) as student, count(distinct teaches.id) as instructor
-- from section natural join takes, teaches
-- where section.sec_id = teaches.sec_id
-- group by section.sec_id, section.course_id
-- ------------------------------------------------

#3

-- select instructor.name, count(student.id)
-- from instructor natural join advisor, student
-- where instructor.id = advisor.i_id and student.id = advisor.s_id
-- group by name;

#4 ---------------------------------------------------------------

-- select count(sec_id), semester, year
-- from section
-- group by sec_id, semester, year
-- order by year, semester asc

#5 ----------------------------------------------------------------

-- select building, sum(capacity)
-- from classroom
-- group by building


#6 -----------------------------------------------------------------

-- select student.name, count(student.name)
-- from student, instructor
-- where instructor.name = student.name
-- group by student.name
-- having count(student.name > 1)

-- ITS PROBABLY SUPER SIMPLE, BUT I JUST CAN'T THINK OF HOW TO COMBINE THEM
#7 -----------------------------------------------------------------

-- select dept_name, avg(course.credits), year
-- from course natural join section
-- group by section.year, dept_name
-- order by dept_name;

#8 ------------------------------------------------------------------

-- select distinct teaches.ID, instructor.name
-- from teaches join takes join instructor
-- where teaches.course_id = takes.course_id and teaches.sec_id = takes.sec_id and teaches.semester = takes.semester and instructor.ID = teaches.ID
-- group by teaches.id
-- having count(teaches.sec_id > 3);

#9 -------------------------------------------------------------------

-- select course_id, count(sec_id)
-- from section
-- group by course_id

#10 -------------------------------------------------------------------

-- select department.dept_name, count(distinct student.id) as students, count(distinct instructor.id) as teachers
-- from department natural left outer join student, instructor
-- where department.dept_name = instructor.dept_name 
-- group by department.dept_name;
