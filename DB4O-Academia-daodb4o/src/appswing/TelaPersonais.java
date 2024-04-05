
package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Aluno;
import modelo.Funcionario;
import modelo.Personal;
import regras_negocio.Fachada;


public class TelaPersonais {

    private JDialog frame;

    private JLabel label_2;
    private JLabel label_3;
    private JLabel lblLargura;
    private JLabel lblAltura;

    private JScrollPane scrollPane;
    private JTable table;

    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_4;

    private JButton button_1;
    private JButton button_3;
    private JButton button_4;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPersonais window = new TelaPersonais();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPersonais() {
        initialize();
    }

    private void initialize() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setTitle("Listar Personais");
        frame.setBounds(100, 100, 813, 428);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent arg0) {
                Fachada.inicializar();
                listagem(); // Chama o método listagem() para preencher a tabela ao abrir a janela
            }
            
        	public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
        });

      


      
        table = new JTable() {
        	
        	public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (table.getSelectedRow() >= 0) {
                    	String nome = (String) table.getValueAt(table.getSelectedRow(), 0);
                        
                    	Funcionario f = Fachada.localizarFuncionario(nome);
                        textField_1.setText(nome);
                        label_2.setText("");
                    }
                } catch (Exception erro) {
                    label_2.setText(erro.getMessage());
                }
            }
        });

       
        // Configuração da tabela
      
        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // Criação do modelo da tabela com as colunas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Salario");
        model.addColumn("Horario");
    
        // Define o modelo da tabela
        table.setModel(model);

        // Criação do JScrollPane e configuração
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(21, 34, 751, 182);
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setViewportView(table); // Define a tabela como conteúdo do JScrollPane

        // Adiciona o JScrollPane ao painel principal da janela
        frame.getContentPane().add(scrollPane);


        
        button_1 = new JButton("Criar");
        button_1.setToolTipText("Cadastrar novo Personal");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                	
                	
                	String nome = textField_1.getText().trim();
                	if (nome.isEmpty()) {
                	    throw new Exception("Nome não pode estar vazio.");
                	  
                	}
                	
                	
                	
                    double salario = Double.parseDouble(textField_2.getText().trim());
                    String horario = textField_4.getText().trim();

                    // Geração de matrícula e data de vencimento
               
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String data = sdf.format(new Date()); // Obtém a data atual no formato "dd/MM/yyyy"


                    Fachada.criarPersonal(nome, data, salario, horario);
                    label_2.setText("Personal criado");
                    listagem();
                } catch (Exception ex) {
                    label_2.setText( ex.getMessage());
                }
               

            }
        });
        button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        button_1.setBounds(87, 325, 86, 23);
        frame.getContentPane().add(button_1);

        button_3 = new JButton("Limpar");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText("");
                textField_2.setText("");
                textField_4.setText("");
            }
        });
        button_3.setBounds(330, 325, 89, 23);
        frame.getContentPane().add(button_3);

        button_4 = new JButton("Apagar");
        button_4.setToolTipText("Apagar Personal e seus dados");
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textField_1.getText();
                    int escolha = JOptionPane.showConfirmDialog(null, "Esta operação apagará os dados do  Personal. Deseja continuar?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (escolha == JOptionPane.YES_OPTION) {
                        Fachada.excluirPersonal(nome);
                        label_2.setText("Personal excluído");
                        listagem();
                    } else {
                        label_2.setText("Exclusão cancelada");
                    }
                } catch (Exception erro) {
                    label_2.setText(erro.getMessage());
                }
            }
        });
        button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        button_4.setBounds(219, 325, 74, 23);
        frame.getContentPane().add(button_4);

        label_2 = new JLabel("");
        label_2.setBounds(59, 360, 434, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("Nome:");
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label_3.setBounds(12, 220, 62, 14);
        frame.getContentPane().add(label_3);

        lblLargura = new JLabel("Horario:");
        lblLargura.setHorizontalAlignment(SwingConstants.LEFT);
        lblLargura.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblLargura.setBounds(12, 285, 71, 22);
        frame.getContentPane().add(lblLargura);

        lblAltura = new JLabel("Salario:");
        lblAltura.setBounds(10, 250, 46, 14);
        frame.getContentPane().add(lblAltura);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField_1.setColumns(10);
        textField_1.setBackground(Color.WHITE);
        textField_1.setBounds(87, 220, 86, 20);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField_2.setColumns(10);
        textField_2.setBounds(87, 250, 86, 20);
        frame.getContentPane().add(textField_2);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField_4.setColumns(10);
        textField_4.setBounds(87, 285, 86, 20);
        frame.getContentPane().add(textField_4);
        frame.setVisible(true);
    }

    public void listagem() {
        try {
            // Criação do modelo da tabela com as colunas
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nome");
            model.addColumn("Horario");
            model.addColumn("Salario");
       
            
            // Preenchimento do modelo com os dados atualizados dos alunos
            List<Personal> lista = Fachada.listarProfessores();
            for (Personal ps : lista) {
                model.addRow(new Object[]{ps.getNome(),ps.getHorario(),ps.getSalario() });
            }

            // Define o modelo atualizado na tabela
            table.setModel(model);

            // Redesenha a tabela
            table.repaint();
            
            label_2.setText("Resultados: " + lista.size() + " Personais - Selecione uma linha para editar");
            
            // Redimensiona automaticamente as colunas da tabela
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        } catch (Exception erro) {
            label_2.setText(erro.getMessage());
        }
    }


}
