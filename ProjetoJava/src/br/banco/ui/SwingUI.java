package br.banco.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.banco.fachada.Banco;
import br.banco.modelo.Conta;
import br.banco.modelo.ContaBancaria;
import br.banco.modelo.ContaCorrente;
import br.banco.modelo.ImageImplement;
import br.banco.modelo.excessoes.ContaJaCadastradaException;
import br.banco.modelo.excessoes.ContaNaoEncontradaException;
import br.banco.modelo.excessoes.SaldoInsuficienteException;
import br.banco.modelo.excessoes.ValorInvalidoException;
import br.banco.modelo.relatorio.GeradorPDF;
import br.banco.modelo.relatorio.RelatorioConta;
import br.banco.modelo.util.ContaTableModel;
import br.banco.persistencia.ConexaoBD;

public class SwingUI {
	
			private JFrame janela;
			private JPanel panelNorte;
			private JPanel panelSul;
			
			private JPanel panelCentro;
			private ImageImplement panel;
			
			private JFrame janelaCadastro;
			private JPanel panelCadastroCentro;
			private JLabel nomeLabel;
			private JTextField nomeField;
			private JTextField codigoField;
			private JLabel codigoLabel;
			private JPanel panelCadastrarSul;
			
			private JFrame janelaAlterar;
			private JPanel panelAlterarCentro;
			private JPanel panelAlterarSul;
			
			private JFrame janelaRemover;
			private JPanel panelRemoverCentro;
			private JLabel nomeRemoverLabel;
			private JTextField nomeRemoverField;
			private JPanel panelRemoverSul;
			
			private JFrame janelaRelatorio;
			private JPanel panelRelatorioCentro;
			private JPanel panelRelatorioSul;
			private JPanel panelRelatorioNorte;
			private JTextField nomeRelatorioField;
			private JLabel nomeRelatorioLabel;
			
			private JFrame opcRelatorio;
			private JPanel panelopcRelatorio;
			
			private JFrame frCreditar;
			private JPanel panelCreditarNorte;
			private JPanel panelCreditarSul;
			private JTextField numCreditarField;
			private JLabel numCreditarLabel;
			private JTextField valorCreditarField;
			private JLabel valorCreditarLabel;
			
			private JFrame frDebitar;
			private JPanel panelDebitarNorte;
			private JPanel panelDebitarSul;
			private JTextField numDebitarField;
			private JLabel numDebitarLabel;
			private JTextField valorDebitarField;
			private JLabel valorDebitarLabel;
			Banco bc = getBanco();

			public static void main(String[] args) {

				SwingUI principal = new SwingUI();
				principal.mostraTela();
			}

			/**
			 * Método que faz chamada aos principais componentes da minha tela.
			 */
			private void mostraTela() {
				
				preparaJanelaPrincipal();
				
				preparaPanelCentro();
				mostraPanelCentro();
				
				preparaPanelNorte();
				mostraPanelNorte();
				
				preparaPanelSul();
				mostraPanelSul();
				
				mostrarBotoesOpcoes();
				
				mostraJanelaPrincipal();
			}
					
