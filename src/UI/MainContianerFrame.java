package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainContianerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Handler handler;

	public MainContianerFrame(String titulo, Handler handler) {
		super(titulo);
		this.handler = handler;
		initUI();
	}

	private void initUI() {
		addMenubar();
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addMenubar() {
		setJMenuBar(generateUserMenu());
	}

	private JMenuBar generateUserMenu() {
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
				handler.addUpdateUserPane();
			}
		});

		JMenuItem menuItemBaja = new JMenuItem("Baja");
		menuItemBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				handler.addDeleteUserPane();
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuUsuario);
		return menuBar;
	}

	protected void changePanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}
}