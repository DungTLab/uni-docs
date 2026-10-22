# Final Exam (FE) Guide: Architecture, Environment, and Deployment

This guide supplements the comprehensive theoretical guides by focusing on the **practical project structure, environment configurations, and deployment descriptors**. Many FE questions test your knowledge of where files are located, how Maven is configured, and how the web application is structured.

---

## 1. Standard Java Web Project Structure (Maven)

In modern PRJ301 projects (like `FPTMusicStore`), the standard Maven directory structure is used:

```text
MyProject/
├── pom.xml                     <-- Maven configuration file (dependencies, build plugins)
├── src/
│   ├── main/
│   │   ├── java/               <-- Java source files (Servlets, Models, DAOs, DB Context)
│   │   │   ├── controller/     <-- Servlets
│   │   │   ├── dao/            <-- Data Access Objects
│   │   │   ├── model/          <-- Java Beans / POJOs
│   │   │   └── db/             <-- Database connection utilities
│   │   └── webapp/             <-- Web application root directory (accessible to clients)
│   │       ├── css/            <-- Stylesheets
│   │       ├── js/             <-- JavaScript files
│   │       ├── images/         <-- Static images
│   │       ├── META-INF/       <-- Container-specific info (e.g., context.xml)
│   │       └── WEB-INF/        <-- Private directory (NOT directly accessible via URL)
│   │           ├── web.xml     <-- Web Application Deployment Descriptor
│   │           ├── lib/        <-- JAR files (if not using Maven)
│   │           └── classes/    <-- Compiled Java classes (automatically generated)
│   └── test/                   <-- Unit tests (JUnit)
└── target/                     <-- Compiled output, WAR file generation
```

> [!IMPORTANT]
> **Key Exam Fact:** Files placed inside the `WEB-INF` folder **cannot** be accessed directly from a client browser. They must be accessed via a Servlet (using `RequestDispatcher.forward()`).

---

## 2. Deployment Descriptor: `web.xml`

The `web.xml` file is located in `/WEB-INF/web.xml`. It configures Servlets, Filters, Listeners, and session settings.

### Servlet Mapping Example
```xml
<web-app version="6.0" xmlns="https://jakarta.ee/xml/ns/jakartaee" ...>
    <!-- 1. Declare the Servlet -->
    <servlet>
        <servlet-name>ArtistServlet</servlet-name>
        <servlet-class>controller.ArtistServlet</servlet-class>
    </servlet>
    
    <!-- 2. Map the Servlet to a URL -->
    <servlet-mapping>
        <servlet-name>ArtistServlet</servlet-name>
        <url-pattern>/artist</url-pattern>
    </servlet-mapping>

    <!-- Session Timeout Configuration (in minutes) -->
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>
</web-app>
```

> [!TIP]
> If a Servlet uses `@WebServlet("/artist")`, the `web.xml` declaration is optional. However, `web.xml` can override annotations if the `metadata-complete` attribute is set to `true`.

---

## 3. Environment & Dependencies (Tomcat, Jakarta, MSSQL)

Based on the course environment, the following tech stack is used. FE exams often ask about the versions and dependencies.

- **Web Server:** Apache Tomcat 10.x+
- **Java Version:** JDK 11 / JDK 21
- **API Spec:** Jakarta EE 10 (`jakarta.*` namespace replaces `javax.*` in newer versions, though `javax` is still used in older projects).

### Maven `pom.xml` Dependencies
The `pom.xml` defines external libraries downloaded automatically. For PRJ301, the crucial ones are:

1. **Jakarta EE API** (for Servlets, Filters):
   ```xml
   <dependency>
       <groupId>jakarta.platform</groupId>
       <artifactId>jakarta.jakartaee-api</artifactId>
       <version>10.0.0</version>
       <scope>provided</scope>
   </dependency>
   ```
   *(Note: `scope` is `provided` because Tomcat already provides these classes).*

2. **Microsoft SQL Server JDBC Driver**:
   ```xml
   <dependency>
       <groupId>com.microsoft.sqlserver</groupId>
       <artifactId>mssql-jdbc</artifactId>
       <version>12.8.0.jre11</version>
   </dependency>
   ```
   *Driver Class:* `com.microsoft.sqlserver.jdbc.SQLServerDriver`
   *JDBC URL:* `jdbc:sqlserver://localhost:1433;databaseName=YourDBName`

3. **JSTL (JavaServer Pages Standard Tag Library)**:
   ```xml
   <dependency>
       <groupId>jakarta.servlet.jsp.jstl</groupId>
       <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
       <version>3.0.2</version>
   </dependency>
   ```

---

## 4. Exam Folder Structure (PE Context for FE theory)

While PE (Practical Exam) is coding-focused, FE might ask about packaging.
- An exam project is often compressed into a `.zip` or `.rar`.
- When exporting a project, the `.war` (Web Application Archive) file is deployed to Tomcat's `webapps` directory.
- `WAR` files contain everything needed to run the web application (classes, jsp, html, web.xml).

---

## 5. Quick Review Questions for FE

1. **Where should a `web.xml` file be placed in a Java Web App?**
   - **Answer:** `/WEB-INF/web.xml`
2. **Which XML file manages dependencies in a Maven project?**
   - **Answer:** `pom.xml`
3. **Can a user access a JSP file placed inside `WEB-INF` by typing its URL in the browser?**
   - **Answer:** No, it is secure and hidden from direct HTTP requests.
4. **What is the default port for Tomcat and SQL Server?**
   - **Answer:** Tomcat is `8080`, SQL Server is `1433`.
5. **What scope is used in Maven for Servlet API since Tomcat provides it at runtime?**
   - **Answer:** `<scope>provided</scope>`
