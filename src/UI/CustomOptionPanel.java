package UI;

import javax.swing.JOptionPane;

public class CustomOptionPanel extends JOptionPane {

	public static void showInformationMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Atención", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
