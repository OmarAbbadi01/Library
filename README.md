# library
This project is to CRUD books into database, it simulates the hexagonal (ports and adatpters) architecture, 
it uses Factory Method, Singleton, Adpater, and Observer patterns.

To run the application you need first to setup the database(s) first, first create a PostgreSql database with a database user and password,
then, create a MySql database with a database user and password, after that, go to the Configuration class (application -> config -> Configuration.java)
and set the list of credentials as you've set them up.
Run the Main class and have a look on the GUI dashboard that pops up, use the console to try the CRUD operations and watch how the GUI dashboard keeps updating on each update.
