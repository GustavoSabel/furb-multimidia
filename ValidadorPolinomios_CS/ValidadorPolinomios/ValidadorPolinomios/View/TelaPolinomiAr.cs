using System;
using System.Text;

namespace View
{

	using AnalysisError = Analisador.AnalysisError;
	using Polinomio = Polinomio.Polinomio;

	public class TelaPolinomiAr : javax.swing.JFrame
	{

		private const long serialVersionUID = 1L;

		public TelaPolinomiAr()
		{
			initComponents();
		}

		private void initComponents()
		{

			btnValidar = new javax.swing.JButton();
			jScrollPaneEntrada = new javax.swing.JScrollPane();
			txtEntrada = new javax.swing.JTextArea();
			jScrollPaneSaida = new javax.swing.JScrollPane();
			txtSaida = new javax.swing.JTextArea();

			DefaultCloseOperation = javax.swing.WindowConstants.EXIT_ON_CLOSE;

			btnValidar.Text = "Validar";
			btnValidar.addActionListener(new ActionListenerAnonymousInnerClassHelper(this));

			StringBuilder str = new StringBuilder();
			str.Append("x^2 + 2").Append("\n");
			str.Append("2x + 1").Append("\n");
			str.Append("3x^(3").Append("\n");
			str.Append("10+2x^2+3x^10").Append("\n");
			str.Append("2-2+10x+x150^2+x(42)x+xx^2x^3").Append("\n");
			str.Append("2^(-2^-5)^5+x^x^4").Append("\n");
			str.Append("x^y^-y^w^+a").Append("\n");
			str.Append("10+(((5)))").Append("\n");
			str.Append("10*(((5)))").Append("\n");
			str.Append("1^100*x").Append("\n");
			str.Append("(2x^2+2)").Append("\n");
			str.Append("(2+2x^2)").Append("\n");

			txtEntrada.Text = str.ToString();

			txtEntrada.ToolTipText = "Podem ser informados vários polinômios para validar. Quebrar uma linha para cada polinômio diferente.";
			jScrollPaneEntrada.ViewportView = txtEntrada;
			txtEntrada.AccessibleContext.AccessibleDescription = "Podem ser informados vários polinômios para validar. Quebrar uma linha para cada polinômio diferente. Ex:\\n\nx^2+2\\n\n2x+1\\n\n3x^3\\n";

			txtSaida.Text = "Pressione \"Validar\" para validar os polinômios";
			txtSaida.Editable = false;
			txtSaida.RequestFocusEnabled = false;
			jScrollPaneSaida.ViewportView = txtSaida;

			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(ContentPane);
			ContentPane.Layout = layout;
			layout.HorizontalGroup = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPaneEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 300, short.MaxValue).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPaneSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 300, short.MaxValue)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(btnValidar).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, short.MaxValue));
			layout.VerticalGroup = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPaneSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 400, short.MaxValue).addComponent(jScrollPaneEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 400, short.MaxValue)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnValidar).addGap(5, 5, 5));

			pack();
		}

		private class ActionListenerAnonymousInnerClassHelper : java.awt.@event.ActionListener
		{
			private readonly TelaPolinomiAr outerInstance;

			public ActionListenerAnonymousInnerClassHelper(TelaPolinomiAr outerInstance)
			{
				this.outerInstance = outerInstance;
			}

			public virtual void actionPerformed(java.awt.@event.ActionEvent evt)
			{
				outerInstance.btnValidarActionPerformed(evt);
			}
		}

		private void btnValidarActionPerformed(java.awt.@event.ActionEvent evt)
		{
			try
			{
				txtSaida.Text = "";
				string[] polinomios = txtEntrada.Text.split("\n");

				foreach (string polinomio in polinomios)
				{
					if (polinomio.Length > 0)
					{
						if (Polinomio.validar(polinomio))
						{
							txtSaida.append("Válido: " + Polinomio.criarPolinomio(polinomio).simplificar().ordenar().ToString() + Environment.NewLine);
						}
						else
						{
							txtSaida.append("Inválido" + Environment.NewLine);
						}
					}
				}
			}
			catch (AnalysisError e)
			{
				e.printStackTrace();
			}
		}

		public static void Main(string[] args)
		{
			try
			{
				foreach (javax.swing.UIManager.LookAndFeelInfo info in javax.swing.UIManager.InstalledLookAndFeels)
				{
					if ("Nimbus".Equals(info.Name))
					{
						javax.swing.UIManager.LookAndFeel = info.ClassName;
						break;
					}
				}
			}
			catch (ClassNotFoundException ex)
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				java.util.logging.Logger.getLogger(typeof(TelaPolinomiAr).FullName).log(java.util.logging.Level.SEVERE, null, ex);
			}
			catch (InstantiationException ex)
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				java.util.logging.Logger.getLogger(typeof(TelaPolinomiAr).FullName).log(java.util.logging.Level.SEVERE, null, ex);
			}
			catch (IllegalAccessException ex)
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				java.util.logging.Logger.getLogger(typeof(TelaPolinomiAr).FullName).log(java.util.logging.Level.SEVERE, null, ex);
			}
			catch (javax.swing.UnsupportedLookAndFeelException ex)
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				java.util.logging.Logger.getLogger(typeof(TelaPolinomiAr).FullName).log(java.util.logging.Level.SEVERE, null, ex);
			}
			// </editor-fold>

			/* Create and display the form */
			java.awt.EventQueue.invokeLater(() =>
			{
				(new TelaPolinomiAr()).Visible = true;
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
}