package view;

import javax.swing.*;
import java.awt.*;

public class PainelInicio extends JPanel {
    public JButton botaoIniciar = new JButton("Iniciar");

    public PainelInicio() {
        // Cor padrão
        Color cor = new Color(123, 50,250);

        // Define layout e espaçamento
        setLayout(new BorderLayout(10, 10));
        setBackground(cor);

        // Título estilizado
        JLabel titulo = new JLabel("Gerenciador de Atividades Escolares", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 29));
        titulo.setForeground(new Color(34, 70, 55));

        // Botão estilizado
        botaoIniciar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        botaoIniciar.setForeground(Color.BLACK);
        botaoIniciar.setFocusPainted(true);
        botaoIniciar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Painel auxiliar centralizado para o botão
        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new FlowLayout());
        painelBotao.setBackground(cor);
        painelBotao.add(botaoIniciar);

        // Adiciona componentes
        add(titulo, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    }
}