			private void preparaPanelSul() {
				panelSul = new JPanel();
				panelSul.setLayout(null);
				panelSul.setBackground(Color.BLACK);
				panelSul.setPreferredSize(new Dimension(100, 40));
				JButton btSobre = new JButton("Sobre");
				btSobre.setBorder(null);
				btSobre.setBackground(Color.WHITE);
				btSobre.setSize(70, 20);
				btSobre.setLocation(700, 10);
				
				btSobre.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showConfirmDialog(janela, "Criadores do Projeto Desembestado:\n"
								+ "          José Welington Ideão\n"
								+ "          Kevin Mariz Macêdo\n"
								+ "   Vinicius Nascimento da Silva", "Sobre", JOptionPane.CLOSED_OPTION);
						
					}
				});
				panelSul.add(btSobre);
			}

			private void mostraPanelSul() {
				janela.getContentPane().add(panelSul, BorderLayout.SOUTH);
				
			}

					//preparando o painel Norte;
					private void preparaPanelNorte() {
						panelNorte = new JPanel(new GridLayout(1, 5));
						panelNorte.setPreferredSize(new Dimension(100, 125));
						panelNorte.getBaseline(200, 200);
					}
					
			//Adicionando o Painel Norte da Janela principal
					private void mostraPanelNorte() {
						janela.getContentPane().add(panelNorte, BorderLayout.NORTH);
					}

			//Botões do Painel Principal
			private void mostrarBotoesOpcoes() {
				JButton btcadastrar = new JButton(new ImageIcon("imagens/cadastro.png"));
				JButton btalterar = new JButton(new ImageIcon("imagens/moneyIcon.png"));
				JButton btremover = new JButton(new ImageIcon("imagens/remover.png"));
				JButton btrelatorio = new JButton(new ImageIcon("imagens/Relatorio.png"));
				
				btcadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						janelaCadastrar();
					}
				});
				
				btalterar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						janelaAlterar();
					}
				});
				
				btremover.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						janelaRemover();
					}
				});
				
				btrelatorio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						janelaRelatorio();
					}
				});
				
				panelNorte.add(btcadastrar);
				panelNorte.add(btalterar);
				panelNorte.add(btremover);
				panelNorte.add(btrelatorio);
				
			}
	//Janela Relatório
			private void janelaRelatorio() {
				janelaRelatorio = new JFrame("Relatório");
				panelRelatorioNorte = new JPanel();
				panelRelatorioCentro = new JPanel();
				panelRelatorioSul = new JPanel();
				nomeRelatorioField = new JTextField(30);
				nomeRelatorioLabel = new JLabel("Informe o ID do Cliente");
			
				ContaTableModel contaTableModel = new ContaTableModel(bc.listarContas());
				JTable jtableConta = new JTable(contaTableModel);
				JScrollPane jscrollPane = new JScrollPane(jtableConta);
				jscrollPane.setPreferredSize(new Dimension(300,200));
				JButton btRelatorio = new JButton("Gerar Relatório da Conta");
				btRelatorio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
						int select = jtableConta.getSelectedRow();
						Conta c = contaTableModel.getConta(select);
						TipoRelatorio(c);
						}catch(ArrayIndexOutOfBoundsException e1){
							JOptionPane.showMessageDialog(frDebitar,"Conta não selecionada ", "Erro", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					});
				
				JButton btCancelar = new JButton("Cancelar");
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						janelaRelatorio.dispose();	
					}
				});
				
				JButton btpesquisar = new JButton("Pesquisar");
				btpesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(nomeRelatorioField.getText().equalsIgnoreCase("Todos")){
							contaTableModel.setDados(bc.listarContas());
						}else{	
							try {
								Conta c = bc.buscarConta(nomeRelatorioField.getText());
								contaTableModel.clearTable();
								contaTableModel.addRow(c);
							} catch (ContaNaoEncontradaException e1) {
								JOptionPane.showMessageDialog(frDebitar, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
							}
						}	
						
					}
				});
				
				panelRelatorioNorte.add(nomeRelatorioLabel);
				panelRelatorioNorte.add(nomeRelatorioField);
				panelRelatorioCentro.add(jscrollPane);
				panelRelatorioSul.add(btpesquisar);
				panelRelatorioSul.add(btRelatorio);
				panelRelatorioSul.add(btCancelar);
				
				janelaRelatorio.add(panelRelatorioNorte, BorderLayout.NORTH);
				janelaRelatorio.add(panelRelatorioSul, BorderLayout.SOUTH);
				janelaRelatorio.add(panelRelatorioCentro, BorderLayout.CENTER);
				
				janelaRelatorio.setIconImage(new ImageIcon("imagens/raio.png").getImage());
				janelaRelatorio.setResizable(false);
				janelaRelatorio.setSize(500, 300);
				janelaRelatorio.setLocationRelativeTo(null);
				janelaRelatorio.setVisible(true);
			}

			private void TipoRelatorio(Conta conta) {
				opcRelatorio = new JFrame("Tipo de Relatório");
				panelopcRelatorio = new JPanel();
				
				JButton btPDF = new JButton(new ImageIcon("Imagens/pdf.png"));
				btPDF.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GeradorPDF pdf = new GeradorPDF();
						pdf.criandoDocumento(conta);
						opcRelatorio.dispose();
						JOptionPane.showMessageDialog(frDebitar,"Relatorio em .pdf gerado com sucesso\n",
								"Relatório", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				
				JButton btTXT = new JButton(new ImageIcon("Imagens/TXT.png"));
				btTXT.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RelatorioConta rc = new RelatorioConta();
						opcRelatorio.dispose();
						rc.gerarRelatorio(conta);
						JOptionPane.showMessageDialog(frDebitar,"Relatorio em .txt gerado com sucesso\n",
								"Relatório", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				
				panelopcRelatorio.add(btTXT);
				panelopcRelatorio.add(btPDF);
				
				opcRelatorio.add(panelopcRelatorio, BorderLayout.CENTER);
				opcRelatorio.setIconImage(new ImageIcon("Imagens/raio.png").getImage());
				opcRelatorio.setResizable(false);
				opcRelatorio.setSize(300, 100);
				opcRelatorio.setLocationRelativeTo(null);
				opcRelatorio.setVisible(true);

			}				//Janela Remover
			protected void janelaRemover() {
				janelaRemover = new JFrame("Remover");
				panelRemoverCentro = new JPanel();
				panelRemoverCentro.setLayout(new FlowLayout());
				panelRemoverSul = new JPanel();
				nomeRemoverField = new JTextField(30);
				nomeRemoverLabel = new JLabel("Informe o codigo da conta");
				JButton btRemover = new JButton("Remover");
				btRemover.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String number  = nomeRemoverField.getText();
							bc.excluirConta(number);
							JOptionPane.showMessageDialog(janelaRemover, "Removido com sucesso", "Removido", JOptionPane.INFORMATION_MESSAGE);
							nomeRemoverField.setText("");
						} catch (ContaNaoEncontradaException e1) {
							JOptionPane.showMessageDialog(janelaRemover, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
							nomeRemoverField.setText("");
						} 
					}
				});
				
				panelRemoverCentro.add(nomeRemoverLabel);
				panelRemoverCentro.add(nomeRemoverField);
				panelRemoverSul.add(btRemover);
				janelaRemover.add(panelRemoverSul, BorderLayout.SOUTH);
				janelaRemover.add(panelRemoverCentro, BorderLayout.CENTER);
				janelaRemover.setIconImage(new ImageIcon("imagens/raio.png").getImage());
				janelaRemover.setResizable(false);
				janelaRemover.setSize(500, 100);
				janelaRemover.setLocationRelativeTo(null);
				janelaRemover.setVisible(true);
			}

	//Janela Alterar
			private void janelaAlterar() {
				janelaAlterar = new JFrame("Alterar e Modificar");
				panelAlterarCentro = new JPanel();
				panelAlterarSul = new JPanel();
				
				JButton btDebitar = new JButton("Debitar", new ImageIcon("imagens/menos.png"));
				btDebitar.setBackground(Color.WHITE);
				
				JButton btCreditar = new JButton("Creditar", new ImageIcon("imagens/mais.png"));
				btCreditar.setBackground(Color.WHITE);
				
				JButton btCancelar = new JButton("Cancelar");
				
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						janelaAlterar.dispose();	
					}
				});
				
				btDebitar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Chamada do metodo debitar
						debitar();
						
					}
				});
				
				btCreditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Chamada do metodo creditar
						creditar();
						
					}
				});
				
				panelAlterarCentro.add(btCreditar);
				panelAlterarCentro.add(btDebitar);
				panelAlterarSul.add(btCancelar);
				janelaAlterar.add(panelAlterarCentro, BorderLayout.CENTER);
				janelaAlterar.add(panelAlterarSul, BorderLayout.SOUTH);
				janelaAlterar.setIconImage(new ImageIcon("imagens/raio.png").getImage());
				janelaAlterar.setResizable(false);
				janelaAlterar.setSize(500, 125);
				janelaAlterar.setLocationRelativeTo(null);
				janelaAlterar.setVisible(true);
			}
			
	//Janela Creditar
	private void creditar() {
				frCreditar = new JFrame("Creditar");
				panelCreditarNorte = new JPanel(new FlowLayout());
				panelCreditarSul = new JPanel();
				
				numCreditarField = new JTextField(27);
				numCreditarLabel =   new JLabel("Informe o codigo da conta");
				valorCreditarField = new JTextField(27);
				valorCreditarLabel = new JLabel("Informe o valor a creditar  ");
				
				JButton btSalvar = new JButton("Salvar");
				btSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Conta c = bc.buscarConta(numCreditarField.getText());
								c.creditar(Float.parseFloat(valorCreditarField.getText()));
								bc.modificarConta(c);
								JOptionPane.showMessageDialog(null,"Creditado com sucesso", "Bem Sucedida", JOptionPane.INFORMATION_MESSAGE);
						} catch (ValorInvalidoException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
						} catch (ContaNaoEncontradaException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
						}catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Formato não aceito", "Erro", JOptionPane.INFORMATION_MESSAGE);
						}
							
						numCreditarField.setText("");
						valorCreditarField.setText("");
					}
				});
				
				
				panelCreditarNorte.add(numCreditarLabel);
				panelCreditarNorte.add(numCreditarField);
				panelCreditarNorte.add(valorCreditarLabel);
				panelCreditarNorte.add(valorCreditarField);
				
				panelCreditarSul.add(btSalvar);
				
				frCreditar.add(panelCreditarNorte);
				frCreditar.add(panelCreditarSul, BorderLayout.SOUTH);
				frCreditar.setIconImage(new ImageIcon("Imagens/raio.png").getImage());
				frCreditar.setResizable(false);
				frCreditar.setSize(500, 150);
				frCreditar.setLocationRelativeTo(null);
				frCreditar.setVisible(true);
				
			}

	//Janela de Debitar
	private void debitar() {
				frDebitar = new JFrame("Debitar");
				panelDebitarNorte = new JPanel(new FlowLayout());
				panelDebitarSul = new JPanel();
				
				numDebitarField = new JTextField(27);
				numDebitarLabel = new JLabel("Informe o codigo da conta");
				valorDebitarField = new JTextField(27);
				valorDebitarLabel = new JLabel("Informe o valor a Debitar    ");
				
				JButton btSalvar = new JButton("Salvar");
				btSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Conta c = bc.buscarConta(numDebitarField.getText());
							c.debitar(Float.parseFloat(valorDebitarField.getText()));
							bc.modificarConta(c);
							JOptionPane.showMessageDialog(frDebitar, "Debitado com Sucesso", "Bem Sucedido", JOptionPane.INFORMATION_MESSAGE);
							
						} catch (ContaNaoEncontradaException e1) {
							JOptionPane.showMessageDialog(frDebitar, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
						} catch (SaldoInsuficienteException e1) {
							JOptionPane.showMessageDialog(frDebitar, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
						} catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(frDebitar, "Formato não aceito", "Erro", JOptionPane.INFORMATION_MESSAGE);
						}
						numDebitarField.setText("");
						valorDebitarField.setText("");
					}
				});
				
				panelDebitarNorte.add(numDebitarLabel);
				panelDebitarNorte.add(numDebitarField);
				panelDebitarNorte.add(valorDebitarLabel);
				panelDebitarNorte.add(valorDebitarField);
				
				panelDebitarSul.add(btSalvar);
				
				frDebitar.add(panelDebitarNorte);
				frDebitar.add(panelDebitarSul, BorderLayout.SOUTH);
				frDebitar.setIconImage(new ImageIcon("Imagens/raio.png").getImage());
				frDebitar.setResizable(false);
				frDebitar.setSize(500, 150);
				frDebitar.setLocationRelativeTo(null);
				frDebitar.setVisible(true);
			}

	//Janela Cadastrar
			private void janelaCadastrar() {
				janelaCadastro = new JFrame("Cadastrar");
				panelCadastroCentro = new JPanel();
				panelCadastrarSul = new JPanel();
				panelCadastroCentro.setLayout(new FlowLayout());
				codigoField = new JTextField(40);
				codigoLabel = new JLabel("Codigo");
				nomeField = new JTextField(40);
				nomeLabel = new JLabel("Nome  ");
				
				JButton btSalvar = new JButton("Salvar");
				btSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Conta c = new ContaCorrente(nomeField.getText(),codigoField.getText());
							if(!((c.getNomeCliente().equals("")||c.getNumero().equals("")))){
							bc.cadastrarConta(c);
							JOptionPane.showMessageDialog(frCreditar, "Cadastrado com Sucesso", "Salvo", JOptionPane.INFORMATION_MESSAGE);
							nomeField.setText("");
							codigoField.setText("");
							}else
								JOptionPane.showMessageDialog(frCreditar, "Existem campos vazios", "Erro", JOptionPane.INFORMATION_MESSAGE);
						} catch (ContaJaCadastradaException e1) {
							JOptionPane.showMessageDialog(frCreditar, e1.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
						   nomeField.setText("");
						}
					}
				});
				panelCadastroCentro.add(nomeLabel);
				panelCadastroCentro.add(nomeField);
				panelCadastroCentro.add(codigoLabel);
				panelCadastroCentro.add(codigoField);
				panelCadastrarSul.add(btSalvar);
				janelaCadastro.add(panelCadastroCentro, BorderLayout.CENTER);
				janelaCadastro.add(panelCadastrarSul, BorderLayout.SOUTH);
				janelaCadastro.setIconImage(new ImageIcon("imagens/raio.png").getImage());
				janelaCadastro.setResizable(false);
				janelaCadastro.setSize(500, 125);
				janelaCadastro.setLocationRelativeTo(null);
				janelaCadastro.setVisible(true);
				
			}
			
	//Imagem Central no Painel Norte		
			private void preparaPanelCentro() {
				panelCentro = new JPanel();
				panelCentro.setBackground(Color.BLACK);
				panel = new ImageImplement(new ImageIcon("imagens/telaCenter.jpg").getImage());
				panelCentro.add(panel);
			}
			
			private void mostraPanelCentro() {
				janela.add(panelCentro, BorderLayout.CENTER);
			}
			
	//preparando a Janela Principal;
			private void preparaJanelaPrincipal() {
				janela = new JFrame("El Dorado");
				janela.setIconImage(new ImageIcon("imagens/raio.png").getImage());
				janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}  

	//Mostrar a Janela Principal;
			private void mostraJanelaPrincipal() {
				janela.setResizable(false);
				janela.setSize(800, 600);
				janela.setLocationRelativeTo(null);
				janela.setVisible(true);
			}
			private Banco getBanco(){
				return bc = new Banco(new ContaBancaria(ConexaoBD.getInstance()));
			}
}
