-- Drop if exists (optional)
DROP TABLE IF EXISTS bill, appointment, patient, dentist, surgery, office_manager CASCADE;

-- 1. Office Manager
CREATE TABLE office_manager (
                                manager_id SERIAL PRIMARY KEY,
                                first_name VARCHAR(50) NOT NULL,
                                last_name  VARCHAR(50) NOT NULL,
                                phone      VARCHAR(20),
                                email      VARCHAR(100) UNIQUE
);

-- 2. Patient
CREATE TABLE patient (
                         patient_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name  VARCHAR(50) NOT NULL,
                         phone      VARCHAR(20),
                         email      VARCHAR(100) UNIQUE,
                         date_of_birth DATE NOT NULL,
                         address    TEXT
);

-- 3. Dentist
CREATE TABLE dentist (
                         dentist_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name  VARCHAR(50) NOT NULL,
                         phone      VARCHAR(20),
                         email      VARCHAR(100) UNIQUE,
                         specialization VARCHAR(100)
);

-- 4. Surgery
CREATE TABLE surgery (
                         surgery_id SERIAL PRIMARY KEY,
                         name       VARCHAR(100) NOT NULL,
                         address    TEXT NOT NULL,
                         phone      VARCHAR(20)
);

-- 5. Appointment
CREATE TABLE appointment (
                             appointment_id SERIAL PRIMARY KEY,
                             date_time      TIMESTAMP NOT NULL,
                             status         VARCHAR(20) CHECK (status IN ('CONFIRMED', 'CANCELLED', 'RESCHEDULED')),
                             patient_id     INT NOT NULL REFERENCES patient(patient_id) ON DELETE CASCADE,
                             dentist_id     INT NOT NULL REFERENCES dentist(dentist_id) ON DELETE CASCADE,
                             surgery_id     INT NOT NULL REFERENCES surgery(surgery_id) ON DELETE CASCADE
);

-- 6. Bill
CREATE TABLE bill (
                      bill_id        SERIAL PRIMARY KEY,
                      amount         NUMERIC(10, 2) NOT NULL,
                      is_paid        BOOLEAN NOT NULL DEFAULT FALSE,
                      appointment_id INT NOT NULL UNIQUE REFERENCES appointment(appointment_id) ON DELETE CASCADE,
                      patient_id     INT NOT NULL REFERENCES patient(patient_id) ON DELETE CASCADE
);

INSERT INTO patient (first_name, last_name, phone, email, date_of_birth, address)
VALUES ('Alice', 'Johnson', '555-1001', 'alice.johnson@example.com', '1988-06-15', '123 Apple St'),
  ('Bob', 'Miller', '555-1002', 'bob.miller@example.com', '1975-09-22', '456 Banana Ave'),
  ('Carol', 'Smith', '555-1003', 'carol.smith@example.com', '1990-11-03', '789 Cherry Blvd'),
  ('David', 'Lee', '555-1004', 'david.lee@example.com', '1982-03-30', '135 Walnut Way');


INSERT INTO dentist (first_name, last_name, phone, email, specialization)
VALUES
  ('Emily', 'White', '555-2001', 'emily.white@example.com', 'Orthodontics'),
  ('Frank', 'Green', '555-2002', 'frank.green@example.com', 'Pediatric Dentistry'),
  ('Grace', 'Black', '555-2003', 'grace.black@example.com', 'Cosmetic Dentistry');


INSERT INTO surgery (name, address, phone) VALUES
   ('Downtown Dental', '10 Main St', '555-3001'),
   ('Uptown Smiles', '22 North Rd', '555-3002'),
   ('Suburban Dental Care', '88 South Rd', '555-3003');

-- Alice with Emily
INSERT INTO appointment (date_time, status, patient_id, dentist_id, surgery_id) VALUES
    ('2025-04-10 10:00', 'CONFIRMED', 1, 1, 1),
    ('2025-04-15 14:00', 'CONFIRMED', 1, 1, 1);

-- Bob with Frank
INSERT INTO appointment (date_time, status, patient_id, dentist_id, surgery_id) VALUES
    ('2025-04-12 11:00', 'CONFIRMED', 2, 2, 2);

-- Carol with Grace
INSERT INTO appointment (date_time, status, patient_id, dentist_id, surgery_id) VALUES
    ('2025-04-14 09:00', 'CANCELLED', 3, 3, 3);

-- David with Emily
INSERT INTO appointment (date_time, status, patient_id, dentist_id, surgery_id) VALUES
    ('2025-04-20 15:00', 'CONFIRMED', 4, 1, 1);


INSERT INTO bill (amount, is_paid, appointment_id, patient_id) VALUES
(150.00, TRUE, 1, 1),
(200.00, FALSE, 2, 1),
(300.00, TRUE, 3, 2),
(250.00, FALSE, 5, 4);



SELECT *
FROM dentist
ORDER BY last_name ASC;


SELECT
    a.appointment_id,
    a.date_time,
    a.status,
    p.patient_id,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name,
    p.email AS patient_email
FROM appointment a
         JOIN patient p ON a.patient_id = p.patient_id
WHERE a.dentist_id = 1;  -- Replace 1 with the actual dentist_id

SELECT
    a.appointment_id,
    a.date_time,
    a.status,
    s.name AS surgery_name,
    s.address AS surgery_address
FROM appointment a
         JOIN surgery s ON a.surgery_id = s.surgery_id
ORDER BY a.date_time ASC;

SELECT
    appointment_id,
    date_time,
    status
FROM appointment
WHERE patient_id = 1
  AND DATE(date_time) = '2025-04-10';

