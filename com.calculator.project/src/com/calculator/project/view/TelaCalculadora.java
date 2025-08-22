package com.calculator.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.calculator.project.model.Calculadora;

public class TelaCalculadora extends JFrame{

	// Estou chamando a classe calculadora para compor a tela como seu objeto
	private Calculadora calculadora;
	private JTextField displayField;
	
	public TelaCalculadora() {
		// TODO Auto-generated constructor stub
		this.calculadora = new Calculadora();
		configurarJanela();
		inicializarComponentes();
	}
	
	// Método para apliação e caracterização da tela da calculadora
	private void configurarJanela() {
		// TODO Auto-generated method stub
		setTitle(" Projeto - Calculadora"); // Exibe o nome do projeto
		setSize(320, 450); // Medida da tela em largura e altura
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a operação da tela padrão
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		// Configuração do layout principal
		setLayout(new BorderLayout(10, 10));
				
		// Painel do display
		JPanel painelDisplay = new JPanel(new BorderLayout());
		displayField = new JTextField("0");
		displayField.setEditable(false);
		displayField.setFont(new Font("Arial", Font.BOLD, 28)); // Estilização da fonte do display
		displayField.setHorizontalAlignment(JTextField.RIGHT);
		displayField.setBackground(new Color(240, 240, 240)); // Formato de cor RGB
		painelDisplay.add(displayField, BorderLayout.CENTER); // Centralização do display em tela
		
		// Painel de botões
		JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 5, 5));
		
		// Ordem de alinhamento dos botões
		String[] textoBotoes = {
				"7", "8", "9", "/",
				"4", "5", "6", "*",
				"1", "2", "3", "-",
				"0", ".", "=", "+",
				"C", "Histórico"
		};
		
		// Cria e adiciona botões
		for (String texto : textoBotoes) {
			JButton botao = criarBotao(texto);
			painelBotoes.add(botao);
		}
		
		// Adição de componente na tela
		add(painelDisplay, BorderLayout.NORTH);
		add(painelBotoes, BorderLayout.CENTER);
		
		// Adição de margem
		((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	private JButton criarBotao(String texto) {
		// TODO Auto-generated method stub
		JButton botao = new JButton(texto);
		botao.setFont(new Font("Arial", Font.BOLD, 10));
		botao.setFocusPainted(false);
		
		// Cores especificas para cada operação
		if (texto.matches("[/*\\-+=]")) {
			botao.setBackground(new Color(240, 165, 0));// Cor laranja para as operações
			botao.setForeground(Color.WHITE);
		} else if (texto.equals("C") || texto.equals("Histórico")) {// Se a o texto não for igual a uma das condições passa pra o teste falso
			botao.setBackground(new Color(169, 169, 169)); // Cor cinza para o resultado ou hisórico de operações
		} else {
			botao.setBackground(new Color(240, 240, 240)); // Conza claro para os números
		}
		
		botao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				// TODO Auto-generated method stub
				tratarCliqueBotao(texto);
			}
		});
		
		return botao;
	}
	
	private void tratarCliqueBotao(String texto) {
		// TODO Auto-generated method stub
		try {
			switch (texto) {
			// Cada case desta condição é um número a ser clicado
			case "0":
				case "1":
					case "2":
						case "3":
							case "4":
								case "5":
									case "6":
										case "7":
											case "8":
												case "9":
				calculadora.adicionarDigito(texto);
				break;

				case ".":
					calculadora.adicionarPonto();
					break;
					
				case "+":
					case "-":
						case "*":
							case "/":
					calculadora.setOperadorPendente(texto);
					break;
					
				case "=":
					calculadora.calcular();
					break;
					
				case "C":
					calculadora.limparDisplay();
					break;
				
				case "Histórico":
					exibirHistórico();
					break;
					
				case "⌫":
					// O backspace ele remove o últgimo dígito
					break;
				
				case "±":
					// Troca de sinal espeífico para números negativos
					break;
			}
			
			atualizarDisplay();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Erro" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizarDisplay() {
		// TODO Auto-generated method stub
		displayField.setText(calculadora.getDisplay());
	}
	
	private void exibirHistórico() {
		// TODO Auto-generated method stub
		// Estilização da exibição do histórico
//		JDialog dialogo = new JDialog(this, " Histórico de Operações\n", true);
//		dialogo.setSize(400, 300);
//		dialogo.setLocationRelativeTo(this);
//		dialogo.setLayout(new BorderLayout(10, 10));
//		
//		// Painel principal com margem
//		JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
//		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		
//		// Título
//		JLabel titulo = new JLabel("Histórico de operações", JLabel.CENTER);
//		titulo.setFont(new Font("Arial", Font.BOLD, 16));
//		titulo.setForeground(new Color(0, 51, 102));
//		
//		// Área de texto em formatação
		JTextArea areaTexto = new JTextArea();
		areaTexto.setEditable(false);
		areaTexto.setFont(new Font("Monospace", Font.PLAIN, 14));
		areaTexto.setBackground(new Color(240, 248, 248)); // Fundo cinza
		
		// Preenchimento com operações
		StringBuilder historico = new StringBuilder();
		
		if (calculadora.getHistoridoDeOperacoes().isEmpty()) {// Se o ohistórico de operações estiver vázio			
			historico.append(" Nenhuma operação registrada ainda.\n");
			historico.append(" Use a calculadora para ver os resultados aqui!");
		} else {
			historico.append(String.format("%-30s %-15s\n", "Operação", "Resuldado"));
	        historico.append("----------------------------------------------\n");

			for (String operacao : calculadora.getHistoridoDeOperacoes()) {
				String linhaFormatada = formatarLinhaHistorico(operacao);
				historico.append("• ").append(linhaFormatada).append("\n");
			}
		}
		
		JOptionPane.showMessageDialog(this, historico.toString(), "Histórico", JOptionPane.INFORMATION_MESSAGE);
//		
//		areaTexto.setText(historico.toString());
//		areaTexto.setCaretPosition(0); // Scroll para o topo da tela
//		
//		// Adicionando Scroll
//		JScrollPane scrollPane = new JScrollPane(areaTexto);
//		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
//		
//		// Painel de botões
//		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		
//		JButton botaoLimpar = new JButton(" Limpar");
//		botaoLimpar.addActionListener(e -> {
//			int resposta = JOptionPane.showConfirmDialog(dialogo, "Deseja limpar todo o histórico?", "Confirmar", JOptionPane.YES_NO_OPTION);
//			
//			if (resposta == JOptionPane.YES_OPTION) {
//				calculadora.limparHistorico();
//				dialogo.dispose();
//				JOptionPane.showMessageDialog(this, "Histórico limpo!", "Informações", JOptionPane.INFORMATION_MESSAGE);
//			}
//		});
//		
//		JButton botaoFechar = new JButton("Fechar");
//		botaoFechar.addActionListener(e -> dialogo.dispose());
//		
//		// Estilização dos botões
//		botaoLimpar.setBackground(new Color(255, 200, 200));
//		botaoFechar.setBackground(new Color(200, 255, 200));
//		
//		painelBotoes.add(botaoLimpar);
//		painelBotoes.add(botaoFechar);
//		
//		// Apresentação de dialogo na tela
//		painelPrincipal.add(titulo, BorderLayout.NORTH);
//		painelBotoes.add(scrollPane, BorderLayout.CENTER);
//		painelBotoes.add(painelBotoes, BorderLayout.SOUTH);
//		
//		dialogo.add(painelPrincipal);
//		dialogo.setVisible(true);
//		
	}
	
	// Método para exibição melhorada 
	private String formatarLinhaHistorico(String operacao) {
		// TODO Auto-generated method stub
		String operacaoLimpa = operacao.trim();
		
		String[] partes = operacaoLimpa.split(" = ");
		if (partes.length == 2) {
			return String.format("%-25s = %10s", partes[0], partes[1]);
		}
		
		return String.format("%-40s", operacaoLimpa);
	}
}
