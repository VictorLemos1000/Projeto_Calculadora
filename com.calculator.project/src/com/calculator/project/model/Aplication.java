package com.calculator.project.model;

import javax.swing.SwingUtilities;

import com.calculator.project.view.TelaCalculadora;

public class Aplication {

	// Invocação da interface para execução da calculadora
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new TelaCalculadora().setVisible(true);
			}
		});
	}
}
