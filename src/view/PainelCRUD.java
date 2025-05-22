package view;

import javax.swing.*;
import model.Atividade;
import java.awt.*;

public class PainelCRUD extends JPanel {
    public JTextField campoNome = new JTextField(20);
    public JTextField campoDesc = new JTextField(20);
    public JTextField campoData = new JTextField(10);
    public JButton botaoAdicionar = new JButton("Adicionar");
    public JButton botaoRemover = new JButton("Remover");
    public JButton botaoEditar = new JButton("Editar");
    public DefaultListModel<Atividade> modeloLista = new DefaultListModel<>();
    public JList<Atividade> lista = new JList<>(modeloLista);
    private int indiceEdicao = -1;

    public PainelCRUD() {
        Color cor = new Color(123, 50,250);
        Color cor2 = new Color(167, 122, 245);
        setLayout(new BorderLayout(10, 10));
        setBackground(cor);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel do formulário com GridBagLayout para controle detalhado
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(cor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNome = new JLabel("Nome:");
        JLabel labelDesc = new JLabel("Descrição:");
        JLabel labelData = new JLabel("Data:");

        Font fonte = new Font("Segoe UI", Font.PLAIN, 14);
        labelNome.setFont(fonte);
        labelDesc.setFont(fonte);
        labelData.setFont(fonte);
        campoNome.setFont(fonte);
        campoDesc.setFont(fonte);
        campoData.setFont(fonte);

        botaoAdicionar.setFont(fonte);
        botaoRemover.setFont(fonte);
        botaoEditar.setFont(fonte);
        botaoAdicionar.setForeground(Color.BLACK);
        botaoRemover.setForeground(Color.BLACK);
        botaoEditar.setForeground(Color.BLACK);
        botaoAdicionar.setFocusPainted(false);
        botaoRemover.setFocusPainted(false);
        botaoEditar.setFocusPainted(false);
        botaoAdicionar.setOpaque(true);
        botaoRemover.setOpaque(true);
        botaoEditar.setOpaque(true);
        botaoAdicionar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botaoRemover.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botaoEditar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(labelNome, gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painelFormulario.add(labelDesc, gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoDesc, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        painelFormulario.add(labelData, gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoData, gbc);

        // Painel para botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
        painelBotoes.setBackground(cor);
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoEditar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        painelFormulario.add(painelBotoes, gbc);

        // Lista
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lista.setBackground(cor2);

        add(painelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
    }

    public int getIndiceEdicao(){
        return indiceEdicao;
    }

    public void setIndiceEdicao(int i){
        indiceEdicao = i;
    }
}