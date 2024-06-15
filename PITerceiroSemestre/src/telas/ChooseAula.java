package telas;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import entities.Aula;
import entities.Funcionario;
import main.GetRowCount;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseAula extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldData;
	private JTextField textFieldHorarioDeInicio;

	/**
	 * Cria o painel que receberá a data a ser consultada.
	 */
	public ChooseAula(Funcionario funcionario, MainFrame frame) {
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, null));
		
		textFieldData = new JTextField();
		textFieldData.setText("Data");
		textFieldData.setColumns(10);

		JLabel lblMensagem = new JLabel("Data inválida");
		lblMensagem.setVisible(false);
		
		textFieldHorarioDeInicio = new JTextField();
		textFieldHorarioDeInicio.setText("Horario de início");
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal(funcionario, frame);
				frame.setContentPane(mp);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JLabel lblDigiteAData = new JLabel("Digite a data da aula:");
		lblDigiteAData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDigiteAData.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnAvancar = new JButton("Avançar");
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aulaProcurada = textFieldData.getText();
				String horarioComeco = textFieldHorarioDeInicio.getText();
				String data = null;
				try {
					data = aulaProcurada;
					int dia = Integer.parseInt(aulaProcurada.substring(0, 2));
					int mes = Integer.parseInt(aulaProcurada.substring(3, 5));
					int ano = Integer.parseInt(aulaProcurada.substring(6, 10));
					if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
						lblMensagem.setText("Data inválida.");
						lblMensagem.setVisible(true);
					} else {
						aulaProcurada = ano+"-"+mes+"-"+dia;
						boolean naoEncontrado = true;
						int qtdeLinhasTabela = GetRowCount.getRowCount(Aula.getNomeTabela());
						Aula[] alunosNaAula = new Aula[qtdeLinhasTabela];
						Aula aula = new Aula();
						int qtdeDeAlunosNaAula = 0;
						for (int i = 1; i <= qtdeLinhasTabela; i++) {
							aula = Aula.read(i);
							if (aulaProcurada.equals(aula.getData().toString().substring(0, 10))
									&& horarioComeco.equals(aula.getHoraComeco()) ) {
								System.out.println("Aula encontrada.");
								naoEncontrado = false;
								alunosNaAula[qtdeDeAlunosNaAula] = aula;
								qtdeDeAlunosNaAula++;
							}
						}
						if (naoEncontrado) {
							lblMensagem.setText("Não há nenhum aluno registrado para a aula procurada.");
							lblMensagem.setVisible(true);
							System.out.println("Aula não encontrada");
						} else {
							ConsultAgenda ca = new ConsultAgenda(funcionario, frame, alunosNaAula, qtdeDeAlunosNaAula, data);
							frame.setContentPane(ca);
							frame.revalidate(); //refresh
							frame.repaint();
						}
					}
				} catch (NumberFormatException erro) {
					lblMensagem.setText("Data inválida.");
					lblMensagem.setVisible(true);
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_1_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldHorarioDeInicio.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAvancar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
								.addComponent(btnVoltar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
							.addGap(66))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblDigiteAData, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(59)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textFieldHorarioDeInicio, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
									.addGap(70))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
									.addGap(70)))))
					.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(0)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDigiteAData, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textFieldHorarioDeInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAvancar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar)
							.addGap(32))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
								.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		setLayout(groupLayout);
	}
}
