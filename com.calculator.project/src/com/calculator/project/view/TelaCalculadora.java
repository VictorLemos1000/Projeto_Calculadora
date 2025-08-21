package com.calculator.project.view;

import javax.swing.JFrame;
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
	
	private void configurarJanela() {
		// TODO Auto-generated method stub

	}
	
	private void inicializarComponentes() {
		// TODO Auto-generated method stub

	}
}
