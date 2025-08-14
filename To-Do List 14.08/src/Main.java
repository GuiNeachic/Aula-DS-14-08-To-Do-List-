import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private DefaultListModel<String> modeloLista;
    private JList<String> listaTarefas;
    private JTextField campoTarefa;
    private JButton btnAdicionar, btnConcluir, btnRemover;

    //Método Construtor
    public Main() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloLista);
        listaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTarefas.setFont(new Font("Arial", Font.PLAIN, 14));

        campoTarefa = new JTextField();
        btnAdicionar = new JButton("Adicionar");
        btnConcluir = new JButton("Concluir");
        btnRemover = new JButton("Remover");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 3, 5, 5));
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnConcluir);
        painelBotoes.add(btnRemover);

        add(new JScrollPane(listaTarefas), BorderLayout.CENTER);
        add(campoTarefa, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.SOUTH);

        //Evento para adicionar tarefa
        btnAdicionar.addActionListener(e -> {
            String tarefa = campoTarefa.getText().trim();
            if (!tarefa.isEmpty()) {
                modeloLista.addElement(tarefa);
                campoTarefa.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Digite uma tarefa!");
            }
        });

        //Evento para marcar como concluída
        btnConcluir.addActionListener(e -> {
            int indice = listaTarefas.getSelectedIndex();
            if (indice != -1) {
                String tarefa = modeloLista.getElementAt(indice);
                if (!tarefa.startsWith("[✔] ")) {
                    modeloLista.setElementAt("[✔] " + tarefa, indice);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma tarefa!");
            }
        });

        //Evento para remover tarefa
        btnRemover.addActionListener(e -> {
            int indice = listaTarefas.getSelectedIndex();
            if (indice != -1) {
                modeloLista.remove(indice);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma tarefa para remover!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
