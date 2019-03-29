# snack-bar

This project was generated with SpringBoot version 2.3.1, through Spring Initializr.
All of its development was made through NetBeans IDE version 8.2.
It was defined to be a Maven project.

The database utilized was PostgreSQL, using the database management tool pgAdmin 4.

## Dependencies Used

The following dependencies were used during the project generation:
- Web;
- JPA;
- PostgreSQL;

Additionaly, the following dependency was added in the middle of development:
- Lombok.

## Setup

This project uses a postgreSQL database (Download: https://www.postgresql.org/download/). It will be necessary to allow TCP access on port 5432, as well defining a postgres access password. On Windows, downloading the installer certified by EnterpriseDB will help with these configurations. Make sure to opt for pgAdmin 4 during installation. After the installation is completed, run pgAdmin 4 and connect to the server. After that, create a database named 'snack-bar'.

It will be necessary to properly fill the datasource's credentials. This can be done at application.properties file.

## Building and Running

To properly run this project, it's necessary to install its maven dependencies. On NetBeans, after opening the project, left-click on the project root directory and choose "clear and build".  After that, just run its main application SnackBarApplication.java.

## More Info

For more information about this project, please read the snack-bar doc.pdf file.
