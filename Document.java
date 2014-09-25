import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import java.io.*;

public class Document extends JFrame implements ActionListener
{
    
RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

JFileChooser fc = new JFileChooser();

private JTextArea ta;
private int count;
private JMenuBar menuBar;
private JMenu fileM;
private JMenuItem exitI,saveI;
private String pad;
private JToolBar toolBar;

public Document()
{

super("Text Editor");
setSize(600, 600);
setLocationRelativeTo(null); 
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

Container pane = getContentPane();
pane.setLayout(new BorderLayout());

textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
textArea.setCodeFoldingEnabled(true);
RTextScrollPane sp = new RTextScrollPane(textArea);
pane.add(sp);

count = 0;
pad = " ";
ta = new JTextArea(); //textarea

menuBar = new JMenuBar(); //menubar
fileM = new JMenu("File"); //file menu
exitI = new JMenuItem("Exit");  //menu items on File Menu
saveI = new JMenuItem("Save");  //menu items on File Menu
toolBar = new JToolBar();

ta.setLineWrap(true);
ta.setWrapStyleWord(true);

setJMenuBar(menuBar);
menuBar.add(fileM); // "FILE" on the menu bar
fileM.add(saveI); // "Save" on the menu item for FILE
fileM.add(exitI); // "Exit" on the menu item for FILE

saveI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

pane.add(toolBar,BorderLayout.SOUTH);

saveI.addActionListener(this);
exitI.addActionListener(this);

setVisible(true);

}

void writetofile(File ff) throws Exception
{
        FileWriter fw = new FileWriter(ff.getAbsoluteFile());
             BufferedWriter bw = new BufferedWriter(fw);
             bw.write(textArea.getText());
             bw.close(); 
}

public void actionPerformed(ActionEvent e) 
{

JMenuItem choice = (JMenuItem) e.getSource();

if (choice == saveI)
{

 int returnVal = fc.showSaveDialog(Document.this);

    if (returnVal == JFileChooser.APPROVE_OPTION) 
    {
        try
        {
         File file = fc.getSelectedFile();
         writetofile(file);
     	}
     	catch(Exception esa)
    	{

     	}

    }    
}
else if (choice == exitI)
    System.exit(0);
}

public static void main(String[] args) 
{
new Document();
}

}
