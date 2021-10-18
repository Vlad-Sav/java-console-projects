package ru.krsu;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JFrame{
    private JButton button1 = new JButton("Check");
    private JButton button2 = new JButton("Max n");
    private JTextArea txtText = new JTextArea("", 30, 30);
    private JTextField txtNWords = new JTextField(5);
    static Main mainFrame;
    JTextArea area1 = new JTextArea(3, 15);

    static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> namesOfFiles = new ArrayList<>();
    private static String[] texts;
    private static String[] formattedTexts;
    private static String usersText;
    private static String[] words;
    private static int numberOfWords;
    private static double plagiarismPercentage;
    static {
        namesOfFiles.add("text1.txt");
        namesOfFiles.add("text2.txt");
        namesOfFiles.add("text3.txt");
        namesOfFiles.add("text4.txt");
        namesOfFiles.add("text5.txt");
        texts = new String[namesOfFiles.size()];
        formattedTexts = new String[namesOfFiles.size()];
        WorkWithText.readFiles(namesOfFiles, texts);
        WorkWithText.formatTexts(texts, formattedTexts);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new Main();

            }
        });
    }
    public Main(){
        super("anti-plagiarism");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        Font font = area1.getFont();
        float size = font.getSize() + 2.0f;
        area1.setFont( font.deriveFont(size) );
        txtText.setLineWrap(true);
        txtText.setWrapStyleWord(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersText = txtText.getText();
                if(usersText != null && !usersText.equals("")){
                    try {
                        numberOfWords = Integer.parseInt(txtNWords.getText());
                        if(numberOfWords < 20){
                            numberOfWords = 20;
                        }
                    }catch (Exception ee){
                        numberOfWords = 20;
                    }
                    words = WorkWithText.formatUsersText(usersText);
                    plagiarismPercentage = WorkWithText.antiPlagiarism(numberOfWords, words, Arrays.copyOf(formattedTexts, formattedTexts.length));
                    if(plagiarismPercentage > 1){
                        plagiarismPercentage = 1;
                    }
                    area1.setText(String.format("Plagiarism: %.2f %%",plagiarismPercentage * 100));
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersText = txtText.getText();
                if(usersText != null && !usersText.equals("")){
                    words = WorkWithText.formatUsersText(usersText);
                    int n = WorkWithText.findMaxN(words, Arrays.copyOf(formattedTexts, formattedTexts.length));
                    area1.setText("Max n = " + n);
                }
            }
        });
        setLayout(new FlowLayout());
        add(new JScrollPane(txtText));
        add(txtNWords);
        add(button1);
        add(button2);
        add(area1);
        setVisible(true);
    }


}
