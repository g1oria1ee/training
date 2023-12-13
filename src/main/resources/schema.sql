drop table if exists rectangles;
drop table if exists dates;

create table rectangles (
id INT AUTO_INCREMENT PRIMARY KEY,
length INT NOT NULL,
width INT,
area INT,
perimeter INT,
color VARCHAR(255) NOT NULL
);

create table dates (
id INT AUTO_INCREMENT PRIMARY KEY,
updateMonth INT,
updateDay INT,
updateYear INT
);

--insert into rectangles (length, width, area, perimeter, unit) values
--(5, 5, 25, 20, 'cm'), (50, 2, 100, 104, 'ft');