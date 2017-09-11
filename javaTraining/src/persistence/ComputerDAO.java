package persistence;

import java.util.Vector;

import model.Computer;

public interface ComputerDAO {
    void creer(Computer computer);
    Vector<Computer> trouver(String sql, Object... objets);
}
