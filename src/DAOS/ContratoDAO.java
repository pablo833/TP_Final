package DAOS;

import ENTIDADES.Contrato;
import EXCEPTIONS.RadioException;

import java.util.List;

public interface ContratoDAO {
    List<Contrato> getByPrograma(int programa) throws RadioException;
}
