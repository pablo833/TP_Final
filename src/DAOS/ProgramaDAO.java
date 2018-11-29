package DAOS;

import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;

public interface ProgramaDAO {
    Programa getByProductor(int codigo) throws RadioException;

    Programa getByConductor(int codigo) throws RadioException;
}
