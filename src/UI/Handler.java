package UI;

import java.util.List;

import BOS.AuspicianteBO;
import BOS.UsuarioBO;
import DAOS.impl.AuspicianteDaoImpl;
import DAOS.impl.UsuarioDaoImpl;
import UI.AUSPICIANTE.AuspiciantePanel;
import UI.AUSPICIANTE.ListAuspiciantePane;
import UI.USER.ListUserPanel;
import UI.USER.UserPanel;
import ENTIDADES.Auspiciante;
import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;

public class Handler {
    private MainContianerFrame containerFrame;
    private UsuarioBO usuarioBO;
    private AuspicianteBO auspicianteBO;
    private final String USUARIO_CONTRASENIA_INEXISTENTE = "Usuario o contrasia invalidos";
    private final String OPERACION_EXITOSA = "Operación exitos";

    public Handler() {
        this.usuarioBO = new UsuarioBO();
        this.usuarioBO.setDao(new UsuarioDaoImpl());
        this.auspicianteBO = new AuspicianteBO();
        this.auspicianteBO.setDao(new AuspicianteDaoImpl());
    }

    public void initApp() {
        containerFrame = new MainContianerFrame("Radio App", this);
        containerFrame.setVisible(true);
    }

    public void login(Usuario user) throws RadioException {
        Usuario userFound = null;

        try {
            userFound = usuarioBO.getByUserName(user);
            if (userFound == null) {
                CustomOptionPane.showErrorMessage(USUARIO_CONTRASENIA_INEXISTENTE);
            } else {
                containerFrame.removePanel();
                containerFrame.setMenuBarVisible();
            }
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void showLogin() {

        containerFrame.changePanel(new Login(this));
    }

    public void addCreateUserPane() {

        containerFrame.changePanel(UserPanel.create(this, UserPanel.PanelMode.CREATE));
    }

    public void addCreateUpdateUserPane() {
        containerFrame.changePanel(UserPanel.create(this, UserPanel.PanelMode.UPDATE));
    }

    public void addCreateDeleteUserPane() {
        containerFrame.changePanel(UserPanel.create(this, UserPanel.PanelMode.DELETE));
    }

    public void addListUsersPane() {
        containerFrame.changePanel(new ListUserPanel(this));

    }

    public void createUser(Usuario user) throws RadioException {

        try {
            usuarioBO.create(user);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        } catch (Exception e1) {
            CustomOptionPane.showErrorMessage(e1.getMessage());
        }
    }

    public void updateUser(Usuario user) throws RadioException {

        try {
            usuarioBO.update(user);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        } catch (Exception e1) {
            CustomOptionPane.showErrorMessage(e1.getMessage());
        }
    }

    public void deleteUser(Usuario user) throws RadioException {

        try {
            usuarioBO.delete(user);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        } catch (Exception e1) {
            CustomOptionPane.showErrorMessage(e1.getMessage());
        }
    }

    public Usuario getUser(String userName) throws RadioException {
        Usuario user = new Usuario(userName, null, null, null);
        try {
            user = usuarioBO.getByUserName(user);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return user;
    }

    public Usuario getUser(Usuario usuario) throws RadioException {
        Usuario userFound = null;

        try {
            userFound = usuarioBO.getByUserName(usuario);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return userFound;

    }

    public List<Usuario> GetUsuarios() throws RadioException {
        return usuarioBO.getAll();
    }

    public void addCreateAuspiciantePane() {
        containerFrame.changePanel(AuspiciantePanel.create(this, AuspiciantePanel.PanelMode.CREATE));
    }

    public void addUpdateAuspiciantePane() {
        containerFrame.changePanel(AuspiciantePanel.create(this, AuspiciantePanel.PanelMode.UPDATE));
    }

    public void addDeleteAuspiciantePane() {
        containerFrame.changePanel(AuspiciantePanel.create(this, AuspiciantePanel.PanelMode.DELETE));
    }

    public void addListAuspiciantePane() {

        containerFrame.changePanel(new ListAuspiciantePane(this));
    }

    public Auspiciante getAuspiciante(Auspiciante auspiciante) throws RadioException {
        Auspiciante auspicianteFound = null;

        try {
            auspicianteFound = auspicianteBO.getByRazonSocial(auspiciante);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return auspicianteFound;

    }

    public void createAuspiciante(Auspiciante auspiciante) throws RadioException {

        try {
            auspicianteBO.create(auspiciante);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        } catch (Exception e1) {
            CustomOptionPane.showErrorMessage(e1.getMessage());
        }
    }

    public void updateAuspiciante(Auspiciante auspiciante) throws RadioException {

        try {
            auspicianteBO.update(auspiciante);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        } catch (Exception e1) {
            CustomOptionPane.showErrorMessage(e1.getMessage());
        }
    }

    public List<Auspiciante> GetAuspiciantes() throws RadioException {
        return auspicianteBO.getAll();
    }

}
