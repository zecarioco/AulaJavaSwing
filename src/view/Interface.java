package view;

import javax.swing.*;

public class Interface extends JFrame {
    public PainelInicio painelInicio = new PainelInicio();
    public PainelCRUD painelCRUD = new PainelCRUD();

    public Interface() {
        setTitle("Atividades Escolares");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        setContentPane(painelInicio);
        setVisible(true);
    }

    // Método para trocar o painel
    public void mostrarPainelCRUD() {
        setContentPane(painelCRUD);
        revalidate(); // atualiza o layout
        repaint();    // redesenha a janela
    }
}