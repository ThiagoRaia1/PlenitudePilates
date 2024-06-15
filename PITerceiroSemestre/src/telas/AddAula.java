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

public class AddAula extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int NUMERO_MAXIMO_DE_ALUNOS_POR_AULA = 5;

	/**
	 * Create the panel.
	 * @param funcionario - dados do funcionario logado
	 * @param frame - frame principal
	 */
	public AddAula(Funcionario funcionario, MainFrame frame) {
		setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(218, 5, 233, 31);
		add(layeredPane);
		
		RoundedBorder roundedBorder = new RoundedBorder();
		roundedBorder.setBorder(new LineBorder(new Color(159, 150, 138), 2));
		roundedBorder.setRoundBottomRight(50);
		roundedBorder.setRoundTopRight(50);
		roundedBorder.setRoundTopLeft(50);
		roundedBorder.setRoundBottomLeft(50);
		roundedBorder.setBackground(new Color(236, 236, 236));
		roundedBorder.setBounds(20, 18, 638, 394);
		add(roundedBorder);
		roundedBorder.setLayout(null);
		
		TextField textFieldData = new TextField();
		textFieldData.setShadowColor(new Color(0, 128, 255));
		textFieldData.setBackground(new Color(255, 255, 255));
		textFieldData.setBounds(33, 87, 196, 50);
		roundedBorder.add(textFieldData);
		
		TextField textFieldAluno = new TextField();
		textFieldAluno.setBounds(33, 195, 196, 50);
		roundedBorder.add(textFieldAluno);
		
		TextField textFieldInstrutor = new TextField();
		textFieldInstrutor.setBounds(33, 275, 196, 50);
		roundedBorder.add(textFieldInstrutor);
		
		TextField textFieldHora = new TextField();
		textFieldHora.setBounds(289, 87, 196, 50);
		roundedBorder.add(textFieldHora);
		
		TextField textFieldSala = new TextField();
		textFieldSala.setBounds(285, 201, 200, 44);
		roundedBorder.add(textFieldSala);
		
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
		cstmbtnVoltar.setBounds(397, 351, 117, 33);
		roundedBorder.add(cstmbtnVoltar);
		
		JLabel lblMensagem = new JLabel("Preencha todos os dados.");
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setBounds(33, 335, 294, 49);
		lblMensagem.setVisible(false);
		roundedBorder.add(lblMensagem);
		
		CustomButton cstmbtnSalvar = new CustomButton();
		cstmbtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dia = Integer.parseInt(textFieldData.getText().substring(0, 2));
				int mes = Integer.parseInt(textFieldData.getText().substring(3, 5));
				int ano = Integer.parseInt(textFieldData.getText().substring(6, 10));
				
				int anoAtual = Integer.parseInt(LocalDate.now().toString().substring(0, 4));
				int mesAtual = Integer.parseInt(LocalDate.now().toString().substring(5, 7));
				int diaAtual = Integer.parseInt(LocalDate.now().toString().substring(8, 10));
				System.out.println("Dia atual: "+diaAtual);
				System.out.println("Mes atual: "+mesAtual);
				System.out.println("Ano atual: "+anoAtual);
				
				System.out.println(dia);
				System.out.println(mes);
				System.out.println(ano);
				
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
							System.out.println("Dados[0]: "+dados[0]);
							System.out.println("Dados[1]: "+dados[1]);
							System.out.println("horaComeco: "+dados[0]+" "+dados[1]);
							
							// Adionar horario
							aula.setData(Date.valueOf(dados[0]));
							aula.setHoraComeco(dados[1]);
							
							System.out.println("Dados[1] (substring 0-2) = "+dados[1].substring(0, 2));
							
							int horaFim = Integer.parseInt(dados[1].substring(0, 2));
							horaFim++;
							System.out.println("horaFim: "+horaFim);
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
							for (int i = 1; i <= numeroDeLinhas; i++) {
								aulaExistente = Aula.read(i);
								
								if (aulaExistente.getHoraComeco().equals(aula.getHoraComeco()) 
										&& aulaExistente.getIdAluno() == aula.getIdAluno()) {
									lblMensagem.setText("Aluno ja registrado para esta aula.");
									lblMensagem.setVisible(true);
									aulaSeraRegistrada = false;
									break;
								} else {
									aulaSeraRegistrada = true;
								}
									
								if (aulaExistente.getData().equals(aula.getData())
										&& aulaExistente.getHoraComeco().equals(aula.getHoraComeco())) {
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
								}
								
							}
							if (vagasAtualizadas) {
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
		cstmbtnSalvar.setBounds(524, 351, 92, 33);
		roundedBorder.add(cstmbtnSalvar);
		
		JLabel lblNewLabel = new JLabel("Data: (dd/mm/aaaa)");
		lblNewLabel.setForeground(new Color(159, 150, 138));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(42, 64, 187, 21);
		roundedBorder.add(lblNewLabel);
		
		JLabel lblHora = new JLabel("Hora: (99:99)");
		lblHora.setForeground(new Color(159, 150, 138));
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHora.setBounds(300, 64, 185, 21);
		roundedBorder.add(lblHora);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID do Aluno:");
		lblNewLabel_1_1.setForeground(new Color(159, 150, 138));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(42, 174, 187, 21);
		roundedBorder.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ID do Instrutor");
		lblNewLabel_1_1_1.setForeground(new Color(159, 150, 138));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(43, 252, 186, 21);
		roundedBorder.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sala:");
		lblNewLabel_1_1_1_1.setForeground(new Color(159, 150, 138));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(300, 178, 92, 21);
		roundedBorder.add(lblNewLabel_1_1_1_1);
		layeredPane.setLayout(new CardLayout(0, 0));
		
	
		
		
		
		CustomButton cstmbtnNovoAgendamento = new CustomButton();
		layeredPane.add(cstmbtnNovoAgendamento, "name_227161219541700");
		cstmbtnNovoAgendamento.setForeground(new Color(159, 150, 138));
		cstmbtnNovoAgendamento.setFont(new Font("Tahoma", Font.BOLD, 16));
		cstmbtnNovoAgendamento.setText("NOVO AGENDAMENTO");
		cstmbtnNovoAgendamento.setRadius(50);
		cstmbtnNovoAgendamento.setBorderColor(new Color(159, 150, 138));

	}
}
