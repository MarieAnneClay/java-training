package util;

import java.util.ArrayList;

import model.Computer;
import service.ServiceComputer;

public class Page {
    private int computerTotalPages = 0;
    private int numberOfComputerByPage = 10;
    private int computerPage = 1;
    private int end = 1;
    private String search = "";

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getComputerPage() {
        return computerPage;
    }

    public void setComputerPage(int computerPage) {
        this.computerPage = computerPage;
    }

    public int getNumberOfComputerByPage() {
        return numberOfComputerByPage;
    }

    public void setNumberOfComputerByPage(int numberOfComputerByPage) {
        this.computerPage = 1;
        this.numberOfComputerByPage = numberOfComputerByPage;
    }

    public int getComputerTotalPages() {
        return computerTotalPages;
    }

    public void setComputerTotalPages(int computerTotalPages) {
        this.computerTotalPages = computerTotalPages;
    }

    public int getBegin() {
        return computerPage > 0 ? computerPage : 1;
    }

    public int getEnd() {
        int nbPage = numberOfComputerByPage < computerTotalPages ? computerTotalPages / numberOfComputerByPage
                : numberOfComputerByPage / computerTotalPages;
        this.end = nbPage < computerTotalPages + 1 ? nbPage : computerTotalPages;
        return end;
    }

    /**
     * CONSTRUCTOR.
     * 
     * @param computers
     *            ArrayList of the computers
     * @param companies
     *            ArrayList of the companies
     */
    public Page(ArrayList<Computer> computers) {
        this.computerTotalPages = 1 + computers.size() / numberOfComputerByPage;
        this.end = computerTotalPages;
    }

    /**
     * LIST COMPUTER.
     * 
     * @param computers
     *            ArrayList of the computers
     * @param serviceComputer
     *            Service of the computers
     */
    public ArrayList<Computer> getComputerSubest(ArrayList<Computer> computers, ServiceComputer serviceComputer) {
        computerTotalPages = 1 + computers.size() / numberOfComputerByPage;
        return serviceComputer.getComputerSubest((computerPage - 1) * numberOfComputerByPage, numberOfComputerByPage,
                computers);
    }

}
