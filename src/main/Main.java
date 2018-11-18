package main;

import UI.Handler;
import DB.DBManager;

public class Main {

    public static void main(String[] args) {


        //DBManager.createDB();
        // DBManager.borrarTabla();

        new Handler().initApp();
    }
}
