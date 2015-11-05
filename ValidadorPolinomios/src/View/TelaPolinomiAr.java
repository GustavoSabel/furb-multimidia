package View;

import Analisador.AnalysisError;
import Polinomio.Polinomio;

public class TelaPolinomiAr extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	public TelaPolinomiAr() {
		initComponents();
	}

	private void initComponents() {

		btnValidar = new javax.swing.JButton();
		jScrollPaneEntrada = new javax.swing.JScrollPane();
		txtEntrada = new javax.swing.JTextArea();
		jScrollPaneSaida = new javax.swing.JScrollPane();
		txtSaida = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		btnValidar.setText("Validar");
		btnValidar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnValidarActionPerformed(evt);
			}
		});

		txtEntrada.setText(
				"x^2 + 2 \n2x + 1 \n3x^(3 \n10+2x^2+3x^10 \n2-2+10x+x150^2+x(42)x+xx^2x^3 \n2^(-2^-5)^5+x^x^4 \nx^y^-y^w^+a");

		txtEntrada.setToolTipText(
				"Podem ser informados vários polinômios para validar. Quebrar uma linha para cada polinômio diferente.");
		jScrollPaneEntrada.setViewportView(txtEntrada);
		txtEntrada.getAccessibleContext().setAccessibleDescription(
				"Podem ser informados vários polinômios para validar. Quebrar uma linha para cada polinômio diferente. Ex:\\n\nx^2+2\\n\n2x+1\\n\n3x^3\\n");

		txtSaida.setText("Pressione \"Validar\" para validar os polinômios");
		txtSaida.setEditable(false);
		txtSaida.setRequestFocusEnabled(false);
		jScrollPaneSaida.setViewportView(txtSaida);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jScrollPaneEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 300,
										Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
										jScrollPaneSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(btnValidar)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPaneSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 400,
										Short.MAX_VALUE)
						.addComponent(jScrollPaneEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnValidar)
						.addGap(5, 5, 5)));

		pack();
	}

	private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			txtSaida.setText("");
			String[] polinomios = txtEntrada.getText().split("\n");

			for (String polinomio : polinomios) {
				if (Polinomio.validar(polinomio)) {
					txtSaida.append("Válido: " + Polinomio.criarPolinomio(polinomio).toString(false, null)
							+ System.lineSeparator());
				} else {
					txtSaida.append("Inválido" + System.lineSeparator());
				}
			}
		} catch (AnalysisError e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaPolinomiAr().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnValidar;
	private javax.swing.JScrollPane jScrollPaneEntrada;
	private javax.swing.JScrollPane jScrollPaneSaida;
	private javax.swing.JTextArea txtEntrada;
	private javax.swing.JTextArea txtSaida;
	// End of variables declaration
}