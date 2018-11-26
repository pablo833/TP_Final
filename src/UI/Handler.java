package UI;

import BOS.*;
import DAOS.impl.*;
import ENTIDADES.*;
import EXCEPTIONS.RadioException;
import UI.AUSPICIANTE.AuspiciantePanel;
import UI.AUSPICIANTE.ListAuspiciantePane;
import UI.CONDUCTOR.ConductorPanel;
import UI.CONDUCTOR.ListConductoresPanel;
import UI.PRODUCTOR.ListProductoresPanel;
import UI.PRODUCTOR.ProductorPanel;
import UI.PROGRAMA.ListProgramasPanel;
import UI.PROGRAMA.ProgramaAuspiciantePanel;
import UI.PROGRAMA.ProgramaPanel;
import UI.USER.ListUserPanel;
import UI.USER.UserPanel;

import java.util.List;
import java.util.Vector;

public class Handler {
    private MainContianerFrame containerFrame;
    private UsuarioBO usuarioBO;
    private AuspicianteBO auspicianteBO;
    private ConductorBO conductorBO;
    private ProductorBO productorBO;
    private ProgramaBO programaBO;
    private ContratoBO contratoBO;
    private final String USUARIO_CONTRASENIA_INEXISTENTE = "Usuario o contraseña invalidos";
    private final String OPERACION_EXITOSA = "Operación exitosa";

    public Handler() {
        this.usuarioBO = new UsuarioBO();
        this.usuarioBO.setDao(new UsuarioDaoImpl());
        this.auspicianteBO = new AuspicianteBO();
        this.auspicianteBO.setDao(new AuspicianteDaoImpl());
        this.conductorBO = new ConductorBO();
        this.conductorBO.setDao(new ConductorDAOImpl());
        this.productorBO = new ProductorBO();
        this.productorBO.setDao(new ProductorDAOImpl());
        this.programaBO = new ProgramaBO();
        this.programaBO.setDao(new ProgramaDAOImpl());
        this.contratoBO = new ContratoBO();
        this.contratoBO.setDao(new ContratoDAOImpl());
    }

    public void initApp() {
        containerFrame = new MainContianerFrame("Radio App", this);
        containerFrame.setVisible(true);
    }

