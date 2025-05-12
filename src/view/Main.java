package view;

import controller.StoreController;

public class Main {
    public static void main(String[] args) {
        StoreController controller = new StoreController();
        new LoginView(controller); // O la primera ventana que uses
    }
}
