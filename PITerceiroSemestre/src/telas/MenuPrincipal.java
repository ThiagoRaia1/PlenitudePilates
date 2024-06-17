package telas;

import entities.Aluno;
import entities.Aula;
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
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import customComponents.JPictureBox;
import javax.swing.ImageIcon;
import com.toedter.calendar.JCalendar;
import javax.swing.JTextField;

/**
 * Painel do menu principal para navegacao.
 */
public class MenuPrincipal extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static JPanel menuExibicao;
	private static JPanel PanelMenu;
	private static JTextField textFieldHorario;
	
	private static String menuAtual = Aula.getNOME_TABELA();
	private static final int PERMISSAO_MINIMA_PARA_REGISTRAR_AULA = 2;
	private static final int PERMISSAO_MINIMA_PARA_REGISTRAR_ALUNO = 2;
	private static final int PERMISSAO_MINIMA_PARA_REGISTRAR_FUNCIONARIO = 1;
	
	private static final String PADRAO_BR = "dd/MM/yyyy";
	SimpleDateFormat formatoBr = new SimpleDateFormat(PADRAO_BR);
	
	private static final String PADRAO_DATE = "yyyy-MM-dd";
	SimpleDateFormat formatoDate = new SimpleDateFormat(PADRAO_DATE);
	
	/**
	 * Cria o painel do menu principal para navegacao.
	 * @param funcionario - Dados do funcionario logado.
	 * @param frame - Frame principal.
	 */
	public MenuPrincipal(Funcionario funcionario, MainFrame frame) {
		setBackground(new Color(255, 255, 255));
		
		menuAtual = Aula.getNOME_TABELA();
		
		JCalendar calendar = new JCalendar();
		calendar.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		JTable tabelaFuncionarios = new JTable();
        tabelaFuncionarios.setModel(new DefaultTableModel(
        new Object [][] {}, 
        new String [] 
        		{"ID", "Nome", "Funcao", "Cidade", "Contato"} // Nome das colunas da tabela
        ));
		tabelaFuncionarios.setRowHeight(40);
		tabelaFuncionarios.setEnabled(false);
		tabelaFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JTable tabelaAlunos = new JTable();
        tabelaAlunos.setModel(new DefaultTableModel(
        new Object [][] {}, 
        new String [] 
        		{"ID", "Nome", "Idade", "Contato", "Registrado por"} // Nome das colunas da tabela
        ));
		tabelaAlunos.setRowHeight(40);
		tabelaAlunos.setEnabled(false);
		tabelaAlunos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		scrollPane.setVisible(false);

		JPictureBox addIcon = new JPictureBox();
		addIcon.setIcon(new ImageIcon("src\\images\\add.png"));
		
		JPictureBox iconEdit = new JPictureBox();
		iconEdit.setIcon(new ImageIcon("src\\images\\edit.png"));
		
		JPictureBox fundo = new JPictureBox();
		fundo.setIcon(new ImageIcon("src\\images\\fundoMenuPrincipal.png"));

		addIcon.setVisible(false);
		iconEdit.setVisible(false);
		
		JLabel lblHorario = new JLabel("Horario:");
		
		textFieldHorario = new JTextField();
		textFieldHorario.setToolTipText("(XX:XX)");
		textFieldHorario.setColumns(10);
		
		JLabel lblMensagem = new JLabel("Selecione a data e hora.");
		lblMensagem.setVisible(true);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			/**
			 * Fecha a aplicacao.
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuExibicao = new JPanel();
		menuExibicao.setBackground(Color.LIGHT_GRAY);
		
		PanelMenu = new JPanel();
		PanelMenu.setBackground(SystemColor.activeCaption);
		
		
		JButton btnUm = new JButton("Consultar");
		btnUm.addActionListener(new ActionListener() {
			/**
			 * Direciona para a proxima tela de acordo com o menu selecionado.
			 * "ConsultAgenda", se "Agenda" estiver selecionada;
			 * "ChooseAluno", se "Alunos" estiver selecionado;
			 * "ChooseFuncionario", se a "Equipe" estiver selecionado";
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				if (menuAtual == Aula.getNOME_TABELA()) {
					
					String dataProcurada = formatoDate.format(calendar.getDate());
					
					String dataBr = formatoBr.format(calendar.getDate());
					
					String horarioComeco = textFieldHorario.getText();
					
					try {
						int horas = Integer.parseInt(horarioComeco.substring(0,2));
						int minutos = Integer.parseInt(horarioComeco.substring(3,5));
						if (horas <= 0 || horas > 23 || minutos < 0 || minutos > 59 || horarioComeco.charAt(2) != ':'
								|| horarioComeco.length() != 5) {
							lblMensagem.setText("Horário inválido.");
							lblMensagem.setVisible(true);
						} else {
							boolean naoEncontrado = true;
							int qtdeLinhasTabela = GetRowCount.getRowCount(Aula.getNOME_TABELA());
							Aula[] alunosNaAula = new Aula[qtdeLinhasTabela];
							Aula aula = new Aula();
							int qtdeDeAlunosNaAula = 0;
							for (int i = 1; i <= qtdeLinhasTabela; i++) {
								aula = Aula.read(i);
								if (dataProcurada.equals(aula.getData().toString().substring(0, 10))
										&& horarioComeco.equals(aula.getHoraComeco()) ) {
									System.out.println("Aula encontrada.");
									naoEncontrado = false;
									alunosNaAula[qtdeDeAlunosNaAula] = aula;
									qtdeDeAlunosNaAula++;
								}
							}
							if (naoEncontrado) {
								lblMensagem.setText("Nenhum aluno registrado.");
								lblMensagem.setVisible(true);
								System.out.println("Aula não encontrada");
							} else {
								ConsultAgenda ca = new ConsultAgenda(funcionario, frame, alunosNaAula, qtdeDeAlunosNaAula, dataBr);
								frame.setContentPane(ca);
								frame.revalidate(); //refresh
								frame.repaint();
							}
						}
					} catch (NumberFormatException erro) {
						// erro.printStackTrace();
						lblMensagem.setText("Horário inválido.");
						lblMensagem.setVisible(true);
						System.out.println("Horário inválido.");
					} catch (StringIndexOutOfBoundsException erro) {
						// erro.printStackTrace();
						lblMensagem.setText("Horário inválido.");
						lblMensagem.setVisible(true);
						System.out.println("Horário inválido.");
					}
					
				}
				
				if (menuAtual == Aluno.getNOME_TABELA()) {
					ChooseAluno ca = new ChooseAluno(funcionario, frame);
					frame.setContentPane(ca);
				}
				if (menuAtual == Funcionario.getNOME_TABELA()) {
					ChooseFuncionario cf = new ChooseFuncionario(funcionario, frame);
					frame.setContentPane(cf);
				}
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});

		JButton btnDois = new JButton("Novo");
		btnDois.addActionListener(new ActionListener() {
			/**
			 * Direciona para a proxima tela de acordo com o menu selecionado.
			 * "AddAula", se "Agenda" estiver selecionada;
			 * "AddAluno", se "Alunos" estiver selecionado;
			 * "AddFuncionario", se a "Equipe" estiver selecionado";
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				if (menuAtual == Aula.getNOME_TABELA()) {
					
					String dataProcurada = formatoDate.format(calendar.getDate());
					
					String dataBr = formatoBr.format(calendar.getDate());
					
					String horarioComeco = textFieldHorario.getText();
					
					try {
						int horas = Integer.parseInt(horarioComeco.substring(0,2));
						int minutos = Integer.parseInt(horarioComeco.substring(3,5));
						if (horas <= 0 || horas > 23 || minutos < 0 || minutos > 59 || horarioComeco.charAt(2) != ':'
								|| horarioComeco.length() != 5) {
							lblMensagem.setText("Horário inválido.");
							lblMensagem.setVisible(true);
							System.out.println("Horário inválido.");
						} else {
							int qtdeLinhasTabela = GetRowCount.getRowCount(Aula.getNOME_TABELA());
							Aula[] alunosNaAula = new Aula[qtdeLinhasTabela];
							Aula aula = new Aula();
							int qtdeDeAlunosNaAula = 0;
							for (int i = 1; i <= qtdeLinhasTabela; i++) {
								aula = Aula.read(i);
								if (dataProcurada.equals(aula.getData().toString().substring(0, 10))
										&& horarioComeco.equals(aula.getHoraComeco()) ) {
									System.out.println("Aula encontrada.");
									alunosNaAula[qtdeDeAlunosNaAula] = aula;
									qtdeDeAlunosNaAula++;
								}
							}
							if (qtdeDeAlunosNaAula == 5) {
								lblMensagem.setText("Não há vagas disponíveis.");
								lblMensagem.setVisible(true);
							} else {
								System.out.println(qtdeDeAlunosNaAula);
								AddAula addAula = new AddAula(funcionario, frame, dataBr, horarioComeco);
								frame.setContentPane(addAula);
							}
						}
					} catch (NumberFormatException erro) {
						// erro.printStackTrace();
						lblMensagem.setText("Horário inválido.");
						lblMensagem.setVisible(true);
					} catch (StringIndexOutOfBoundsException erro) {
						// erro.printStackTrace();
						lblMensagem.setText("Horário inválido.");
						lblMensagem.setVisible(true);
					}
				}
				if (menuAtual == Aluno.getNOME_TABELA()) {
					AddAluno addAluno = new AddAluno(funcionario, frame);
					frame.setContentPane(addAluno);
				}
				if (menuAtual == Funcionario.getNOME_TABELA()) {
					AddFuncionario addFuncionario = new AddFuncionario(funcionario, frame);
					frame.setContentPane(addFuncionario);
				}
				frame.revalidate(); //refresh
				frame.repaint();
			}
		});
		
		/**
		 * Verifica se o usuario tem permissao para registrar novas aulas, para que os botoes iniciem 
		 * habilitados ou nao.
		 */
		if (funcionario.getNivelDeAcesso() > PERMISSAO_MINIMA_PARA_REGISTRAR_AULA) {
        	btnDois.setEnabled(false);
        	btnUm.setEnabled(true);
        } else {
        	btnDois.setEnabled(true);
        	btnUm.setEnabled(true);
        }
		
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.setForeground(new Color(255, 255, 255));
		btnAgenda.setBorderPainted(false);
		btnAgenda.setBackground(new Color(153, 180, 209));
		btnAgenda.addActionListener(new ActionListener() {
			/**
			 * Faz as alteracoes necessarias para que apenas os componentes relacionados a "Agenda" sejam exibidos.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				lblMensagem.setText("Selecione a data e hora.");
				lblMensagem.setVisible(true);
				menuAtual = Aula.getNOME_TABELA();
				scrollPane.setVisible(false);
				btnUm.setText("Consultar");
				btnDois.setText("Novo");
				addIcon.setVisible(false);
				iconEdit.setVisible(false);
				calendar.setVisible(true);
				lblHorario.setVisible(true);
				textFieldHorario.setVisible(true);
				// Verifica se o usuario ter permissao para registrar novas aulas.		
				if (funcionario.getNivelDeAcesso() > PERMISSAO_MINIMA_PARA_REGISTRAR_AULA) {
		        	btnDois.setEnabled(false);
		        	btnUm.setEnabled(true);
		        } else {
		        	btnDois.setEnabled(true);
		        	btnUm.setEnabled(true);
		        }
				addIcon.setVisible(false);
				iconEdit.setVisible(false);
				revalidate(); //refresh
				repaint();
			}
		});
		btnAgenda.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnAlunos = new JButton("Alunos");
		btnAlunos.setForeground(new Color(255, 255, 255));
		btnAlunos.setBackground(new Color(153, 180, 209));
		btnAlunos.setBorderPainted(false);
		btnAlunos.addActionListener(new ActionListener() {
			/**
			 * Faz as alteracoes necessarias para que apenas os componentes relacionados a "Alunos" sejam exibidos.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				calendar.setVisible(false);
				menuAtual = Aluno.getNOME_TABELA();
				btnUm.setText("Editar");
				btnDois.setText("Novo");
				addIcon.setVisible(true);
				iconEdit.setVisible(true);
				lblHorario.setVisible(false);
				textFieldHorario.setVisible(false);
				lblMensagem.setVisible(false);
				scrollPane.setVisible(true);
				
				DefaultTableModel model = (DefaultTableModel) tabelaAlunos.getModel();
				int numeroDeLinhasTabela = GetRowCount.getRowCount(Aluno.getNOME_TABELA());
				int qtdeLinhasTabelaAtual = model.getRowCount();
				//System.out.println("Quantidade de linhas atual: "+model.getRowCount());
				if (qtdeLinhasTabelaAtual != 0) {
					for (int i = qtdeLinhasTabelaAtual; i > 0; i--) {
						//System.out.println(i);
						model.removeRow(i-1);
					}
				}
				Aluno alunoTabela = new Aluno();
				if (numeroDeLinhasTabela >= qtdeLinhasTabelaAtual) {
					for (int i = 1; i <= numeroDeLinhasTabela; i++) {
						alunoTabela = Aluno.read(i);
						model.addRow(new Object[]
				        		{alunoTabela.getId(), alunoTabela.getNome(), 
				        				CalculaIdade.calculaIdade(alunoTabela.getDataNascimento()), 
				        				alunoTabela.getTelefone(), 
				        				Funcionario.read(alunoTabela.getRegistradoPor()).getNome()}
				        );
					}
				}
		        scrollPane.setViewportView(tabelaAlunos);
		        if (funcionario.getNivelDeAcesso() > PERMISSAO_MINIMA_PARA_REGISTRAR_ALUNO) {
		        	btnDois.setEnabled(false);
		        	btnUm.setEnabled(false);
		        } else {
		        	btnDois.setEnabled(true);
		        	btnUm.setEnabled(true);
		        }
		        revalidate(); //refresh
				repaint();
			}
		});
		btnAlunos.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnEquipe = new JButton("Equipe");
		btnEquipe.setForeground(new Color(255, 255, 255));
		btnEquipe.setBackground(new Color(153, 180, 209));
		btnEquipe.setBorderPainted(false);
		btnEquipe.addActionListener(new ActionListener() {
			/**
			 * Faz as alteracoes necessarias para que apenas os componentes relacionados a "Alunos" sejam exibidos.
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				calendar.setVisible(false);
				menuAtual = Funcionario.getNOME_TABELA();
				btnUm.setText("Editar");
				btnDois.setText("Novo");
				addIcon.setVisible(true);
				iconEdit.setVisible(true);
				lblHorario.setVisible(false);
				textFieldHorario.setVisible(false);
				lblMensagem.setVisible(false);
				scrollPane.setVisible(true);
				
				DefaultTableModel model = (DefaultTableModel) tabelaFuncionarios.getModel();
				int numeroDeLinhasTabela = GetRowCount.getRowCount(Funcionario.getNOME_TABELA());
				int qtdeLinhasTabelaAtual = model.getRowCount();
				if (qtdeLinhasTabelaAtual != 0) {
					for (int i = qtdeLinhasTabelaAtual; i > 0; i--) {
						//System.out.println(i);
						model.removeRow(i-1);
					}
				}
				Funcionario funcionarioTabela = new Funcionario();
				if (numeroDeLinhasTabela >= qtdeLinhasTabelaAtual) {
					for (int i = 1; i <= numeroDeLinhasTabela; i++) {
						funcionarioTabela = Funcionario.read(i);
						model.addRow(new Object[]
				        		{funcionarioTabela.getId(), funcionarioTabela.getNome(), 
				        				funcionarioTabela.getFuncao(), 
				        				funcionarioTabela.getCidade(), funcionarioTabela.getTelefone()}
				        );
					}
				}
				if (funcionario.getNivelDeAcesso() > PERMISSAO_MINIMA_PARA_REGISTRAR_FUNCIONARIO) {
		        	btnDois.setEnabled(false);
		        	btnUm.setEnabled(false);
		        } else {
		        	btnDois.setEnabled(true);
		        	btnUm.setEnabled(true);
		        }
		        scrollPane.setViewportView(tabelaFuncionarios);
				revalidate(); //refresh
				repaint();
			}
		});
		btnEquipe.setFont(new Font("Tahoma", Font.BOLD, 20));
		
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
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(PanelMenu, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(menuExibicao, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(PanelMenu, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
				.addComponent(menuExibicao, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
		);
		GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
		gl_panel_2_1.setHorizontalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addComponent(lblNomeUsuario_1, GroupLayout.PREFERRED_SIZE, 55, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
		);
		gl_panel_2_1.setVerticalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2_1.createSequentialGroup()
					.addGap(55)
					.addComponent(lblNomeUsuario_1))
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addComponent(btnLogout))
		);
		panel_2_1.setLayout(gl_panel_2_1);
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
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(9))
		);
		gl_panelMenu.setVerticalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4_1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btnAgenda, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(btnAlunos, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btnEquipe, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(panel_3_2, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		PanelMenu.setLayout(gl_panelMenu);
		
		GroupLayout gl_menuExibicao = new GroupLayout(menuExibicao);
		gl_menuExibicao.setHorizontalGroup(
			gl_menuExibicao.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(90)
					.addComponent(btnUm, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnDois, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(186)
					.addComponent(textFieldHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(104)
					.addComponent(iconEdit, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(119))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(197)
					.addComponent(addIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(90)
					.addComponent(lblMensagem, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
				.addGroup(Alignment.TRAILING, gl_menuExibicao.createSequentialGroup()
					.addGap(90)
					.addComponent(lblHorario, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(10)
					.addComponent(calendar, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addGap(17))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addGap(17))
				.addComponent(fundo, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
		);
		gl_menuExibicao.setVerticalGroup(
			gl_menuExibicao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_menuExibicao.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUm)
						.addComponent(btnDois))
					.addGap(5)
					.addComponent(textFieldHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(39)
					.addComponent(iconEdit, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(39)
					.addComponent(addIcon, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(71)
					.addComponent(lblMensagem, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(39)
					.addComponent(lblHorario, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(96)
					.addComponent(calendar, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addGap(11))
				.addGroup(gl_menuExibicao.createSequentialGroup()
					.addGap(96)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addGap(11))
				.addComponent(fundo, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
		);
		menuExibicao.setLayout(gl_menuExibicao);
		setLayout(groupLayout);
		
	}
}
