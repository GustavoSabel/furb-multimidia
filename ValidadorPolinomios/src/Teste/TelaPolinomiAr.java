package Teste;

public class TelaPolinomiAr extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    public TelaPolinomiAr() {
        initComponents();
    }
                      
    private void initComponents() {

        btnValidar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSaida = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnValidar.setText("Validar");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });

        txtEntrada.setColumns(20);
        txtEntrada.setRows(5);
        txtEntrada.setText("x^2+2\n 2x+1\n 3x^3\n 10+2x^2+3x^10\n 2-2+10x+x150^2+x(42)x+xx^2x^3\n");
        
	//Validador.validar("10+2x^2+3x^10");
	//Validador.validar("2-2+10x+x150^2+x(42)x+xx^2x^3");
	
        txtEntrada.setToolTipText("Podem ser informados vários polinômios para validar. Quebrar uma linha para cada polinômio diferente.");
        txtEntrada.setName("Entrada"); // NOI18N
        jScrollPane1.setViewportView(txtEntrada);
        txtEntrada.getAccessibleContext().setAccessibleDescription("Podem ser informados vários polinômios para validar. Quebrar uma linha para cada polinômio diferente. Ex:\\n\nx^2+2\\n\n2x+1\\n\n3x^3\\n");

        txtSaida.setEditable(false);
        txtSaida.setColumns(20);
        txtSaida.setRows(5);
        txtSaida.setName("Saida"); // NOI18N
        txtSaida.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(txtSaida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnValidar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnValidar)
                .addGap(5, 5, 5))
        );

        pack();
    }                      

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {   
	txtSaida.setText("");
	String[] polinomios = txtEntrada.getText().split("\n");
	
	for (String polinomio : polinomios) {
	    if(Validador.validar(polinomio)){
		txtSaida.append("Válido" + System.lineSeparator());
	    }else{
		txtSaida.append("Inválido" + System.lineSeparator());
	    }
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
            java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPolinomiAr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPolinomiAr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnValidar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtEntrada;
    private javax.swing.JTextArea txtSaida;
    // End of variables declaration                   
}