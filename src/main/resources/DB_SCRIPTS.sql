-- 🚀 Step 1: Create the Database (Only if it doesn't exist)
CREATE DATABASE IF NOT EXISTS student_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- Uses UTF-8 encoding (utf8mb4) to support special characters
-- Uses Unicode collation (utf8mb4_unicode_ci) for proper sorting

-- Selecting the newly created database
USE student_db;

-- 🚀 Step 2: Create the Students Table
CREATE TABLE IF NOT EXISTS student (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    class_group VARCHAR(50) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 🚀 Step 3: Create the Attendance Table
CREATE TABLE IF NOT EXISTS attendance (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    attendance_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    status ENUM('Present', 'Absent') NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    UNIQUE (student_id, attendance_date)
);

-- 🚀 Step 4: Insert Sample Data into Students Table
INSERT INTO student (name, class_group) VALUES
('Alice Johnson', 'G1'),
('Bob Smith', 'G1'),
('Charlie Brown', 'G2'),
('David Wilson', 'G2');

select * from student;
SELECT id, name, class_group, create_date FROM student WHERE class_group LIKE 'G1';