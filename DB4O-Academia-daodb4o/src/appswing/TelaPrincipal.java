package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

public class TelaPrincipal {
    private static boolean telaPrincipalAberta = false; // Variável para controlar se a tela principal está aberta
    private JFrame frame;
    private JMenu mnAluno;
    private JMenu mnPersonais;
    private JLabel label;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    if (!telaPrincipalAberta) { // Verifica se a tela principal já está aberta
                        TelaPrincipal window = new TelaPrincipal();
                        window.frame.setVisible(true);
                        telaPrincipalAberta = true; // Atualiza a variável para indicar que a tela foi aberta
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    

    /**
     * Create the application.
     */
    public TelaPrincipal() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	frame = new JFrame();
    	frame.setTitle("Academia");
    	frame.setBounds(100, 100, 500, 400);
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.getContentPane().setLayout(null);
    	frame.setResizable(false);

    	label = new JLabel("");
    	label.setFont(new Font("Tahoma", Font.PLAIN, 26));
    	label.setHorizontalAlignment(SwingConstants.CENTER);
    	label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Ajuste aqui para as dimensões do JFrame
    	label.setText("Inicializando...");
    	ImageIcon imagem = new ImageIcon(getClass().getResource("/foto/1.png"));
    	imagem = new ImageIcon(imagem.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT)); // Ajuste aqui para as dimensões da imagem
    	label.setIcon(imagem);
    	frame.getContentPane().add(label);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        mnAluno = new JMenu("Aluno");
        mnAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaAlunos();
            }
        });
        menuBar.add(mnAluno);

        mnPersonais = new JMenu("Personais");
        mnPersonais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaPersonais();
            }
        });
        menuBar.add(mnPersonais);
    }
}