    public void login(Usuario user) throws RadioException {

        try {
            if (usuarioBO.getByUserName(user) == null) {
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

    //
    //USUARIOS
    //
    public void addCreateUserPane() {

        containerFrame.changePanel(UserPanel.create(this, basePanel.PanelMode.CREATE));
    }

    public void addCreateUpdateUserPane() {
        containerFrame.changePanel(UserPanel.create(this, basePanel.PanelMode.UPDATE));
    }

    public void addCreateDeleteUserPane() {
        containerFrame.changePanel(UserPanel.create(this, basePanel.PanelMode.DELETE));
    }

    public void addListUsersPane() {
        containerFrame.changePanel(new ListUserPanel(this));

    }

    //
    //AUSPICIANTES
    //
    public void addCreateAuspiciantePane() {
        containerFrame.changePanel(AuspiciantePanel.create(this, basePanel.PanelMode.CREATE));
    }

    public void addUpdateAuspiciantePane() {
        containerFrame.changePanel(AuspiciantePanel.create(this, basePanel.PanelMode.UPDATE));
    }

    public void addDeleteAuspiciantePane() {
        containerFrame.changePanel(AuspiciantePanel.create(this, basePanel.PanelMode.DELETE));
    }

    public void addListAuspiciantePane() {

        containerFrame.changePanel(new ListAuspiciantePane(this));
    }

    //
    //CONDUCTORES
    //
    public void addCreateConductorPane() {
        containerFrame.changePanel(ConductorPanel.create(this, basePanel.PanelMode.CREATE));
    }

    public void addUpdateConductorPane() {
        containerFrame.changePanel(ConductorPanel.create(this, basePanel.PanelMode.UPDATE));
    }

    public void addDeleteConductorPane() {
        containerFrame.changePanel(ConductorPanel.create(this, basePanel.PanelMode.DELETE));
    }

    public void addListConductoresPane() {

        containerFrame.changePanel(new ListConductoresPanel(this));
    }

    //
    //PRODUCTORES
    //
    public void addCreateProductorPane() {
        containerFrame.changePanel(ProductorPanel.create(this, basePanel.PanelMode.CREATE));
    }

    public void addUpdateProductorPane() {
        containerFrame.changePanel(ProductorPanel.create(this, basePanel.PanelMode.UPDATE));
    }

    public void addDeleteProductorPane() {
        containerFrame.changePanel(ProductorPanel.create(this, basePanel.PanelMode.DELETE));
    }

    public void addListProductoresPane() {

        containerFrame.changePanel(new ListProductoresPanel(this));
    }

    //
    //PROGRAMAS
    //
    public void addCreateProgramaPane() {
        containerFrame.changePanel(ProgramaPanel.create(this, basePanel.PanelMode.CREATE));
    }

    public void addCreateUpdateProgramaPane() {
        containerFrame.changePanel(ProgramaPanel.create(this, basePanel.PanelMode.UPDATE));
    }

    public void addCreateDeleteProgramaPane() {
        containerFrame.changePanel(ProgramaPanel.create(this, basePanel.PanelMode.DELETE));
    }

    public void addListProgrmasPane() {
        containerFrame.changePanel(new ListProgramasPanel(this));
    }

    public void addProgramaAuspiciantePanel() {
        containerFrame.changePanel(new ProgramaAuspiciantePanel(this));
    }

    //USUARIOS
    public void createUser(Usuario user) throws RadioException {

        try {
            usuarioBO.create(user);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void updateUser(Usuario user) throws RadioException {

        try {
            usuarioBO.update(user);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void deleteUser(Usuario user) throws RadioException {

        try {
            usuarioBO.delete(user);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
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

    public Usuario getUser(Usuario usuario) {
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

    //AUSPICIANTES
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
        }
    }

    public void updateAuspiciante(Auspiciante auspiciante) throws RadioException {

        try {
            auspicianteBO.update(auspiciante);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public List<Auspiciante> GetAuspiciantes() {
        List<Auspiciante> auspiciantes = null;
        try {
            auspiciantes = auspicianteBO.getAll();
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return auspiciantes;
    }

    //CONDUCTORES
    public Conductor getConductor(int DNI) throws RadioException {
        Conductor conductor = new Conductor(null, null, DNI, 0);
        try {
            conductor = conductorBO.getByDNI(conductor);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return conductor;
    }

    public void createCondutor(Conductor conductor) throws RadioException {
        try {
            conductorBO.create(conductor);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void updateConductor(Conductor conductor) throws RadioException {
        try {
            conductorBO.update(conductor);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void deleteConductor(Conductor conductor) throws RadioException {
        try {
            conductorBO.delete(conductor);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public List<Conductor> getConductores() throws RadioException {
        return conductorBO.getAll();
    }

    public Vector getConductoresVector() {
        Vector model = new Vector();
        try {
            List<Conductor> conductores = conductorBO.getAll();
            for (Conductor c : conductores) {
                model.addElement(c);
            }
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return model;
    }

    //PRODUCTORES
    public Productor getProductor(Integer dni) {
        Productor productor = new Productor(null, null, dni);
        try {
            productor = productorBO.getByDNI(productor);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return productor;
    }

    public void createProductor(Productor productor) throws RadioException {
        try {
            productorBO.create(productor);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void deleteProductor(Productor productor) throws RadioException {
        try {
            productorBO.delete(productor);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void updateProductor(Productor productor) throws RadioException {
        try {
            productorBO.update(productor);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public List<Productor> getProductores() throws RadioException {
        return productorBO.getAll();
    }

    public Vector getProductoresVector() {
        Vector model = new Vector();
        try {
            List<Productor> productores = productorBO.getAll();
            for (Productor p : productores) {
                model.addElement(p);
            }

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return model;
    }

    //PROGRAMAS
    public void createPrograma(Programa programa) throws RadioException {
        try {
            programaBO.create(programa);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public void updatePrograma(Programa programa) throws RadioException {
        try {
            programaBO.update(programa);
            CustomOptionPane.showInformationMessage(OPERACION_EXITOSA);

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    public Programa getPrograma(String nombre) throws RadioException {
        Programa programa = new Programa(nombre, null, null, null, null);
        try {
            programa = programaBO.getByNombre(programa);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
        return programa;
    }

    public void deletePrograma(Programa programa) throws RadioException {
    }

    public List<Programa> getProgramas() throws RadioException {
        return programaBO.getAll();
    }

    public Vector getAuspicianteVector() throws RadioException {
        Vector model = new Vector();

        List<Auspiciante> auspiciantes = auspicianteBO.getAll();
        for (Auspiciante a : auspiciantes) {
            model.addElement(a);
        }

        return model;
    }

    //CONTRATOS
    public void createContrato(Contrato contrato) throws RadioException {

        contratoBO.create(contrato);
    }

    public Vector getProgramasVector() throws RadioException {
        Vector model = new Vector();

        List<Programa> programas = programaBO.getAll();

        for (Programa p : programas) {
            model.addElement(p);
        }

        return model;
    }

    public List<Contrato> GetContratos(Programa programa) throws RadioException {

        return contratoBO.getByPrograma(programa);
    }

    public void deleteContract(Contrato contrato) {
        try {
            contratoBO.delete(contrato);
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }
}
