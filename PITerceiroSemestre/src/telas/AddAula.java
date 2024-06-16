package telas;

import javax.swing.JPanel;
import customComponents.RoundedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import customComponents.CustomButton;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import customComponents.TextField;
import entities.Aula;
import entities.Funcionario;
import main.GetRowCount;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AddAula extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int NUMERO_MAXIMO_DE_ALUNOS_POR_AULA = 5;

	/**
	 * Cria o painel.
	 * @param funcionario - Dados do funcionario logado
	 * @param frame - Frame principal
	 * @param horarioComeco - O horário de início da aula digitado pelo usuário.
	 * @param dataBr - A data escolhida pelo usuário no formato dd/MM/yyyy
	 */
	public AddAula(Funcionario funcionario, MainFrame frame, String dataBr, String horarioComeco) {
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		RoundedBorder roundedBorder = new RoundedBorder();
		roundedBorder.setBorder(new LineBorder(new Color(159, 150, 138), 2));
		roundedBorder.setRoundBottomRight(50);
		roundedBorder.setRoundTopRight(50);
		roundedBorder.setRoundTopLeft(50);
		roundedBorder.setRoundBottomLeft(50);
		roundedBorder.setBackground(new Color(236, 236, 236));
		
		TextField textFieldData = new TextField();
		textFieldData.setShadowColor(new Color(0, 128, 255));
		textFieldData.setBackground(new Color(255, 255, 255));
		
		TextField textFieldAluno = new TextField();
		
		TextField textFieldInstrutor = new TextField();
		
		TextField textFieldHora = new TextField();
		
		TextField textFieldSala = new TextField();
		
		textFieldData.setText(dataBr);
		textFieldHora.setText(horarioComeco);
		
		CustomButton cstmbtnVoltar = new CustomButton();
		cstmbtnVoltar.setBackground(new Color(255, 255, 255));
		cstmbtnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		cstmbtnVoltar.setBorderColor(new Color(255, 255, 255));
		cstmbtnVoltar.setForeground(new Color(159, 150, 138));
		cstmbtnVoltar.setText("Voltar");
		cstmbtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal mp = new MenuPrincipal(funcionario, frame);
				frame.setContentPane(mp);
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		JLabel lblMensagem = new JLabel("Preencha todos os dados.");
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setVisible(false);
		
		CustomButton cstmbtnSalvar = new CustomButton();
		cstmbtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dia = Integer.parseInt(textFieldData.getText().substring(0, 2));
				int mes = Integer.parseInt(textFieldData.getText().substring(3, 5));
				int ano = Integer.parseInt(textFieldData.getText().substring(6, 10));
				
				int anoAtual = Integer.parseInt(LocalDate.now().toString().substring(0, 4));
				int mesAtual = Integer.parseInt(LocalDate.now().toString().substring(5, 7));
				int diaAtual = Integer.parseInt(LocalDate.now().toString().substring(8, 10));
				
				if (anoAtual > ano) {
					lblMensagem.setText("Data inválida.");
					lblMensagem.setVisible(true);
				} else if (mesAtual > mes) {
					lblMensagem.setText("Data inválida.");
					lblMensagem.setVisible(true);
				} else if (diaAtual < dia) {
					lblMensagem.setText("Data inválida.");
					lblMensagem.setVisible(true);
				} else {
					boolean dadosPreenchidos = true;
					String[] dados = new String[5];
					dados[0] = ano +"-"+ mes +"-"+ dia;
					dados[1] = textFieldHora.getText();
					dados[2] = textFieldAluno.getText();
					dados[3] = textFieldInstrutor.getText();
					dados[4] = textFieldSala.getText();
					for (int i = 0; i < dados.length; i++) {
						if (dados[i].equals("")) {
							lblMensagem.setText("Preencha todos os dados.");
							lblMensagem.setVisible(true);
							dadosPreenchidos = false;
							break;
						} else {
							dadosPreenchidos = true;
						}
					}
					if (dadosPreenchidos) {
						// Adicionar condicional para ter uma idade mínima e máxima
						try {
							Aula aula = new Aula();
							// Adionar horario
							aula.setData(Date.valueOf(dados[0]));
							aula.setHoraComeco(dados[1]);
							
							int horaFim = Integer.parseInt(dados[1].substring(0, 2));
							horaFim++;
							
							if (horaFim > 9) {
								aula.setHoraFim(horaFim +":00");
							} else {
								aula.setHoraFim("0"+ horaFim +":00");
							}
							
							aula.setVagasOcupadas(1);
							aula.setQtdeVagasDisponiveis(NUMERO_MAXIMO_DE_ALUNOS_POR_AULA - aula.getVagasOcupadas());
							aula.setSala(Integer.parseInt(dados[4]));
							aula.setIdAluno(Integer.parseInt(dados[2]));
							aula.setIdFuncionario(Integer.parseInt(dados[3]));
							
							boolean aulaSeraRegistrada = true, vagasAtualizadas = false;
							int numeroDeLinhas = GetRowCount.getRowCount(Aula.getNOME_TABELA());
							Aula aulaExistente = new Aula();

							// Verifica se ja existe um registro do aluno na data e hora informadas.
							for (int i = 1; i <= numeroDeLinhas; i++) {
								aulaExistente = Aula.read(i);
								if (aulaExistente.getData().equals(aula.getData())
										&& aulaExistente.getHoraComeco().equals(aula.getHoraComeco()) 
										&& aulaExistente.getIdAluno() == aula.getIdAluno()) {
									lblMensagem.setText("Aluno ja registrado para esta aula.");
									lblMensagem.setVisible(true);
									aulaSeraRegistrada = false;
									vagasAtualizadas = false;
									break;
								} else {
									aulaSeraRegistrada = true;
								}
							}
							if (aulaSeraRegistrada) {
								// Verifica se há registros de outros alunos nesta mesma hora e data.
								for (int i = 1; i <= numeroDeLinhas; i++) {
									aulaExistente = Aula.read(i);
									if (aulaExistente.getData().equals(aula.getData())
											&& aulaExistente.getHoraComeco().equals(aula.getHoraComeco())) {
										System.out.println("Há registros de outros alunos nesta mesma hora e data.");
										if (aulaExistente.getQtdeVagasDisponiveis() > 0) {
											Aula.update(aulaExistente);
											aulaExistente = Aula.read(i);
											vagasAtualizadas = true;
											aulaSeraRegistrada = true;
											break;
										} else {
											lblMensagem.setText("Não há mais vagas para esta aula.");
											lblMensagem.setVisible(true);
											aulaSeraRegistrada = false;
											break;
										}
									} else {
										System.out.println("Não há registros de outros alunos nesta mesma hora e data.");
									}
								}
							}
							if (vagasAtualizadas) {
								System.out.println("vagas disponiveis: "+aulaExistente.getQtdeVagasDisponiveis());
								System.out.println("vagas ocupadas: "+aulaExistente.getVagasOcupadas());
								aula.setQtdeVagasDisponiveis(aulaExistente.getQtdeVagasDisponiveis());
								aula.setVagasOcupadas(aulaExistente.getVagasOcupadas());
							}
							if (aulaSeraRegistrada) {
								lblMensagem.setText(Aula.create(aula));
								lblMensagem.setVisible(true);
							}
							
						} catch (NumberFormatException erro) {
							erro.printStackTrace();
							lblMensagem.setText("Preencha os dados corretamente.");
							lblMensagem.setVisible(true);
						} catch (IllegalArgumentException erro) {
							erro.printStackTrace();
							lblMensagem.setText("Data inválida.");
							lblMensagem.setVisible(true);
						}
					}
				}
			}
		});
		cstmbtnSalvar.setBackground(new Color(255, 255, 255));
		cstmbtnSalvar.setBorderColor(new Color(255, 255, 255));
		cstmbtnSalvar.setForeground(new Color(159, 150, 138));
		cstmbtnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		cstmbtnSalvar.setText("SALVAR");
		
		JLabel lblNewLabel = new JLabel("Data: (dd/mm/aaaa)");
		lblNewLabel.setForeground(new Color(159, 150, 138));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblHora = new JLabel("Hora: (XX:XX)");
		lblHora.setForeground(new Color(159, 150, 138));
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("ID do Aluno:");
		lblNewLabel_1_1.setForeground(new Color(159, 150, 138));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ID do Instrutor");
		lblNewLabel_1_1_1.setForeground(new Color(159, 150, 138));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sala:");
		lblNewLabel_1_1_1_1.setForeground(new Color(159, 150, 138));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		layeredPane.setLayout(new CardLayout(0, 0));
		
	
		
		
		
		CustomButton cstmbtnNovoAgendamento = new CustomButton();
		layeredPane.add(cstmbtnNovoAgendamento, "name_227161219541700");
		cstmbtnNovoAgendamento.setForeground(new Color(159, 150, 138));
		cstmbtnNovoAgendamento.setFont(new Font("Tahoma", Font.BOLD, 16));
		cstmbtnNovoAgendamento.setText("NOVO AGENDAMENTO");
		cstmbtnNovoAgendamento.setRadius(50);
		cstmbtnNovoAgendamento.setBorderColor(new Color(159, 150, 138));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(218)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addGap(225))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(roundedBorder, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(roundedBorder, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)))
					.addGap(19))
		);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(236, 236, 236));
		GroupLayout gl_roundedBorder = new GroupLayout(roundedBorder);
		gl_roundedBorder.setHorizontalGroup(
			gl_roundedBorder.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_roundedBorder.createSequentialGroup()
					.addGap(40)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(71)
					.addComponent(lblHora, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(141))
				.addGroup(gl_roundedBorder.createSequentialGroup()
					.addGap(31)
					.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addGap(60)
					.addComponent(textFieldHora, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addGap(141))
				.addGroup(gl_roundedBorder.createSequentialGroup()
					.addGap(41)
					.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(397))
				.addGroup(gl_roundedBorder.createSequentialGroup()
					.addGap(31)
					.addComponent(lblMensagem, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addGap(70)
					.addComponent(cstmbtnVoltar, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(cstmbtnSalvar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(gl_roundedBorder.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addComponent(textFieldAluno, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addComponent(textFieldInstrutor, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
					.addGap(56)
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addGap(15)
							.addComponent(lblNewLabel_1_1_1_1, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
							.addGap(93))
						.addComponent(textFieldSala, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addGap(9)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
					.addGap(141))
		);
		gl_roundedBorder.setVerticalGroup(
			gl_roundedBorder.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_roundedBorder.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldHora, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFieldAluno, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(textFieldSala, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(textFieldInstrutor, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_roundedBorder.createSequentialGroup()
							.addGap(32)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
							.addGap(8)))
					.addGap(18)
					.addGroup(gl_roundedBorder.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMensagem, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(cstmbtnVoltar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(cstmbtnSalvar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(8))
		);
		roundedBorder.setLayout(gl_roundedBorder);
		setLayout(groupLayout);

	}
}
