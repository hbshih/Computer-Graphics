import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * User can choose what kind of projection they want to animate
 * Then either parallel project or perspective projection will appear
 * users can change the rotation, speed and camera by playing with the button in the frame
 * 
 * */

public class Animator extends JFrame
{
// --- Set up the GUI
	Button IncreaseXRotationbutton;
	Button DecreaseXRotationbutton;
	Button IncreaseYRotationbutton;
	Button DecreaseYRotationbutton;
	Button IncreaseZRotationbutton;
	Button DecreaseZRotationbutton;
	Button changeCameraView;
	private TextField xmin = new TextField(5);
	private TextField xmax = new TextField(5);
	private TextField ymin = new TextField(5);
	private TextField ymax = new TextField(5);

	public Animator() throws FileNotFoundException, IOException
	{ 
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new FlowLayout());

		IncreaseXRotationbutton = new Button ("Increase X");
		DecreaseXRotationbutton = new Button ("Decrease X");
		IncreaseYRotationbutton = new Button ("Increase Y");
		DecreaseYRotationbutton = new Button ("Decrease Y");
		IncreaseZRotationbutton = new Button ("Increase Z");
		DecreaseZRotationbutton = new Button ("Decrease Z");
		
		changeCameraView = new Button ("Change Camera View"); 		


		add(IncreaseXRotationbutton);
		add(DecreaseXRotationbutton);
		add(IncreaseYRotationbutton);
		add(DecreaseYRotationbutton);
		add(IncreaseZRotationbutton);
		add(DecreaseZRotationbutton);
		add(xmin);
		add(xmax);
		add(ymin);
		add(ymax);
		add(changeCameraView);

		// Rotate X if pressed
		IncreaseXRotationbutton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if (response == 0)parallel.setRotationXYZ(5, 0, 0);
				else perspective.setRotationXYZ(5, 0, 0);
			}
		});
		DecreaseXRotationbutton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if (response == 0)parallel.setRotationXYZ(-5, 0, 0);
				else perspective.setRotationXYZ(-5, 0, 0);
			}
		});
		IncreaseYRotationbutton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if (response == 0)parallel.setRotationXYZ(0, 5, 0);
				else perspective.setRotationXYZ(0, 5, 0);
			}
		});
		DecreaseYRotationbutton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if (response == 0)parallel.setRotationXYZ(0, -5, 0);
				else perspective.setRotationXYZ(0, -5, 0);
			}
		});
		IncreaseZRotationbutton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if (response == 0)parallel.setRotationXYZ(0, 0, 5);
				else perspective.setRotationXYZ(0, 0, 5);
			}
		});
		DecreaseZRotationbutton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				if (response == 0)parallel.setRotationXYZ(0, 0, -5);
				else perspective.setRotationXYZ(0, 0, -5);
			}
		});
		changeCameraView.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				try
				{
					// Get the value entered
					Double xmin_ = Double.parseDouble(xmin.getText());
					Double xmax_ = Double.parseDouble(xmax.getText());
					Double ymin_ = Double.parseDouble(ymin.getText());
					Double ymax_ = Double.parseDouble(ymax.getText());
					if (response == 0)parallel.setCamera(xmin_,xmax_,ymin_,ymax_);
					else perspective.setCamera(xmin_,xmax_,ymin_,ymax_);
				}catch(NumberFormatException error)
				{
					System.out.println(error);
					JOptionPane.showMessageDialog(null, "There's an error, please check if you have typed the right numbers \n" + error);
				}
				
			}
		});		
		setVisible(true);
	}


	private int R;
	protected void animate(Graphics g)
	{
		//  g.setColor(Color.RED);   
		//  R=R>60?0:R+1; // if r > 60 : set it to 0, else r++
		//R = R+1;
		// g.fillPolygon(new int[]{100,WIDTH/2,R*1},new int[]{200,HEIGHT/2,R*3},3);
		//  g.fillRect(R, HEIGHT/2, R , HEIGHT/5);
	}

	protected void loop(boolean show)
	{
		while(show)
		{
			image=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			animate(g2);
			((Graphics2D)getGraphics()).drawImage(image,0,0,null);
			//Pass g2 to paint
			paint(getGraphics());
			try {Thread.sleep(INTERVAL);}
			catch(InterruptedException e){}
		}
	}
	
	//When an applet starts executing, paint( Graphics g ) is automatically called. It paints the screen
	public final void paint(Graphics g){}


	private static final int WIDTH=750;
	private static final int HEIGHT=800;
	private static final int INTERVAL=100;
	private BufferedImage image;
	static ParallelAnimator parallel;
	static PerspectiveAnimator perspective;
	static int response = -1; // Set the initial response to -1
	
	
	public static void main(String[] args) 
	{

		// Set up initial screen and options
		String[] options = new String[] {"Parallel", "Perspective"};
		String startScreenInfo = "Hi, \nThis is an projection simulator.\n\nPlease choose Parallel Projection or Perspective Projection to start the simulator:";
		if (response == -1)
		{
			response = JOptionPane.showOptionDialog(null, startScreenInfo, "Computer Graphics",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[0]);
		}

		if (response == 0)
		{
			try {
				parallel = new ParallelAnimator();
				parallel.loop(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			try {
				perspective = new PerspectiveAnimator();
				perspective.loop(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    		
		}


	}
}
