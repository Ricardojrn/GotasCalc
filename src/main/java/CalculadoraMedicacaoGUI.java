import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculadoraMedicacaoGUI extends JFrame {
    private JTextField textFieldGotas;
    private JComboBox<String> comboBoxMedicamentos;
    private JLabel labelResultado30;
    private JLabel labelResultado60;

//  Define as cores usadas na aplicação //
    Color corBranco = new Color(255,255,255);
    Color corAzul = new Color(90, 150, 227);

    public CalculadoraMedicacaoGUI() {
        setTitle("GotasCalc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(470, 250);
        setLocation(450,250);
//  Define o ícone da janela
        ImageIcon icon = new ImageIcon("img/img_icon.png");
        Image resizedIcon = icon.getImage().getScaledInstance(32, 28, Image.SCALE_SMOOTH);
        setIconImage(resizedIcon);

        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textFieldGotas.requestFocus();
                }
        });

        comboBoxMedicamentos = new JComboBox<>();
        comboBoxMedicamentos.addItem("Clonazepam");
        comboBoxMedicamentos.addItem("Fenobarbital");
        comboBoxMedicamentos.addItem("Clorpromazina");
        comboBoxMedicamentos.addItem("Haloperidol");

        JLabel labelGotas = new JLabel("Gotas diárias");
        labelGotas.setForeground(corAzul);
        textFieldGotas = new JTextField(5);
        textFieldGotas.setBackground(corBranco);
        textFieldGotas.setForeground(Color.BLACK);

        JLabel labelText30 = new JLabel("30 dias");
        labelResultado30 = new JLabel(" ");
        labelText30.setForeground(corAzul);
        JLabel labelText60 = new JLabel("60 dias");
        labelResultado60 = new JLabel(" ");
        labelText60.setForeground(corAzul);

        JButton buttonCalcular = new JButton("Calcular");
        buttonCalcular.setBackground(corAzul);
        buttonCalcular.setForeground(corBranco);

//  Aciona a função calcularFrasco a partir do botão calcular
        buttonCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularFrascos();
            }
        });
//  Aciona a função calcularFrasco a partir do botão ENTER
        textFieldGotas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calcularFrascos();
                }
            }
        });

        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setBackground(corBranco);

        JPanel panelCalculadora = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCalculadora.setBackground(corBranco);
        panelCalculadora.setBorder(BorderFactory.createLineBorder(corAzul));

//  Container da primeira coluna (comboBoxMedicamento e buttonCalcular)
        JPanel coluna1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn1 = new GridBagConstraints();
        gbcColumn1.anchor = GridBagConstraints.EAST;
        gbcColumn1.insets = new Insets(5, 5, 5, 5);
        coluna1.setBackground(corBranco);

        gbcColumn1.gridx = 0;
        gbcColumn1.gridy = 0;
        coluna1.add(comboBoxMedicamentos, gbcColumn1);

        gbcColumn1.gridx = 0;
        gbcColumn1.gridy = 1;
        coluna1.add(buttonCalcular, gbcColumn1);

//  Container da segunda coluna (labelGotas, textFieldGotas)
        JPanel column2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn2 = new GridBagConstraints();
        gbcColumn2.anchor = GridBagConstraints.CENTER;
        gbcColumn2.insets = new Insets(11, 5, 5, 5);
        column2.setBackground(corBranco);

        gbcColumn2.gridx = 0;
        gbcColumn2.gridy = 0;
        column2.add(labelGotas, gbcColumn2);

        gbcColumn2.gridx = 0;
        gbcColumn2.gridy = 2;
        column2.add(textFieldGotas, gbcColumn2);

//  Container da terceira coluna (labelResultadoText30, labelResultado30)
        JPanel column3 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn3 = new GridBagConstraints();
        gbcColumn3.anchor = GridBagConstraints.CENTER;
        gbcColumn3.insets = new Insets(10, 5, 7, 5);
        column3.setBackground(corBranco);

        gbcColumn3.gridx = 0;
        gbcColumn3.gridy = 0;
        column3.add(labelText30, gbcColumn3);

        gbcColumn3.gridx = 0;
        gbcColumn3.gridy = 1;
        column3.add(labelResultado30, gbcColumn3);

