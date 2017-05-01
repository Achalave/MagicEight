
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
    
    public static JFrame frame;
    public static JPanel mainPanel;
    public static CardLayout layout;
    
    public static void main(String[] args){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainPanel = new JPanel();
        layout = new CardLayout();
        mainPanel.setLayout(layout);
        frame.add(mainPanel);
        
        //Add things to main panel card layout
        GameView view = new GameView();
        mainPanel.add(view);
        frame.setVisible(true);
        view.start();
    }
    
    public static void setVisiblePanel(String key){
        layout.show(frame, key);
    }
}
