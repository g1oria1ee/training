drop table if exists rectangles;

create table rectangles (
id INT AUTO_INCREMENT PRIMARY KEY,
length INT NOT NULL,
width INT,
area INT,
perimeter INT,
unit VARCHAR(255) NOT NULL
);

--insert into rectangles (length, width, area, perimeter, unit) values
--(5, 5, 25, 20, 'cm'), (50, 2, 100, 104, 'ft');