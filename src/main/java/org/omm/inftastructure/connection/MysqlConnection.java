package org.omm.inftastructure.connection;
import com.sun.source.tree.SynchronizedTree;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection extends ConnectionFactory{
      private volatile static Connection connection = null;
    ConnectionFactory myFactory;
    @Override
    public Connection createConnection(){

        try {
            if(connection == null){
                synchronized(MysqlConnection.class){
                    if (connection == null){
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection("jdbc:MySql://localhost:3306/mysql",myFactory.getUsername(),myFactory.getPassword());
                    }
                }
            }

        }catch (Exception e){
            System.out.print(e);
        }

        return connection;
    }
}
