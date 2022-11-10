Topic Index
=============


PL/SQL
------
Procedures
	- Anonymous block
	- stored procedures 

Parameters in procudures
	- IN 
	- OUT
	- INOUT 

Exceptions in procedures
	- In-Built
	- Custom
	[Raise an exception]
	[handle exception in exception block]


DECLARE - in Anonymous
BEGIN
EXCEPTION
END

FUNCTIONS
	- function has a return statement

NOTE: function returns the value and procedure does not. 

TRIGGERS
--------
Operations: INSERT / UPDATE / DELETE
time: BEFORE / AFTER 

- Triggers are pl/sql block on code that gets executed if the defined conditions are met. 

CURSORS
-------
	- Implicit: %ROW_TYPE%, NOT_FOUND, FOUND, isOPEN, TYPE
	- explicit: to iterate over the resultset that has multiple rows. 

[google MCQs in PL/SQL]. 


CORE JAVA
=========
Access Specifiers of class and methods
---------------------------------------
	- class: public and default 
	- method: public, protected, default, private

Classification of variables
---------------------------
	- instance
	- local

Heap and Stack
--------------
	- Objects go in Heap
	- References go on stack 

Inheritance
-----------
	- Single, Muti-level 
		[Multiple inheritance is not allowed in java]
	- overriding, overloading, polymorphism 

Keywords
--------
	- static
	- final
	- abstract 

Interfacing
-----------
	- Features from Java 8: default methods
	- functional interface

Strings and Arrays
------------------
	- String, StringBuilder, StringBuffer 
	- Arrays: sort, binarySearch

Collections & Generics
----------------------
	- List: ArrayList, Vector, LinkedList 
	- Map: HashMap, Hashtable, LinkedHashMap
	- Set: TreeSet, HashSet 
	- Comparator Interface 

Lambda Expressions & streams
----------------------------
	- streams: filter, foreach, map 

Threads and Parellel Programming
--------------------------------
	- Multi-Threading, Future, parellel streams

JDBC API
--------
	- Connection, DriverManager, Class.forName
	- ResultSet 
	- PreparedStatement: executeQuery & executeUpdate

Junit
-----
	- assertEquals, assertArrayEquals
	- @Test, @Before, @After, @BeforeClass, @AfterClass, Test Suite, Test Runner

Hibernate/Jpa
-------------
	- What is ORM, what is Hibernate, What is JPA? 
	- Role of Hibernate and JPA
	- What is EntiryManagerFactory, EntityManager, EntityTransaction, Persistence, SessionFactory
	- How have you configured Hibernate? 
	- Difference between Criteria Query, HQL and JPQL. 
	- Advantage of JPQL over Native Query. 
	- What method of EntityManager have you used in your project
 

Spring CORE & MVC
------------------
	- ApplicationContext
	- ClassPathXMLApplicationContext 
	- Creating a Bean in Spring? 
	- Scope of Bean? singleton & prototype & request & session 
	- Autowiring and ways to do it?
	- IOC in Spring 
	- DI and its advantages 
	- Model,ModelAndView,HttpServletRequest
	- @controller,@service,@component,@Bean,@configuration,@componentscan
	- Spring JDBC: JdbcTemplate classes? which one have you used? RowMapper?, what methods have you used? 
	- Configuration can be done using XML & Java. YOur Opinion on them? which one did you use for your
		project? 













