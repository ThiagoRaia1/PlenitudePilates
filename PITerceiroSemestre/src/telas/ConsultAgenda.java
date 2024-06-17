package telas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Aluno;
import entities.Aula;
import entities.Funcionario;
import main.CalculaIdade;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import customComponents.CustomButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultAgenda extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Cria o painel para visualizar os alunos registrados na data e horario escolhidos pelo usuario no painel
	 * "MenuPrincipal".
	 * @param funcionario - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 * @param aula - Array de objetos da classe "Aula", onde serao consultados os IDs dos alunos registrados.
	 * @param qtdeDeAlunosNaAula - Quantidade de alunos registrados na aula buscada.
	 * @param data - Data procurada pelo usuario.
	 */
	public ConsultAgenda(Funcionario funcionario, MainFrame frame, Aula[] aula, int qtdeDeAlunosNaAula, String data) {
		setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVisible(false);
		JTable tabelaAlunos = new JTable();
        tabelaAlunos.setModel(new DefaultTableModel(
        new Object [][] {}, 
        new String [] 
        		{"ID", "Nome", "Idade", "Contato", "Registrado por"} // Nome das colunas da tabela
        ));
		tabelaAlunos.setRowHeight(40);
		tabelaAlunos.setEnabled(false);
		tabelaAlunos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		scrollPane.setVisible(true);
		DefaultTableModel model = (DefaultTableModel) tabelaAlunos.getModel();
		Aluno alunoTabela = new Aluno();
		for (int i = 0; i < qtdeDeAlunosNaAula; i++) {
			alunoTabela = Aluno.read(aula[i].getIdAluno());
	        model.addRow(new Object[]
	        		{alunoTabela.getId(), alunoTabela.getNome(), 
	        				CalculaIdade.calculaIdade(alunoTabela.getDataNascimento()), 
	        				alunoTabela.getTelefone(), Funcionario.read(alunoTabela.getRegistradoPor()).getNome()}
	        );
		}
        scrollPane.setViewportView(tabelaAlunos);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		JLabel lblAlunosNaAula = new JLabel("Alunos na aula do dia: "+ data +" às "+ aula[0].getHoraComeco());
		lblAlunosNaAula.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlunosNaAula.setForeground(Color.DARK_GRAY);
		lblAlunosNaAula.setBackground(Color.LIGHT_GRAY);
		lblAlunosNaAula.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		CustomButton btnVoltar = new CustomButton();
		btnVoltar.setFocusPainted(false);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVoltar.setBorderPainted(false);
		btnVoltar.setRadius(10);
		btnVoltar.setText("VOLTAR");
		btnVoltar.setColorOver(new Color(236, 236, 236));
		btnVoltar.setColorClick(new Color(236, 236, 236));
		btnVoltar.setColor(new Color(255, 255, 255));
		btnVoltar.setBorderColor(new Color(255, 255, 255));
		btnVoltar.addActionListener(new ActionListener() {
			/**
			 * Retorna ao menu principal.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal(funcionario, frame);
				frame.setContentPane(mp);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_2_1 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_3_1 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE))
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblAlunosNaAula, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(lblAlunosNaAula, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
					.addGap(8))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(11))
		);
		setLayout(groupLayout);

	}
}
