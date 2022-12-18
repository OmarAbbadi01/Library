package org.omm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookTable extends JFrame {
    private final String[] columnNames = {"ID", "Author ID", "Title"};
    private JTable bookTable;
    private DefaultTableModel bookModel;

    public BookTable() {
        setTitle("Book Table");
        setSize(400, 300);

        String[][] data = {
                {"123", "456", "The Great Gatsby"},
                {"789", "012", "To Kill a Mockingbird"},
                {"345", "678", "1984"}
        };
        bookModel = new DefaultTableModel(data, columnNames);
        bookTable = new JTable(bookModel);

        add(new JScrollPane(bookTable));

        setVisible(true);
    }

    // called from notify
    void updateData() {
        // call find All
        // update view
        String[][] data = {
                {"14645654", "4565435345", "Th345345345e Great Gatsby"},
                {"7353589", "0134534532", "To Kill a Mockingbird3453453453"},
                {"345345345435", "63453453578", "34534531984"}
        };
        bookModel = new DefaultTableModel(data, columnNames);
        bookTable = new JTable(bookModel);

        add(new JScrollPane(bookTable));

        setVisible(true);
    }

}