//  Container da quarta coluna (labelResultadoText60, labelResultado60)
        JPanel column4 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcColumn4 = new GridBagConstraints();
        gbcColumn4.anchor = GridBagConstraints.CENTER;
        gbcColumn4.insets = new Insets(10, 5, 7, 5);
        column4.setBackground(corBranco);

        gbcColumn4.gridx = 0;
        gbcColumn4.gridy = 0;
        column4.add(labelText60, gbcColumn4);

        gbcColumn4.gridx = 0;
        gbcColumn4.gridy = 1;
        column4.add(labelResultado60, gbcColumn4);

//  Adicionando os containers à tela calculadora
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCalculadora.add(coluna1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCalculadora.add(column2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelCalculadora.add(column3, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panelCalculadora.add(column4, gbc);
        jTabbedPane.addTab("Calculadora", panelCalculadora);

//  Painel de informações
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(corBranco);
        panelInfo.setBorder(BorderFactory.createLineBorder(corAzul));

        JLabel labelInfo = new JLabel("Informações");
        labelInfo.setFont(new Font("Arial", Font.BOLD, 16));
        labelInfo.setForeground(corAzul);
        panelInfo.add(labelInfo);
        //  TABELA
        String [] colunas = {"Medicamento","Concentração","Gotas por mL","Volume Frasco"};
        Object [][] dados ={
                {"Clonazepam","2,5 mg/mL","25 gts","20 mL"},
                {"Fenobarbital","40 mg/mL","40 gts","20 mL"},
                {"Clorpromazina","40 mg/mL","40 gts","20 mL"},
                {"Haloperidol","20 mg/mL","20 gts","20 mL"}
        };
        JTable tabela = new JTable(dados,colunas);
        tabela.setBackground(corBranco);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(440, 87));
        panelInfo.add(scrollPane);

        JLabel labelFormula = new JLabel("(gotas/dia x qtd dias)  /  (gotas/ml x v_frasco)");
        labelFormula.setFont(new Font("Cambria Math", Font.BOLD, 16));
        labelFormula.setForeground(corAzul);
        panelInfo.add(labelFormula);
/*
        ImageIcon imgFormula = new ImageIcon("img/formula.png");
        JLabel labelFormula = new JLabel(imgFormula);
        panelInfo.add(labelFormula);
*/
        jTabbedPane.addTab("Info", panelInfo);

        add(jTabbedPane);
        setVisible(true);
    }

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }
    private void calcularFrascos() {

        if (isInteger(textFieldGotas.getText()) == true){
            int gotasDiarias = Integer.parseInt(textFieldGotas.getText());
            double concentracao;
            double volumeFrasco;
            int duracaoTratamento30 = 30;

            String medicamento = (String) comboBoxMedicamentos.getSelectedItem();

            if (medicamento.equals("Clonazepam")) {
                concentracao = 25;
                volumeFrasco = 20;
            } else if (medicamento.equals("Fenobarbital")) {
                concentracao = 40;
                volumeFrasco = 20;
            } else if (medicamento.equals("Clorpromazina")){
                concentracao = 40;
                volumeFrasco = 20;
            } else if(medicamento.equals("Haloperidol")){
                concentracao = 20;
                volumeFrasco = 20;
            } else {
                concentracao = 0;
                volumeFrasco = 0;
            }

            int totalGotas30 = gotasDiarias * duracaoTratamento30;
            double totalMl30 = totalGotas30 / concentracao;
            double totalFrascos30 = totalMl30 / volumeFrasco;
            labelResultado30.setText(String.valueOf(totalFrascos30));
            labelResultado30.setForeground(corAzul);

            double totalFrascos60 = totalFrascos30 * 2;
            labelResultado60.setText(String.valueOf(totalFrascos60));
            labelResultado60.setForeground(corAzul);
        }else
        {
            labelResultado30.setText("Valor");
            labelResultado30.setForeground(Color.RED);
            labelResultado60.setText("Inválido");
            labelResultado60.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraMedicacaoGUI();
            }
        });
    }
}
