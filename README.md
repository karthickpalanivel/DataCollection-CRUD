# DataCollection-CRUD
NOTE: This project is entirely for beginners to understand the CRUD concept in java AWT


This is a mini-app that can be used for collecting data as like forms. If you wanna upgrade a bit in this, of course you can make pull request to this project.
This application is created for beginners to understand the basic concept of CRUD

C - create
R - Ready
U - Update
D - Delete 
A tradiitional method of UI is created using Java AWT (Abstract windows ToolKit) and using JDBC (Java Database Connectivity). It is an application programming interface (API) that allows Java applications to interact with different databases.

Requirements needed: Java (Above version 8), Mysql Jar file driver and Mysql connector

Java link: https://javadl.oracle.com/webapps/download/AutoDL?BundleId=249185_b291ca3e0c8548b5a51d5a5f50063037

MySql Connection: https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.2.0.tar.gz

click the above links or paste the above links in any browsers to download.


Step - 1 - Download or clone this repository to your local system
Step - 2 - new Project in eclipse IDE and extract these files in that project
Step - 3 - Create a table in mySql for the following data collection or copy paste the below code

--------------------------------------------------------
create database <tablename>;
use <tablename>;
create table users(
  ID int primary key auto_increment,
  NAME varchar(50),
  PHONE int(10),
  CITY varchar(50),
  AGE int(2),
  EMAIL varchar(30)
);
select * from users;
insert into users (NAME, PHONE, CITY, AGE, EMAIL) values
--------------------------------------------------------  

step - 4 - in user.java file under src directory edit your table name in line number 28 or for String URL instead of <tablename> you can insert your table name for connection. 

Use Java Eclipse for run the project. You need not to copy the mysql file, instead I already added in your mysql directory.

For any queries, Feel free to contact.
