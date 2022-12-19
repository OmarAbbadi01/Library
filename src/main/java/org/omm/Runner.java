package org.omm;

import lombok.extern.java.Log;
import org.omm.application.BookController;
import org.omm.domain.model.BookDto;

import java.util.Scanner;

@Log
public class Runner {

    private Scanner in;
    private BookController controller;

    public void run() {
        SetUp setup = new SetUp();
        controller = setup.setUpController();
        in = new Scanner(System.in);
        int input = 1;
        while (input > 0) {
            intro();
            input = in.nextInt();
            try {
                switch (input) {
                    case 1:
                        retrieveById();
                        break;
                    case 2:
                        retrieveAll();
                        break;
                    case 3:
                        create();
                        break;
                    case 4:
                        delete();
                        break;
                    case 5:
                        update();
                        break;
                }
            } catch (Exception e) {
                log.info("" + e);
            }
        }
    }

    private void intro() {
        System.out.println("\nChoose Operation, Enter a Number to Pursuit" +
                "\n1: to retrieve a book by ID" +
                "\n2: to retrieve all books" +
                "\n3: to add a book" +
                "\n4: to delete a book" +
                "\n5: to update a book" +
                "\nnegative number: to exit");
    }

    private void retrieveById() throws Exception {
        System.out.print("Enter ID: ");
        Long id = in.nextLong();
        System.out.println(controller.findById(id));
    }

    private void retrieveAll() throws Exception {
        System.out.println(controller.findAll());
    }

    private void create() throws Exception {
        System.out.print("Enter ID: ");
        Long id = in.nextLong();
        System.out.print("Enter Author ID: ");
        Long authorId = in.nextLong();
        in.nextLine();
        System.out.print("Enter Title: ");
        String title = in.nextLine();
        BookDto bookDto = new BookDto(id, authorId, title);
        System.out.println(controller.create(bookDto));
    }

    private void delete() throws Exception {
        System.out.println("Enter ID: ");
        Long id = in.nextLong();
        System.out.println(controller.delete(id));
    }

    private void update() throws Exception {
        System.out.print("Enter ID: ");
        Long id = in.nextLong();
        System.out.print("Enter Author ID: ");
        Long authorId = in.nextLong();
        in.nextLine();
        System.out.print("Enter Title: ");
        String title = in.nextLine();
        BookDto bookDto = new BookDto(id, authorId, title);
        System.out.println(controller.update(bookDto));
    }

}