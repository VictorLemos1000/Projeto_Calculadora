package com.calculator.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A calculadora tem que ter no mínimo por padrão
 * método das 4 operações básicas da matemática
 * "," para operações decimais.
 */
public class Calculadora {

	// O resultado ele recebe atribuições das operações.
	private float resultado;
	
	// Atributo para armazenar uma lista do histórico de operações adicionadas.
	// A lista é do tipo String pois vai armazenar e atribuir valores em formato de caracteres.
	private List<String> historidoDeOperacoes;
	
	// Construtor para inialição dos atributos.
	public Calculadora() {
		// TODO Auto-generated constructor stub
		
		// O resultado de operações inicializa com 0.
		this.resultado = 0;
		/*
		 * E a lista dos histórico ele recebe um arrayList vázio como inicializador
		 * depois ele vai alocando uma operação.
		 */
		this.historidoDeOperacoes = new ArrayList<>();
	}
	
	// Métodos de operações básicas.
	public float adcicao(float parcela1, float parcela2) {
		// TODO Auto-generated method stub
		
		// Realização da operação
		resultado = parcela1 + parcela2;
		// Cada operação matemática tem String como formato
		String operacao = String.format(" %.2f + %.2f = %.2f", parcela1, parcela2, resultado);
		
		// Armazenmendo de uma operação armazenada na lsta.
		historidoDeOperacoes.add(operacao);
		return resultado;
	}
	
	/*
	 * O cabeçalho de cada operação matemática tem como parâmetro o nome de cada
	 * valor padrão dos operandos.
	 */
	public float subtracao(float minuendo, float subtraendo) {
		// TODO Auto-generated method stub
		resultado = minuendo - subtraendo;
		String operacao = String.format(" %.2f - %.2f = %.2f", minuendo, subtraendo, resultado);
		
		historidoDeOperacoes.add(operacao);
		return resultado;
	}
	
	public float multiplicacao(float fator1, float fator2) {
		// TODO Auto-generated method stub
		resultado = fator1 * fator2;
		String operacao = String.format(" %.2f * %.2f = %.2f", fator1, fator2, resultado);
		
		historidoDeOperacoes.add(operacao);
		return resultado;
	}
	
	public float divisao(float dividendo, float divisor) {
		// TODO Auto-generated method stub
		
		/*
		 * O divisor da operação não pode ser 0,
		 * e dispara a exceção de tratamento aritimético.
		 */
		if (divisor == 0) {
			throw new ArithmeticException(" A operação não pode ser divisível por 0.");
		}		

		resultado = dividendo / divisor;
		String operacao = String.format(" %.2f / %.2f = %.2f", dividendo, divisor, resultado);
		
		historidoDeOperacoes.add(operacao);
		return resultado;
	}
	
	/*
	 * Este método ele está fazendo uma verificação exibir todas as operações
	 * primeiro ele ver se o histórico ele está vázio,
	 * caso contrário vair apresentar todas as operações listadas.
	 */
	public void exibirHistórico() {
		// TODO Auto-generated method stub
		if (historidoDeOperacoes.isEmpty()) {
			System.out.println(" Nenhuma operação registrada.");
		} else {
			System.out.println("\n Histótico de operações");
			for (String operação : historidoDeOperacoes) {
				System.out.println(operação);
			}
		}
	}
	
	// Atributos que vão compor um teclado para a interface da calculadora
	private String display = "0"; // No texto em tela o display deve exibir o inicializador como 0;
	private String valorAtual = ""; // Atributo armazena dos dígitos a serem digitados;
	private boolean aguardandoNovoNumero = true; // O flag para resetar o display
	
	public void adicionarDigito(String digito) {
		// TODO Auto-generated method stub
		if (aguardandoNovoNumero) { // Caso seja um novo número;
			display = digito; // Novo número a ser adicionado;
			aguardandoNovoNumero = false; // Status para a concatenação de dígitos;
		} else { // Caso contrário;
			display += digito; // Concatenação do número atual;
		}
		valorAtual = display; // Atualiza um novo número a memória.
	}
	
	/*
	 * O método ele realiza em tela o display para seu estado inicial
	 * ou apaga um caractere específico, gerando uma atualização de valor em menmória
	 * e altera o estado do número atualizado.
	 */
	public void limparDisplay() {
		// TODO Auto-generated method stub
		display = "0";
		valorAtual = "";
		aguardandoNovoNumero = true;
	}
	
	/*
	 * Vrifica se o número inicializa com "0.", ou seja uma valor adicionado pra decimal
	 * e é adicionado "." se não houver na composição atualizando assim um valor decimal.
	 */
	public void adicionarPonto() {
		// TODO Auto-generated method stub
		if (aguardandoNovoNumero) {
			display = "0.";
			aguardandoNovoNumero = false;
		} else if (!display.contains(".")) {
			display += ".";
		}
		valorAtual = display;
	}
	
	/*
	 * Atributo essencial para manipular as operações matemáticas e
	 * distribuir distinguir um valor de outro
	 */
	private String operacaoPendente = "";	// Atribui um novo valor pós fatoração do operador 
	private float numero1 = 0; // Armazena o 1º valor da operação matemática
	
	public void setOperadorPendente(String operacao) {
		// TODO Auto-generated method stub
		if (!valorAtual.isEmpty()) { // Se o novo valor estiver não for vázio o bloco é executado. 
			numero1 = Float.parseFloat(valorAtual); // Salva o 1º valor
			operacaoPendente = operacao;
			aguardandoNovoNumero = true; // Prepara o próximo valor.
		}
	}
	
	public void calcular() {
		// TODO Auto-generated method stub
		// Executa a condição se não houver uma operação e é ignorado
		if (operacaoPendente.isEmpty() || valorAtual.isEmpty()) {
			return;
		}
		
		float numero2 = Float.parseFloat(valorAtual);
		resultado = 0;
		
		// Seleção para escolher um operador matemático
		switch (operacaoPendente) {
		case "+":
			resultado = numero1 + numero2;
			break;
		case "-":
			resultado = numero1 - numero2;
			break;
		case "*":
			resultado = numero1 * numero2;
			break;
		case "/":
			if (numero2 == 0) {
				display = "Erro";
				limparDisplay();
				return;
			}
			
			resultado = numero1 / numero2;
			break;			
		default:
			break;
		}
		String operacaoCompleta = String.format(" %.2f %s %.2f = %.2f", numero1, operacaoPendente, numero2, resultado);
		historidoDeOperacoes.add(operacaoCompleta);
		
		display = String.valueOf(resultado);
		valorAtual = display;
		operacaoPendente = ""; // Reset da operação;
		aguardandoNovoNumero = true;
	}
	
	public String getDisplay() {
		return display;
	}

	public List<String> getHistoridoDeOperacoes() {
		return historidoDeOperacoes;
	}
	
	public void limparHistorico() {
		historidoDeOperacoes.clear();
	}
}
