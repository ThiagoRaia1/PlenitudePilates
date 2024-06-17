package telas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import customComponents.JPictureBox;
import entities.Funcionario;
import main.GetRowCount;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Classe para instanciacao do frame principal da aplicacao.
 */
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
	 * Inicia a aplicacao.
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
	 * Cria o frame principal.
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
		textFieldLogin.setToolTipText("Usuário");
		textFieldLogin.setFont(new Font("Arial", Font.BOLD, 24));
		textFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Senha");
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
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
					.addGap(68)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
								.addComponent(textFieldLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
							.addGap(32)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
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
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
		);
		
		JLabel lblLoginFalhou = new JLabel("Usuário ou senha incorretos.");
		lblLoginFalhou.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoginFalhou.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addGap(20)
					.addComponent(lblLoginFalhou, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addComponent(lblLoginFalhou, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(140, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		lblLoginFalhou.setVisible(false);
		
		JPictureBox logo = new JPictureBox();
		logo.setIcon(new ImageIcon("src\\images\\Logo.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(logo, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(logo, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		setExtendedState(MAXIMIZED_BOTH);
		
		btnLogin.addActionListener(new ActionListener() {
			/**
			 * Valida o login. Se o login for valido, avanca para a tela "MenuPrincipal", se nao, exibe
			 * a mensagem "Usuario ou senha incorretos.".
			 */
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String senha = String.copyValueOf(passwordField.getPassword());
				
				boolean loginFalhou = true;
				int numeroDeLinhas = GetRowCount.getRowCount(Funcionario.getNOME_TABELA());
				
				for (int i = 1; i <= numeroDeLinhas; i++) {
					Funcionario funcionario = new Funcionario();
					/**
					 * Apos validacao, as informacoes do usuario logado serao armazenadas em um objeto.
					*/
					funcionario = Funcionario.read(i);
					
					if (login.equals(funcionario.getUsuario()) && senha.equals(funcionario.getSenha())) {
						System.out.println("Login realizado.");
						loginFalhou = false;
						MenuPrincipal m = new MenuPrincipal(funcionario, frame);
						setContentPane(m);
						revalidate(); //refresh
						repaint();
					}
				}
				// Se o login nao for realizado
				if (loginFalhou) {
					lblLoginFalhou.setVisible(true);
				}
			}
		});
	}
}
