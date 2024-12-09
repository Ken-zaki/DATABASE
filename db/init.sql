CREATE DATABASE batangas_db;

USE batangas_db;

CREATE TABLE accounts (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) UNIQUE,
    password VARCHAR(30)
    );
    
CREATE TABLE account_role(
	user_id INT,
    role VARCHAR(20),
	FOREIGN KEY (user_id) REFERENCES accounts(user_id)
    );
    
CREATE TABLE resources(
	resourceID INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(20),
    name VARCHAR(50),
    location VARCHAR(100),
    details VARCHAR(200)
    );
    
    
INSERT INTO resources (
	category, 
	name, 
	location, 
	details) 

VALUES (
	'Healthcare' , 'Batangas Healthcare Specialist Medical Center' , 'Diversion Road, Barangay Alangilan. Batangas, 4200 Batangas' , 'Open 24hrs - (043) 403 8642'),
	('Educational' , 'Batangas State University' , 'Q3MD+MPQ, Rizal Avenue Extension, Batangas' , 'Open 7am - 5pm - (043) 980 0385'),
    ('Food' , 'Butch Seafood & Grill Restaurant' , '8 National Road, Batangas' , 'Open 7am - 1pm - (043) 300 7488'),
    ('Transportation' , 'Batangas City Grand Terminal' , 'Grand Terminal, Diversion Road, Batangas' , 'Open 24hrs');

