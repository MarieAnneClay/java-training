package controller;

import java.sql.SQLException;

import service.ServiceCompany;

public class Main {

    private static ServiceCompany serviceCompany = ServiceCompany.getInstance();

    public static void main(String[] args) throws SQLException {
        serviceCompany.deleteCompany(42);

    }

}
