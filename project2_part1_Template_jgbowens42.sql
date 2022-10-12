use university;
-- -- CSC 3300-001, Fall 2021, Project 2 Part 1
-- -- The due date for this project is 10/07/2021 at 11:59pm. The end date for this project is 10/09/2021 at 11:59pm.
-- -- Name:

-- -- Table List:
-- -- advisor(s_ID, i_ID)
-- -- classroom(building, room_number, capacity)
-- -- course(course_id, title, dept_name, credits)
-- -- department(dept_name, building, budget)
-- -- instructor(id, name, dept_name, salary)
-- -- prereq(course_id, prereq_id)
-- -- section(course_id, sec_id, semester, year, building, room_number, time_slot_id)
-- -- student(ID, name, dept_name, tot_cred)
-- -- takes(ID, course_id, sec_id, semester, year, grade)
-- -- teaches(ID, course_id, sec_id, semester, year)
-- -- time_slot(time_slot_id, day, start_hr, start_min, end_hr, end_min)

-- -- 1. Courses (IDs) and the number of sections that were created for them in the Spring 2009 semester.

-- select course_id, sec_id
-- from section
-- where semester = "Spring" and year = 2009;

-- -- 2. Capacity of each building.
-- -- Example. Assume that at some univesity there are only 2 buildings: Bruner Hall and Foundation Hall. There are 2 classrooms in BH with total capacity of 50 and 3 classrooms in FH with total capacity of 70. In that case, the query should return the relation with 2 tuples: Brunner Hall | 50 and Foundation Hall | 70.

-- select building, capacity
-- from classroom 

-- 3. Schedule of course sections in Taylor room 3128 in the Fall 2009 semester ordered by day and then start time. The first column of the returned table should include info about a day, second one - info about start time, third one - info about end time, and the last two columns - info about course id and section id respectively. Sample tuple that could appear in the result: M | 8:00 | 8:50 | 3300 | 001.

-- select day, start_hr, end_hr, course_id, sec_id
-- from time_slot natural join section
-- where building = "Taylor" and room_number = 3128 and semester = "Fall" and year = 2009;


-- -- 4. Courses (IDs and titles) with their prerequisites (IDs and titles).

-- select c1.course_id, c1.title, c2.course_id, c2.title
-- from course as c1, prereq, course as c2
-- where c1.course_id = prereq.course_id and prereq.prereq_id = c2.course_id;

-- -- 5. Instructors (IDs and names) and the number of students they taught. Ignore instructors who were never assigned to teach any course section.

-- select instructor.name, instructor.id, count(it.course_id)
-- from instructor natural join teaches as it, takes
-- where it.course_id = takes.course_id and it.sec_id = takes.sec_id and it.semester = takes.semester and it.year = takes.year
-- group by instructor.id;

-- -- 6. The biggest number of different teachers that taught some course

-- select instructor.name, count(instructor.id)
-- from instructor natural join teaches, course
-- where course.course_id = teaches.course_id and instructor.id = teaches.id
-- group by instructor.id
-- having max(instructor.id);

-- -- 7. Students (IDs and names) along with info about courses (course_ids) they took  more than 1 time.

-- select student.ID, student.name, count(takes.course_id)
-- from student natural join takes
-- where student.id = takes.id
-- group by student.id
-- having count(takes.course_id) > 1


-- -- 8. Advisors (their IDs and names) of the Computer Science department along with info about students (their IDs and names) they advise. 
-- Returned data should be ordered in a specific way.
-- -- The first few tuples should show all students of a specific advisor. The next few tuples should show all students of some other advisor etc. Sample tuples that could appear in the result: 12121 | Korth | 101 | Green, 12121 | Korth | 103 | Brown, 12121 | Korth | 207 | Snow, 15151 | Silberschatz | 423 | Brandt. It is the case then, that at the top of the table we can see info about students advised by 12121 Korth, later on, we can see info about students advised by 15151 Silberschatz.

-- select s.ID, s.name, i.ID, i.name
-- from instructor as i, advisor as a, student as s
-- where i.ID = a.i_id and a.s_id = s.ID and i.dept_name = "Comp. Sci.";

-- -- 9. Credit hours each instructor taught in the Fall semester 2009. Ignore instructors who didn't teach in that semester.

-- select instructor.name, credits	
-- from instructor natural join course natural join teaches
-- where semester = "Fall" and year = 2009;

-- -- 10. Ids of courses that have prerequisites.

-- select course.course_id
-- from course natural join prereq
-- where course.course_id = prereq.course_id;