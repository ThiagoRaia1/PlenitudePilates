package telas;

import entities.Aluno;
import entities.Funcionario;
import main.CalculaIdade;
import main.GetRowCount;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import customComponents.JPictureBox;
import javax.swing.ImageIcon;

public class MenuPrincipal extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static JPanel menuExibicao;
	private static JPanel PanelMenu;
	
	/**
	 * Create the panel.
	 */
	public MenuPrincipal(Funcionario funcionario, MainFrame frame) {
		setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		JTable tabelaFuncionarios = new JTable();
        tabelaFuncionarios.setModel(new DefaultTableModel(
        new Object [][] {}, 
        new String [] 
        		{"Nome", "Funcao", "Idade", "CPF", "Contato"} // Nome das colunas da tabela
        ));
		tabelaFuncionarios.setRowHeight(40);
		tabelaFuncionarios.setEnabled(false);
		tabelaFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JTable tabelaAlunos = new JTable();
        tabelaAlunos.setModel(new DefaultTableModel(
        new Object [][] {}, 
        new String [] 
        		{"Nome", "Mensalidade", "Idade", "CPF", "Contato"} // Nome das colunas da tabela
        ));
		tabelaAlunos.setRowHeight(40);
		tabelaAlunos.setEnabled(false);
		tabelaAlunos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		menuExibicao = new JPanel();
		menuExibicao.setBackground(Color.LIGHT_GRAY);
		
		PanelMenu = new JPanel();
		PanelMenu.setBackground(SystemColor.activeCaption);
		
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(false);
				revalidate(); //refresh
				repaint();
			}
		});
		btnAgenda.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnAlunos = new JButton("Alunos");
		btnAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(true);
				DefaultTableModel model = (DefaultTableModel) tabelaAlunos.getModel();
				int numeroDeLinhasTabela = GetRowCount.getRowCount(Aluno.getNOME_TABELA());
				int qtdeLinhasTabelaAtual = model.getRowCount();
				System.out.println("Quantidade de linhas atual: "+model.getRowCount());
				if (qtdeLinhasTabelaAtual != 0) {
					for (int i = qtdeLinhasTabelaAtual; i > 0; i--) {
						System.out.println(i);
						model.removeRow(i-1);
					}
				}
				Aluno aluno = new Aluno();
				if (numeroDeLinhasTabela >= qtdeLinhasTabelaAtual) {
					for (int i = 1; i <= numeroDeLinhasTabela; i++) {
						aluno = Aluno.read(i);
				        model.addRow(new Object[]
				        		{aluno.getNome(), 200, CalculaIdade.calculaIdade(aluno.getDataNascimento()), 
				        				"CPF", aluno.getTelefone()}
				        );
					}
				}
		        scrollPane.setViewportView(tabelaAlunos);
				revalidate(); //refresh
				repaint();
			}
		});
		btnAlunos.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnEquipe = new JButton("Equipe");
		btnEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(true);
				DefaultTableModel model = (DefaultTableModel) tabelaFuncionarios.getModel();
				int numeroDeLinhasTabela = GetRowCount.getRowCount(Funcionario.getNOME_TABELA());
				int qtdeLinhasTabelaAtual = model.getRowCount();
				if (qtdeLinhasTabelaAtual != 0) {
					for (int i = qtdeLinhasTabelaAtual; i > 0; i--) {
						System.out.println(i);
						model.removeRow(i-1);
					}
				}
				Funcionario funcionario = new Funcionario();
				if (numeroDeLinhasTabela >= qtdeLinhasTabelaAtual) {
					for (int i = 1; i <= numeroDeLinhasTabela; i++) {
						funcionario = Funcionario.read(i);
				        model.addRow(new Object[]
				        		{funcionario.getNome(), funcionario.getFuncao(), 
				        				19, 
				        				"CPF", funcionario.getTelefone()}
				        );
					}
				}
		        scrollPane.setViewportView(tabelaFuncionarios);
				revalidate(); //refresh
				repaint();
			}
		});
		btnEquipe.setFont(new Font("Tahoma", Font.BOLD, 20));  
		
		JButton btnFinanceiro = new JButton("Financeiro");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(false);
				revalidate(); //refresh
				repaint();
			}
		});
		btnFinanceiro.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(SystemColor.activeCaption);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBackground(SystemColor.activeCaption);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(SystemColor.activeCaption);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(SystemColor.activeCaption);
		
		JLabel lblNomeUsuario_1 = new JLabel(funcionario.getNome());
		lblNomeUsuario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
		gl_panel_2_1.setHorizontalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addComponent(lblNomeUsuario_1, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2_1.setVerticalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2_1.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lblNomeUsuario_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		panel_2_1.setLayout(gl_panel_2_1);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAluno addPanel = new AddAluno(funcionario, frame);
				frame.setContentPane(addPanel);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseAluno ca = new ChooseAluno(funcionario, frame);
				frame.setContentPane(ca);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JPictureBox addIcon = new JPictureBox();
		addIcon.setIcon(new ImageIcon("C:\\Users\\1050482313025\\git\\PlenitudePilates\\PITerceiroSemestre\\src\\images\\add-friend.png"));
		
		JPictureBox iconEdit = new JPictureBox();
		iconEdit.setIcon(new ImageIcon("C:\\Users\\1050482313025\\git\\PlenitudePilates\\PITerceiroSemestre\\src\\images\\edit.png"));
		JPictureBox fundo = new JPictureBox();
		fundo.setIcon(new ImageIcon("C:\\Users\\1050482313025\\git\\PlenitudePilates\\PITerceiroSemestre\\src\\images\\fundo.png"));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(PanelMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(menuExibicao, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(PanelMenu, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
				.addComponent(menuExibicao, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
		);
		GroupLayout gl_panelMenu = new GroupLayout(PanelMenu);
		gl_panelMenu.setHorizontalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(2))
						.addComponent(btnAgenda, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(btnAlunos, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(btnEquipe, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
							.addGap(4))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(panel_3_2, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(btnFinanceiro, GroupLayout.PREFERRED_SIZE, 124, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(9))
		);
		gl_panelMenu.setVerticalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btnAgenda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(btnAlunos, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btnEquipe, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(panel_3_2, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btnFinanceiro, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(panel_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		PanelMenu.setLayout(gl_panelMenu);
		GroupLayout gl_menuExibicao = new GroupLayout(menuExibicao);
		gl_menuExibicao.setHorizontalGroup(
			gl_menuExibicao.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(90)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
					.addGap(17))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(197)
					.addComponent(addIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(185)
					.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(104)
					.addComponent(iconEdit, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(119))
				.addComponent(fundo, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
		);
		gl_menuExibicao.setVerticalGroup(
			gl_menuExibicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(11)
					.addComponent(btnEditar)
					.addGap(62)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addGap(11))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(39)
					.addComponent(addIcon, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(11)
					.addComponent(btnAdicionar))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(39)
					.addComponent(iconEdit, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addComponent(fundo, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
		);
		menuExibicao.setLayout(gl_menuExibicao);
		setLayout(groupLayout);
		
	}
}
