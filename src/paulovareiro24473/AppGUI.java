package paulovareiro24473;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import paulovareiro24473.guiComponents.TransparentJPanel;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.MenuState;
import paulovareiro24473.states.State;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.shop.ShopState;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class AppGUI {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel borderLabel;
	public static JTextField textField;
	public static JTextArea textArea;
	public static JScrollPane scrollPane;
	public static  JLabel topHeader;
	
	public static ArrayList<State> states = new ArrayList<State>();
	public static STATE currentState = STATE.menu;

	
	private int mouseX,mouseY;
	private Color transparent = new Color(0, 0, 0,0);
	private Color backgroundColor = new Color(29,20,21);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while(textArea == null){
			System.out.println("Loading game..");
		}
		Logic.loadFiles();
		addState(new MenuState());
		addState(new StoryState());
		addState(new ShopState());
		Logic.cls();
		while(true){
			for(State s : states){
				if(s.getState().equals(currentState)){
					s.run();
				}
			}
		}
	}

	
	public static void toggleContinue(){
		states.get(0).getOptions()[0].setName("Continue Story");
		states.get(0).getOptions()[0].setAlias(new String[]{"continue","story"});
	}
	
	public static void addState(State state){
		states.add(state);
	}
	public static ArrayList<State> getStates(){
		return states;
	}
	
	public static State getStateBySTATE(STATE state){
		for(State s : states){
			if(s.getState().equals(state)){
				return s;
			}
		}
		return null;
	}
	
	public static void quit(){
		System.exit(1);
	}
	
	public static void saveGame(){
		Option saveOpc = null;
		for(Option opc : getStateBySTATE(STATE.menu).getOptions()){
			if(opc.getName() == "Save game"){
				saveOpc = opc;
				break;
			}
		}
		
		if(saveOpc != null){
			saveOpc.execute();
		}
	}
	
	public void enterPressed(){
		Logic.input = textField.getText();
		textField.setText("");
	}
	
	public AppGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.setMinimumSize(new Dimension(534, 491));
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.white);
		frame.setBackground(transparent);
		frame.setBounds(100, 100, 494, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel_3 = new TransparentJPanel();
		panel_3.setDoubleBuffered(false);
		panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_3.setMinimumSize(new Dimension(420, 340));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
		);
		panel_3.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_2.setAutoscrolls(true);
		panel_2.setFocusTraversalPolicyProvider(true);
		panel_2.setDoubleBuffered(false);
		panel_2.setBounds(41, 127, 454, 330);
		panel_3.add(panel_2);
		panel_2.setBackground(backgroundColor);
		JPanel panel_1 = new JPanel();
		panel_1.setFocusTraversalPolicyProvider(true);

		panel_3.setBackground(transparent);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10){
					enterPressed();
				}
			}
		});
		textField.setFont(new Font("Courier New", Font.BOLD, 14));
		textField.setForeground(Color.WHITE);
		textField.setCaretColor(Color.WHITE);
		textField.setBackground(backgroundColor);
		textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Titulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		textField.setColumns(10);
		panel_1.add(textField);
		
		panel = new JPanel();
		panel.setDoubleBuffered(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setFocusable(false);
		panel.setVerifyInputWhenFocusTarget(false);
		panel.setRequestFocusEnabled(false);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setAutoscrolls(false);
		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(false);
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setWheelScrollingEnabled(false);
		scrollPane.setVerifyInputWhenFocusTarget(false);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.setFocusable(false);
		panel.add(scrollPane);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		textArea = new JTextArea();
		textArea.setAutoscrolls(false);
		textArea.setFocusTraversalKeysEnabled(false);
		textArea.setRequestFocusEnabled(false);
		textArea.setVerifyInputWhenFocusTarget(false);
		textArea.setFocusable(false);
		textArea.setEditable(false);
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(backgroundColor);
		textArea.setFont(new Font("Courier New", Font.BOLD, 13));
		textArea.setBorder(null);
		scrollPane.setViewportView(textArea);
		textArea.setBackground(backgroundColor);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(24)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		panel_2.setLayout(gl_panel_2);
		
		borderLabel = new JLabel("");
		borderLabel.setIcon(new ImageIcon(this.getClass().getResource("/border.png")));
		borderLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		borderLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
			}
		});
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.setFocusTraversalPolicyProvider(true);
		exitLabel.setFocusCycleRoot(true);
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quit();
			}
		});
		
		topHeader = new JLabel("");
		topHeader.setForeground(Color.WHITE);
		topHeader.setFont(new Font("Courier New", Font.BOLD, 14));
		topHeader.setBounds(220, 109, 112, 17);
		panel_3.add(topHeader);
		exitLabel.setBounds(308, 77, 24, 21);
		panel_3.add(exitLabel);
		
		JLabel saveGameLabel = new JLabel("");
		saveGameLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveGame();
			}
		});
		saveGameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveGameLabel.setBounds(233, 80, 65, 21);
		panel_3.add(saveGameLabel);
		
		JLabel minimizeLabel = new JLabel("");
		minimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		minimizeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizeLabel.setBounds(199, 77, 24, 21);
		panel_3.add(minimizeLabel);
		borderLabel.setBounds(0, 0, 534, 491);
		panel_3.add(borderLabel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
