import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;

class Processo {
    int id, tempoExecucao, tempoRestante, prioridade, quantum;

    public Processo(int id, int tempoExecucao, int prioridade, int quantum) {
        this.id = id;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoExecucao;
        this.prioridade = prioridade;
        this.quantum = quantum;
    }

    public boolean isFinalizado() {
        return tempoRestante == 0;
    }

    public void executar(int tempo) {
        tempoRestante = Math.max(0, tempoRestante - tempo);
    }
}

class Escalonador {

    Queue<Processo> processos;

    public Escalonador() {
        processos = new LinkedList<>();
    }

    public void adicionarProcesso(Processo p) {
        processos.add(p);
    }

    // Implementação do FCFS
    public void fcfs(JTextArea outputArea) {
        outputArea.append("Executando FCFS...\n");
        while (!processos.isEmpty()) {
            Processo p = processos.poll();
            while (!p.isFinalizado()) {
                p.executar(1);
                outputArea.append("Processo " + p.id + " executando...\n");
            }
            outputArea.append("Processo " + p.id + " finalizado.\n");
        }
    }

    // Implementação do Round-Robin
    public void roundRobin(JTextArea outputArea) {
        outputArea.append("Executando Round-Robin...\n");
        Queue<Processo> processosRoundRobin = new LinkedList<>(processos);
        while (!processosRoundRobin.isEmpty()) {
            Processo p = processosRoundRobin.poll();
            if (!p.isFinalizado()) {
                int tempoExecutado = Math.min(p.quantum, p.tempoRestante);
                p.executar(tempoExecutado);
                outputArea.append("Processo " + p.id + " executando (Quantum " + tempoExecutado + ")\n");

                if (!p.isFinalizado()) {
                    processosRoundRobin.add(p);
                } else {
                    outputArea.append("Processo " + p.id + " finalizado.\n");
                }
            }
        }
    }

    // Implementação de Job mais curto primeiro (Shortest Job First)
    public void sjf(JTextArea outputArea) {
        outputArea.append("Executando SJF...\n");
        PriorityQueue<Processo> filaPrioridade = new PriorityQueue<>(
            Comparator.comparingInt(p -> p.tempoExecucao)
        );

        filaPrioridade.addAll(processos);

        while (!filaPrioridade.isEmpty()) {
            Processo p = filaPrioridade.poll();
            while (!p.isFinalizado()) {
                p.executar(1);
                outputArea.append("Processo " + p.id + " executando...\n");
            }
            outputArea.append("Processo " + p.id + " finalizado.\n");
        }
    }

    // Implementação de Escalonamento por Prioridade
    public void escalonamentoPorPrioridade(JTextArea outputArea) {
        outputArea.append("Executando escalonamento por prioridade...\n");

        PriorityQueue<Processo> filaPrioridade = new PriorityQueue<>(
            Comparator.comparingInt(p -> p.prioridade)
        );

        filaPrioridade.addAll(processos);

        while (!filaPrioridade.isEmpty()) {
            Processo p = filaPrioridade.poll();
            while (!p.isFinalizado()) {
                p.executar(1);
                outputArea.append("Processo " + p.id + " executando com prioridade " + p.prioridade + "...\n");
            }
            outputArea.append("Processo " + p.id + " finalizado.\n");
        }
    }
}

public class Main extends JFrame {
    private JTextArea outputArea;
    private JTextField idField, execTimeField, priorityField, quantumField;
    private Escalonador escalonador;

    public Main() {
        escalonador = new Escalonador();

        setTitle("Escalonador de Processos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("ID do Processo:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Tempo de Execução:"));
        execTimeField = new JTextField();
        inputPanel.add(execTimeField);

        inputPanel.add(new JLabel("Prioridade:"));
        priorityField = new JTextField();
        inputPanel.add(priorityField);

        inputPanel.add(new JLabel("Quantum:"));
        quantumField = new JTextField();
        inputPanel.add(quantumField);

        JButton addButton = new JButton("Adicionar Processo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    int execTime = Integer.parseInt(execTimeField.getText());
                    int priority = Integer.parseInt(priorityField.getText());
                    int quantum = Integer.parseInt(quantumField.getText());
                    Processo p = new Processo(id, execTime, priority, quantum);
                    escalonador.adicionarProcesso(p);
                    outputArea.append("Processo " + id + " adicionado.\n");
                    idField.setText("");
                    execTimeField.setText("");
                    priorityField.setText("");
                    quantumField.setText("");
                } catch (NumberFormatException ex) {
                    outputArea.append("Erro: Insira valores válidos.\n");
                }
            }
        });
        
        JPanel buttonPanel = new JPanel();
        String[] escalonamentoOptions = {"FCFS", "Round-Robin", "SJF", "Prioridade"};
        for (String option : escalonamentoOptions) {
            JButton button = new JButton(option);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (option) {
                        case "FCFS":
                            escalonador.fcfs(outputArea);
                            break;
                        case "Round-Robin":
                            escalonador.roundRobin(outputArea);
                            break;
                        case "SJF":
                            escalonador.sjf(outputArea);
                            break;
                        case "Prioridade":
                            escalonador.escalonamentoPorPrioridade(outputArea);
                            break;
                    }
                }
            });
            buttonPanel.add(button);
        }

        add(inputPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}