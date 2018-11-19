package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainContianerFrame extends JFrame {

    private Handler handler;
    private JMenuBar menuBar;

    public MainContianerFrame(String titulo, Handler handler) {
        super(titulo);
        this.handler = handler;
        initUI();
    }

    private void initUI() {
        showLogin();
        addMenubar();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar.setVisible(false);
    }

    public void setMenuBarVisible() {
        menuBar.setVisible(true);

    }

    private void showLogin() {
        // handler.showLogin();
        Login li = new Login(handler);
        changePanel(li);
    }

    private void addMenubar() {

        JMenu userMenu = generateUserMenu();
        JMenu auspicianteMenu = generateAuspicianteMenu();
        JMenu conductorMenu = generateConductorMenu();
        JMenu productorMenu = generateProductorMenu();

        menuBar = new JMenuBar();
        menuBar.add(userMenu);
        menuBar.add(auspicianteMenu);
        menuBar.add(conductorMenu);
        menuBar.add(productorMenu);

        setJMenuBar(menuBar);
    }

    private JMenu generateUserMenu() {

        JMenu menuUsuario = new JMenu("Usuarios");
        JMenuItem menuItemAlta = new JMenuItem("Alta");

        menuItemAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addCreateUserPane();
            }
        });

        JMenuItem menuItemModificacion = new JMenuItem("Modificación");
        menuItemModificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addCreateUpdateUserPane();
            }
        });

        JMenuItem menuItemBaja = new JMenuItem("Baja");
        menuItemBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addCreateDeleteUserPane();
            }
        });

        JMenuItem menuItemListar = new JMenuItem("Listar");
        menuItemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                handler.addListUsersPane();
            }
        });

        menuUsuario.add(menuItemAlta);
        menuUsuario.add(menuItemModificacion);
        menuUsuario.add(menuItemBaja);
        menuUsuario.add(menuItemListar);

        return menuUsuario;
    }

    private JMenu generateAuspicianteMenu() {
        JMenu menuUsuario = new JMenu("Auspiciantes");
        JMenuItem menuItemAlta = new JMenuItem("Alta");

        menuItemAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addCreateAuspiciantePane();
            }
        });

        JMenuItem menuItemModificacion = new JMenuItem("Modificación");
        menuItemModificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addUpdateAuspiciantePane();
            }
        });

        JMenuItem menuItemBaja = new JMenuItem("Baja");
        menuItemBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addDeleteAuspiciantePane();
            }
        });

        JMenuItem menuItemListar = new JMenuItem("Listar");
        menuItemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addListAuspiciantePane();
            }
        });

        menuUsuario.add(menuItemAlta);
        menuUsuario.add(menuItemModificacion);
        menuUsuario.add(menuItemBaja);
        menuUsuario.add(menuItemListar);

        return menuUsuario;
    }

    private JMenu generateConductorMenu() {
        JMenu menuConductores = new JMenu("Conductores");
        JMenuItem menuItemAlta = new JMenuItem("Alta");

        menuItemAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addCreateConductorPane();
            }
        });

        JMenuItem menuItemModificacion = new JMenuItem("Modificación");
        menuItemModificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addUpdateConductorPane();
            }
        });

        JMenuItem menuItemBaja = new JMenuItem("Baja");
        menuItemBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addDeleteConductorPane();
            }
        });

        JMenuItem menuItemListar = new JMenuItem("Listar");
        menuItemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addListConductoresPane();
            }
        });

        menuConductores.add(menuItemAlta);
        menuConductores.add(menuItemModificacion);
        menuConductores.add(menuItemBaja);
        menuConductores.add(menuItemListar);

        return menuConductores;
    }

    private JMenu generateProductorMenu() {
        JMenu menuProductores = new JMenu("Productores");
        JMenuItem menuItemAlta = new JMenuItem("Alta");

        menuItemAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addCreateProductorPane();
            }
        });

        JMenuItem menuItemModificacion = new JMenuItem("Modificación");
        menuItemModificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addUpdateProductorPane();
            }
        });

        JMenuItem menuItemBaja = new JMenuItem("Baja");
        menuItemBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addDeleteProductorPane();
            }
        });

        JMenuItem menuItemListar = new JMenuItem("Listar");
        menuItemListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.addListProductoresPane();
            }
        });

        menuProductores.add(menuItemAlta);
        menuProductores.add(menuItemModificacion);
        menuProductores.add(menuItemBaja);
        menuProductores.add(menuItemListar);

        return menuProductores;
    }

    protected void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().validate();
    }

    public void removePanel() {
        getContentPane().removeAll();
        getContentPane().validate();
    }
}