import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import static java.lang.Math.*;
 
public class ParallelAnimator extends Animator
{
  private static final String[] files={"./cube.dat","./pyramid.dat"};
  //private static final String[] files={"./bbox.txt"};
  public ParallelAnimator() throws FileNotFoundException, IOException
  {
    super();
    scene=new Scene(files);
    setupCamera();
  }
 
  protected void setupCamera()
  {
    camera= new Camera(-5,5,-5,5);
  }

  // Update the camera
  protected void setCamera(Double xmin, Double xmax, Double ymin, Double ymax)
  {      
	  camera = new Camera(xmin,xmax,ymin,ymax);
	  System.out.println("The new camera is now : " + xmin + "," + xmax + "," + ymin + "," + ymax);
  }
  
  double RotationX = 11;
  double RotationY = 13;
  double RotationZ = 17;
  
  // Update the new rotation matrix
  protected void setRotationXYZ (int x, int y,int z)
  {
	  RotationX = RotationX + x;
	  RotationY = RotationY + y;
	  RotationZ = RotationZ + z;
	  System.out.println("The new rotation matrix is : " + RotationX + "," + RotationY + "," + RotationZ);
  }
 
  protected void animate(Graphics g)
  {
    camera.setViewport(getWidth(),getHeight());
 
    if(g==null || scene==null || camera==null)
      return;
 
    Matrix mX=new Matrix(), mY=new Matrix(), mZ=new Matrix();
    mX.setRotationX(-PI/RotationX);
    mY.setRotationY(PI/RotationY);
    mZ.setRotationZ(PI/RotationZ); 
    scene.transform(mZ.multiply(mY.multiply(mX))); 
 
    scene.draw(camera,g);
  }
 
  public static void main(String[] args)
  {
    try {
		new ParallelAnimator().loop(true);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 
  private Scene scene;
  protected Camera camera;
}