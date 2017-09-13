package dao;

import java.util.LinkedList;
import java.util.Vector;

import model.Computer;

public interface ComputerDAO {
    void creer(Computer computer);
    LinkedList<Computer> trouver(String sql, Object... objets);
    void modifier(Computer computer);
    void supprimer(Computer computer);
}
