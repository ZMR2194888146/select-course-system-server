-- 添加默认管理员
insert into administrator(password, username)values ("88888888", "admin");
insert into student(class_name, college, major, name, password, username) values ("151","电信学院","计科","赵梦儒","123456","201502010232");
insert into teacher(college, name, password, username) VALUES ("电信学院","张厚华","123456","zhh");
-- insert into course(count, capacity, "date", duce, name, score, "space", tid, "time") VALUES (3,40,3,24,"计算机组成原理",3.5,"305",1,2);