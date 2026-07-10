import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ProfilePanel extends JPanel {

    private JTextField collegeField;
    private JTextField cityField;
    private JTextArea bioArea;

    private JPanel knownSkillsPanel;
    private JPanel learningSkillsPanel;

    private JCheckBox[] knownBoxes;
    private JCheckBox[] learningBoxes;

    private JButton saveButton;

    private User currentUser;

    private final String[] ALL_SKILLS = {

            "Java",
            "Python",
            "C++",
            "C",
            "JavaScript",
            "HTML",
            "CSS",
            "SQL",
            "Flutter",
            "Android",
            "AI",
            "Machine Learning",
            "Data Structures",
            "Algorithms",
            "Cyber Security",
            "Cloud",
            "UI/UX",
            "React",
            "NodeJS",
            "Spring Boot"

    };

    private MainFrame mainFrame;
    public ProfilePanel(MainFrame mainFrame) {

    this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("👤 My Profile");
        title.setFont(Theme.TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Theme.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        collegeField = new JTextField();

        cityField = new JTextField();

        bioArea = new JTextArea(4,20);
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);

        knownSkillsPanel = createSkillPanel(true);

        learningSkillsPanel = createSkillPanel(false);

        int row = 0;

        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("College"), gbc);

        gbc.gridx = 1;
        form.add(collegeField, gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("City"), gbc);

        gbc.gridx = 1;
        form.add(cityField, gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Bio"), gbc);

        gbc.gridx = 1;
        form.add(new JScrollPane(bioArea), gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTH;
        form.add(new JLabel("Skills I Know"), gbc);

        gbc.gridx = 1;

        JScrollPane knownScroll = new JScrollPane(knownSkillsPanel);
        knownScroll.setPreferredSize(new Dimension(350,180));

        form.add(knownScroll, gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Skills I Want To Learn"), gbc);

        gbc.gridx = 1;

        JScrollPane learningScroll = new JScrollPane(learningSkillsPanel);
        learningScroll.setPreferredSize(new Dimension(350,180));

        form.add(learningScroll, gbc);

        add(form, BorderLayout.CENTER);

        saveButton = new JButton("💾 Save Profile");
        saveButton.setFont(Theme.BUTTON_FONT);
        saveButton.setBackground(Theme.PRIMARY);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> saveProfile());

    }

    private JPanel createSkillPanel(boolean known){

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0,2,10,10));

        panel.setBackground(Color.WHITE);

        JCheckBox[] boxes = new JCheckBox[ALL_SKILLS.length];

        for(int i=0;i<ALL_SKILLS.length;i++){

            JCheckBox box = new JCheckBox(ALL_SKILLS[i]);

            box.setBackground(Color.WHITE);
            box.setFont(Theme.NORMAL_FONT);
            box.setFocusPainted(false);

            boxes[i] = box;

            panel.add(box);

        }

        if(known)
            knownBoxes = boxes;
        else
            learningBoxes = boxes;

        return panel;

    }

    public void loadUser(User user){

        currentUser = user;

        collegeField.setText(user.getCollege());

        cityField.setText(user.getCity());

        bioArea.setText(user.getBio());

        // Clear all selections

        for(JCheckBox box : knownBoxes)
            box.setSelected(false);

        for(JCheckBox box : learningBoxes)
            box.setSelected(false);

        // Reload previous selections

        for(String skill : user.getSkillsKnown()){

            for(JCheckBox box : knownBoxes){

                if(box.getText().equals(skill))
                    box.setSelected(true);

            }

        }

        for(String skill : user.getSkillsLearning()){

            for(JCheckBox box : learningBoxes){

                if(box.getText().equals(skill))
                    box.setSelected(true);

            }

        }

    }

    private void saveProfile(){

        if(currentUser == null)
            return;

        currentUser.setCollege(collegeField.getText());

        currentUser.setCity(cityField.getText());

        currentUser.setBio(bioArea.getText());

        ArrayList<String> knownSkills = new ArrayList<>();

        for(JCheckBox box : knownBoxes){

            if(box.isSelected())
                knownSkills.add(box.getText());

        }

        ArrayList<String> learningSkills = new ArrayList<>();

        for(JCheckBox box : learningBoxes){

            if(box.isSelected())
                learningSkills.add(box.getText());

        }

        currentUser.setSkillsKnown(knownSkills);

        currentUser.setSkillsLearning(learningSkills);

        mainFrame.refreshApplication();
        JOptionPane.showMessageDialog(
                this,
                "✅ Profile Updated Successfully!"
        );

    }

}