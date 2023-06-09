package interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.LogicaGlobal;

public class CompraBoletos extends JFrame{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JPanel panelTodo;
		private JPanel panelMatrizBotones;
		private JButton matrizBotones [][];
		private JPanel lblCabina;
		private JButton registrarCliente;
		private JButton botonPresionado;
		agregarClientes datos;
		LogicaGlobal logcompras;
		private JButton matrizBotones2[][];
		private String silla;
		private String numeroSillas;
		private String[][] MatrizOcupadoIdpref;
		private String[][] MatrizOcupadoIdnorm;
		
		public CompraBoletos() {
			setSize(1280, 720);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			logcompras= new LogicaGlobal();
			crearPanelTodo();
			crearPanelMatrizBotones();
			crearPanelSuperior();
			crearPanelInferior();
			}

			

		public void crearPanelTodo () {
			silla= new String();
			panelTodo = new JPanel();
			panelTodo.setLayout(new BorderLayout(5, 5));
			setContentPane(panelTodo);
		}
		public void crearPanelMatrizBotones () {
			panelMatrizBotones = new JPanel();
			panelMatrizBotones.setLayout(new GridLayout(7, 6, 10, 10));
			matrizBotones = new JButton[7][6];
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					// j da el numero  y lo ancho
					// i da la letra  y la altura
					char letra = (char) ('C' + i);
					int numero = j + 1;
					matrizBotones[i][j] = new JButton(letra + "" + numero);
					if(j==0 || j==5) {
						matrizBotones[i][j].setBackground(Color.CYAN);
					}
					if(j==1 || j==4) {
						matrizBotones[i][j].setBackground(Color.green);
					}
					if(j==2 || j==3) {
						matrizBotones[i][j].setBackground(Color.orange);
					}
					
					matrizBotones[i][j].addActionListener(new ActionListener() {
						int aux1=numero-1;
						@Override
						public void actionPerformed (ActionEvent e) {
							JButton botonPresionado = (JButton) e.getSource();
							if (!botonPresionado.getBackground().equals(Color.red)) {
								botonPresionado.setBackground(Color.red);
							} else {
								if(aux1==0 || aux1==5) {
									botonPresionado.setBackground(Color.CYAN);	
								}
								if(aux1==1 || aux1==4) {
									botonPresionado.setBackground(Color.green);
								}
								if(aux1==2 || aux1==3) {
									botonPresionado.setBackground(Color.orange);
								}
							}
						}
					});
					panelMatrizBotones.add(matrizBotones[i][j]);
				}
			}
			panelTodo.add(panelMatrizBotones, BorderLayout.CENTER);
		}


		public void crearPanelSuperior () {
			lblCabina = new JPanel();
			lblCabina.setLayout(new GridLayout(4, 3, 10, 10));
			 matrizBotones2=new JButton[4][3];
			for(int i=0;i<4;i++) {
				for(int j=0;j<2;j++) {
					char letra= (char) ('A'+j);
					int numero=i+1;
					// j da la letra
					// i da el numero
					matrizBotones2[i][j]= new JButton (letra+""+numero);
					matrizBotones2[i][j].setBackground(Color.blue);
					matrizBotones2[i][j].addActionListener(new ActionListener(){
					
						/*
						 *  se encarga de evitar que el numero pierda su color 
						 * y al mismo tiempo lo modifica al ser presionado
						 */
					@Override
					public void actionPerformed(ActionEvent e) {
						 botonPresionado = (JButton) e.getSource();
						if (!botonPresionado.getBackground().equals(Color.red)) {
							botonPresionado.setBackground(Color.red);
						} else {
							botonPresionado.setBackground(Color.BLUE);
					}
					}});
					lblCabina.add(matrizBotones2[i][j]);	
				}
				
			}
			
			panelTodo.add(lblCabina, BorderLayout.NORTH);
		}
		public void crearPanelInferior () {
			MatrizOcupadoIdpref= new String[matrizBotones.length][matrizBotones[0].length];
			MatrizOcupadoIdnorm= new String[matrizBotones2.length][matrizBotones2[0].length];
			JButton btnBack = new JButton("Volver");
			JPanel panelInferior = new JPanel();
			registrarCliente= new JButton("Registrar Cliente");
			panelInferior.setLayout(new BorderLayout());
			panelInferior.add(btnBack, BorderLayout.EAST);
			panelInferior.add(registrarCliente,BorderLayout.CENTER );
			panelTodo.add(panelInferior, BorderLayout.SOUTH);
			CompraBoletos informacion =this;
			registrarCliente.addActionListener(new ActionListener(){
				
				/*
				 * param: recibe un evento
				 * return: no retorna pero muestra la ventana agregarCliente
				 */
				@Override
				public void actionPerformed (ActionEvent e) {
					boolean matriz1= logcompras.VerificarPuestosOcupado(matrizBotones) ;
					boolean matriz2= logcompras.VerificarPuestosOcupado(matrizBotones2);
					int centinal=0;
				if( matriz1 || matriz2 && centinal<=0) {
					//String [] nuevosDatos=datos.agregarData();
					//MatrizOcupadoIdpref=logcompras.OcupadorUser(matrizBotones, nuevosDatos[0]);
					//MatrizOcupadoIdnorm=logcompras.OcupadorUser(matrizBotones2,nuevosDatos[0]);
						centinal++;
						JOptionPane.showMessageDialog(null, "recuerde que las azules tienen el valor de 50000 y las de la parte inferior cuestan 25000");	
						silla=logcompras.TipoSilla(matriz1, matriz2, matrizBotones, matrizBotones2);
						numeroSillas=logcompras.devolverSillas(matriz1,matriz2,matrizBotones, matrizBotones2);
						agregarClientes agregarClientes = new agregarClientes(informacion);
						agregarClientes.setVisible(true);	
					
			}
				else {
					JOptionPane.showMessageDialog(informacion, "por favor tome un puesto");
				}
				}

			});
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed (ActionEvent e) {
					System.exit(0);
				}
			
			});}
		
		public String EnviarTiposilla() {
			return silla;
		}
		public String enviarNumeroSillas() {
			/*
			 * ocupadas derecha libres izquierda
			 */
			System.out.println(numeroSillas);
			return numeroSillas;
		}
		public String[][] devolverMatrizpref(){
			return MatrizOcupadoIdpref;
		}
		public String[][] devolverMatriznorm(){
			return MatrizOcupadoIdnorm;
		}
		}
