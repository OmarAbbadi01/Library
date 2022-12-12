package org.omm;

import org.omm.application.BookController;

public class Main {
    public static void main(String[] args) throws Exception {
        SetUp setup = new SetUp();
        BookController controller = setup.setUpController();
        System.out.println(controller.findById(1L));
    }

}
