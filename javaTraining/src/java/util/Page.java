package util;

import java.util.logging.Logger;

public class Page {
    private static Logger LOGGER = Logger.getLogger(Page.class.getName());
    private String search;
    private int numberOfComputerByPage;
    private int currentPage;
    private String sort;
    private String order;
    private static String orderName;
    private static String orderIntroduced;
    private static String orderDiscontinued;
    private static String orderCompany;

    static {
        orderName = "DESC";
        orderIntroduced = "DESC";
        orderDiscontinued = "DESC";
        orderCompany = "DESC";
    }

    public Page(String search, int numberOfComputerByPage, int currentPage, String sort, String order) {
        super();
        this.search = search;
        this.numberOfComputerByPage = numberOfComputerByPage;
        this.currentPage = currentPage;
        this.sort = sort;
        this.order = getOrder(sort, order);
    }

    public String getOrder(String sort, String order) {
        switch (sort) {
        case "cr.name":
            return reverse(order);

        case "introduced":
            return reverse(order);

        case "discontinued":
            return reverse(order);

        case "cy.name":
            return reverse(order);
        default:
            return reverse(order);

        }
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getNumberOfComputerByPage() {
        return numberOfComputerByPage;
    }

    public void setNumberOfComputerByPage(int numberOfComputerByPage) {
        this.numberOfComputerByPage = numberOfComputerByPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderIntroduced() {
        return orderIntroduced;
    }

    public String getOrderDiscontinued() {
        return orderDiscontinued;
    }

    public String getOrderCompany() {
        return orderCompany;
    }

    public String getSort(String sort) {
        switch (sort) {
        case "cr.name":
            return "orderName";

        case "introduced":
            return "orderIntroduced";

        case "discontinued":
            return "orderDiscontinued";

        case "cy.name":
            return "orderCompany";

        default:
            return "orderName";

        }
    }

    public String reverse(String order) {
        if (order.equals("DESC")) {
            order = "ASC";
        } else {
            order = "DESC";
        }
        return order;
    }

}
