package org.omm;

import org.omm.domain.model.BookDto;
import org.omm.domain.service.BookService;
import org.omm.domain.service.Observer;
import org.omm.domain.service.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Dashboard extends JFrame implements Observer {
    private final String[] columnNames = {"ID", "Author ID", "Title"};
    private JTable bookTable;
    private DefaultTableModel bookModel;
    private Subject subject;
    private BookService bookService;

    public Dashboard(Subject subject, BookService bookService) throws Exception {
        this.subject = subject;
        this.bookService = bookService;
        this.subject.register(this);
        setTitle("Book Table");
        setSize(400, 300);

        String[][] data = toMap(bookService.findAll());
        bookModel = new DefaultTableModel(data, columnNames);
        bookTable = new JTable(bookModel);
        add(new JScrollPane(bookTable));
        setVisible(true);
    }

    @Override
    public void updateData() throws Exception {
        String[][] data = toMap(bookService.findAll());
        bookModel = new DefaultTableModel(data, columnNames);
        bookTable = new JTable(bookModel);
        add(new JScrollPane(bookTable));
        setVisible(true);
    }

    private String[][] toMap(List<BookDto> bookDtos) {
        String[][] data = new String[bookDtos.size()][3];
        for (int i = 0; i < bookDtos.size(); i++) {
            data[i][0] = bookDtos.get(i).getId().toString();
            data[i][1] = bookDtos.get(i).getAuthorId().toString();
            data[i][2] = bookDtos.get(i).getTitle();
        }
        return data;
    }
}
