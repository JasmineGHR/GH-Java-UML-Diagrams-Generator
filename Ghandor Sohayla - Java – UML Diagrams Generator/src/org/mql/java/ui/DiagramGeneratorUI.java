package org.mql.java.ui;
import javax.swing.*;

import org.mql.java.models.Project;
import org.mql.java.xml.dom.ProjetParser;
import org.mql.java.xml.dom.XMLSerializer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DiagramGeneratorUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField projectNameField;

    public DiagramGeneratorUI() {
        initUI();
    }

    private void initUI() {
        // Créer les composants Swing
        JLabel projectNameLabel = new JLabel("Nom du Projet:");
        projectNameField = new JTextField(20);
        JButton generateButton = new JButton("Générer Diagrammes");

        // Ajouter un gestionnaire d'événements au bouton
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateDiagrams();
            }
        });

        // Organiser les composants dans le conteneur
        JPanel panel = new JPanel();
        panel.add(projectNameLabel);
        panel.add(projectNameField);
        panel.add(generateButton);

        // Configurer la fenêtre
        add(panel);
        setTitle("Générateur de Diagrammes");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void generateDiagrams() {
        // Récupérer le nom du projet depuis le champ de saisie
        String projectName = projectNameField.getText().trim();

        if (!projectName.isEmpty()) {
            // Générer le projet et le fichier XML ou XMI
            generateProject(projectName);

            // Charger et parcourir le fichier pour générer les diagrammes
            parseAndGenerateDiagrams(projectName);

            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Diagrammes générés avec succès!");
        } else {
            // Afficher un message d'erreur si le champ est vide
            JOptionPane.showMessageDialog(this, "Veuillez saisir un nom de projet.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateProject(String projectName) {
        Project project= new Project(projectName) ;
        project.ProjetInfo();
        XMLSerializer serializer= new XMLSerializer() ;
        serializer.serializeToXML(project, "output/projet.xml");
    }

    private void parseAndGenerateDiagrams(String projectName) {
    	ProjetParser parser=new ProjetParser() ;
        Project proj=parser.parse("output/projet.xml") ;
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DiagramGeneratorUI ui = new DiagramGeneratorUI();
                ui.setVisible(true);
            }
        });
    }
}
