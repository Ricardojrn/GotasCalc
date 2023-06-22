import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculadoraMedicacaoGUI extends JFrame {
    private JTextField textFieldGotas;
    private JButton buttonCalcular;
    private JLabel labelResultadoText30;
    private JLabel labelResultado30;
    private JLabel labelResultadoText60;
    private JLabel labelResultado60;
    private JComboBox<String> comboBoxMedicamento;

    public CalculadoraMedicacaoGUI() {
        setTitle("GotasCalc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 200);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textFieldGotas.requestFocus();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        comboBoxMedicamento = new JComboBox<>();
        comboBoxMedicamento.addItem("Clonazepam");
        comboBoxMedicamento.addItem("Fenobarbital");
        comboBoxMedicamento.addItem("Clorpromazina");
        comboBoxMedicamento.addItem("Haloperidol");

        JLabel labelGotas = new JLabel("Gotas diárias");
        textFieldGotas = new JTextField(5);
        buttonCalcular = new JButton("Calcular");

        labelResultadoText30 = new JLabel("30 dias");
        labelResultado30 = new JLabel(" ");
        labelResultadoText60 = new JLabel("60 dias");
        labelResultado60 = new JLabel(" ");

        // Define as cores preto e azul claro
        Color corCinza = new Color(221, 230, 237);
        Color corAzulClaro = new Color(90, 150, 227);

        panel.setBackground(corCinza);
        labelGotas.setForeground(corAzulClaro);
        textFieldGotas.setBackground(Color.WHITE);
        textFieldGotas.setForeground(Color.BLACK);
        buttonCalcular.setBackground(corAzulClaro);
        buttonCalcular.setForeground(corCinza);
        labelResultadoText30.setForeground(corAzulClaro);
        labelResultado30.setForeground(corAzulClaro);
        labelResultadoText60.setForeground(corAzulClaro);
        labelResultado60.setForeground(corAzulClaro);

        buttonCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularFrascos();
            }
        });

        textFieldGotas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calcularFrascos();
                }
            }
        });

        // Container da primeira coluna (comboBoxMedicamento e buttonCalcular)
        JPanel column1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn1 = new GridBagConstraints();
        gbcColumn1.anchor = GridBagConstraints.EAST;
        gbcColumn1.insets = new Insets(5, 5, 5, 5);
        column1.setBackground(corCinza);

        gbcColumn1.gridx = 0;
        gbcColumn1.gridy = 0;
        column1.add(comboBoxMedicamento, gbcColumn1);

        gbcColumn1.gridx = 0;
        gbcColumn1.gridy = 1;
        column1.add(buttonCalcular, gbcColumn1);

// Container da segunda coluna (labelGotas, textFieldGotas)
        JPanel column2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn2 = new GridBagConstraints();
        gbcColumn2.anchor = GridBagConstraints.CENTER;
        gbcColumn2.insets = new Insets(11, 5, 5, 5);
        column2.setBackground(corCinza);

        gbcColumn2.gridx = 0;
        gbcColumn2.gridy = 0;
        column2.add(labelGotas, gbcColumn2);

        gbcColumn2.gridx = 0;
        gbcColumn2.gridy = 1;
        column2.add(textFieldGotas, gbcColumn2);

// Container da terceira coluna (labelResultadoText30, labelResultado30)
        JPanel column3 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn3 = new GridBagConstraints();
        gbcColumn3.anchor = GridBagConstraints.CENTER;
        gbcColumn3.insets = new Insets(11, 5, 7, 5);
        column3.setBackground(corCinza);

        gbcColumn3.gridx = 0;
        gbcColumn3.gridy = 0;
        column3.add(labelResultadoText30, gbcColumn3);

        gbcColumn3.gridx = 0;
        gbcColumn3.gridy = 1;
        column3.add(labelResultado30, gbcColumn3);

// Container da quarta coluna (labelResultadoText60, labelResultado60)
        JPanel column4 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn4 = new GridBagConstraints();
        gbcColumn4.anchor = GridBagConstraints.CENTER;
        gbcColumn4.insets = new Insets(11, 5, 7, 5);
        column4.setBackground(corCinza);

        gbcColumn4.gridx = 0;
        gbcColumn4.gridy = 0;
        column4.add(labelResultadoText60, gbcColumn4);

        gbcColumn4.gridx = 0;
        gbcColumn4.gridy = 1;
        column4.add(labelResultado60, gbcColumn4);

        // Adiciona os containers à tela principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(column1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(column2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(column3, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(column4, gbc);

        add(panel);

        setVisible(true);
    }

    private void calcularFrascos() {
        int gotasDiarias = Integer.parseInt(textFieldGotas.getText());
        double concentracao;
        double volumeFrasco;
        int duracaoTratamento30 = 30;

        String medicamento = (String) comboBoxMedicamento.getSelectedItem();

        if (medicamento.equals("Clonazepam")) {
            concentracao = 25;
            volumeFrasco = 20;
        } else if (medicamento.equals("Fenobarbital")) {
            concentracao = 40;
            volumeFrasco = 20;
        } else {
            concentracao = 0;
            volumeFrasco = 0;
        }

        int totalGotas30 = gotasDiarias * duracaoTratamento30;
        double totalMl30 = totalGotas30 / concentracao;
        double totalFrascos30 = totalMl30 / volumeFrasco;
        labelResultado30.setText(String.valueOf(totalFrascos30));

        double totalFrascos60 = totalFrascos30 * 2;
        labelResultado60.setText(String.valueOf(totalFrascos60));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraMedicacaoGUI();
            }
        });
    }
}
