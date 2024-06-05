package telas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import customComponents.JPictureBox;
import entities.Funcionario;
import main.BD;
import main.Main;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static MainFrame frame;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(900, 550));
		setBounds(20, 150, 900, 550);
		setTitle("SG Plenitude Pilates");
		//setExtendedState(MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setBounds(0, 0, 900, 550);
		setContentPane(contentPane);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Arial", Font.BOLD, 24));
		textFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.BOLD, 24));
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(25, 25, 112));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 24));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		
		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		
		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(35)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
										.addComponent(textFieldLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
									.addGap(32)
									.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addGap(7)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
		);
		
		JPictureBox pictureBox = new JPictureBox();
		pictureBox.setIcon(new ImageIcon("C:\\Users\\1050482313025\\Desktop\\PlenitudePilates\\PITerceiroSemestre\\src\\telas\\Captura de tela 2024-05-31 162519.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(pictureBox, GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(pictureBox, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		setExtendedState(MAXIMIZED_BOTH);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String senha = String.copyValueOf(passwordField.getPassword());
				System.out.println(getExtendedState());
				// buscar o login e senha digitados no banco para validar o login
				boolean loginFalhou = true;
				String sql = "select count(*) from Funcionario";
				int numeroDeLinhas = 0;
				BD bd = new BD();
				bd.getConnection();
				try {
					bd.st = bd.con.prepareStatement(sql);
					bd.rs = bd.st.executeQuery();
	                while (bd.rs.next()) {
	                    numeroDeLinhas = bd.rs.getInt(1);
	                }
				} catch (SQLException erro) {
					erro.printStackTrace();
				}
                System.out.println("Número de linhas: "+numeroDeLinhas);
				
				for (int i = 1; i <= numeroDeLinhas; i++) {
					Funcionario funcionario = new Funcionario();
					/* 
					 * após validação, as informações do usuario logado serão armazenadas em um objeto
					*/
					funcionario = Funcionario.read(i);
					
					if (login.equals(funcionario.getUsuario()) && senha.equals(funcionario.getSenha())) {
						System.out.println("Login realizado.");
						loginFalhou = false;
						MenuPrincipal m = new MenuPrincipal(funcionario);
						setContentPane(m);
						revalidate(); //refresh
						repaint();
					}
				}
				// Se o login não for realizado
				if (loginFalhou) {
					//lblLoginSenhaIncorretos.setVisible(true);
					System.out.println("Login ou senha incorretos");
				}
			}
		});
	}
}
