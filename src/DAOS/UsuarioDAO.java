package DAOS;

import java.util.List;

import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;

public interface UsuarioDAO {

	public void insert(Usuario usuario) throws RadioException;

	public void update(Usuario usuario) throws RadioException;

	public Usuario get(int codigo) throws RadioException;

	public void delete(int codigo) throws RadioException;

	public void delete(String userName) throws RadioException;

	public List<Usuario> getAll() throws RadioException;

	public Usuario getByUserName(String userName) throws RadioException;
}
