drop table if exists rectangles;

create table rectangles (
id INT AUTO_INCREMENT PRIMARY KEY,
length VARCHAR(250) NOT NULL,
width VARCHAR(250) NOT NULL,
area VARCHAR(250) NOT NULL,
perimeter VARCHAR(250) NOT NULL
);