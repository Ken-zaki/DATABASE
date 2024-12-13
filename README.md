<div align="center">
    <a href="https://github.com/Ken-zaki" target="_blank">
        <img src="https://github.com/user-attachments/assets/e84486d4-79ad-4c12-8605-5d24cd397edb" 
        alt="Logo" width="290" height="290">
    </a>
</div>

# Local Resource Finder (Batangas Area) ⛳

Welcome to the **Local Resource Finder**—a Java-based application designed to help manage and find resources in the Batangas area! This project uses a MySQL database for storing and managing data and differentiates between users and administrators for tailored functionalities.

---

## Features 🚀

### 🔐 User Features
- **Login & Registration**
  - Secure login for existing users.
  - Automatic role assignment during registration (Admin/User).
- **View Resources**
  - Explore available resources categorized into:
    - Healthcare 🏥
    - Education 📚
    - Food 🍔
    - Transportation 🚌

### 🚧 Admin Features
- **Add Resources**
  - Add new resources with details (name, location, category, etc.).
- **Remove Resources**
  - Delete existing resources from the database.
- **View All Resources**
  - View all available resources by category.

---

## Getting Started 🚫🔄

### Prerequisites
- **Java JDK**: Ensure you have Java 8+ installed.
- **MySQL**: Setup MySQL database.
- **IDE**: Use IntelliJ IDEA, Eclipse, or any preferred IDE.
- **Database Setup**: Create tables in your MySQL database:
  ```sql
  CREATE TABLE accounts (
      user_id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(20) UNIQUE ,
      password VARCHAR(30) 
  );

  CREATE TABLE account_role (
      user_id INT,
      role VARCHAR(20),
      FOREIGN KEY (user_id) REFERENCES accounts(user_id)
  );

  CREATE TABLE resources (
      resourceID INT AUTO_INCREMENT PRIMARY KEY,
      category VARCHAR(20),
      name VARCHAR(50),
      location VARCHAR(100),
      details VARCHAR(200)
  );
  ```

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo-name/local-resource-finder.git
   ```

2. Open the project in your preferred IDE.

3. Update `DatabaseConnection.java` with your MySQL credentials:
   ```java
   private static final String URL = "jdbc:mysql://127.0.0.1:3306/Batangas_db";
   private static final String USER = "root";
   private static final String PASSWORD = "yourpassword";
   ```

4. Run the `Main.java` file.

---

## How It Works 🌐

1. **Login/Register**:
   Users can log in with existing accounts or register a new account. Role assignments:
   - Passwords with "@" = Admin.
   - All other passwords = User.

2. **User Menu**:
   - View resources in specific categories.
   - Logout when done.

3. **Admin Menu**:
   - Add resources with details like name, location, and category.
   - Remove resources by name.
   - View resources in any category.

---

## How to Use the Program 🕹️

Below is an illustration of how to use the program.

![How to Use]([path/to/your/image.png](https://github.com/user-attachments/assets/4cb9e40e-cf10-4d02-89f4-4ef310e00f40))

---

## Built With 🔧
- **Java**: Programming language.
- **MySQL**: Database management.
- **JDBC**: For database connectivity.

---

## Authors 🌟
- **Your Name** – *Initial work* – [Ken]([https://github.com/your-profile](https://github.com/Ken-zaki))



