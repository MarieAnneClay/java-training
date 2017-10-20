package core.java.util;

import java.util.logging.Logger;

public class Page {
    private static Logger LOGGER = Logger.getLogger(Page.class.getName());
    private static String orderName;
    private static String orderIntroduced;
    private static String orderDiscontinued;
    private static String orderCompany;

    static {
        orderName = "ASC";
        orderIntroduced = "ASC";
        orderDiscontinued = "ASC";
        orderCompany = "ASC";
    }

    public static String getOrder(String sort) {
        switch (sort) {
        case "name":
            orderName = reverse(orderName);
            return orderName;

        case "introduced":
            orderIntroduced = reverse(orderIntroduced);
            return orderIntroduced;

        case "discontinued":
            orderDiscontinued = reverse(orderDiscontinued);
            return orderDiscontinued;

        case "company.name":
            orderCompany = reverse(orderCompany);
            return orderCompany;

        default:
            orderName = reverse(orderName);
            return orderName;

        }
    }

    public static String reverse(String order) {
        if (order.equals("DESC")) {
            order = "ASC";
        } else {
            order = "DESC";
        }
        return order;
    }

}
