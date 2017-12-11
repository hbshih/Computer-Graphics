import java.awt.Button;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

public class Camera
{
  public Vector3D getVPN()
  {
	  /*Cross product - 0 1 0 x 1 0 0 = 0 0 -1 */
	  return new Vector3D(0.0,0.0,-1.0); 
  }
 
  protected Point3D cameraTransform(final Point3D p)
  {
	  return new Point3D(p.x,p.y,p.z);
  }
 
  protected Point3D projectionTransform(final Point3D p)
  {
	  return new Point3D(p.x,p.y,0);
  } 
 
  /*
   * The values aX, bX, aY, bY are used in viewport transformation. In other words, if (x,y) is a point on the view plane, 
   * and (x',y') the corresponding point on the screen, then x' = aX + bX*x, and y' = aY + bY*y. 
   */
  private final Point3D viewportTransform(final Point3D p)
  {
	  double vp_x = this.ax + this.bx * p.x;
	  double vp_y = this.ay + this.by * p.y;
	  
	  return new Point3D(vp_x,vp_y,0);
  }
 
  public final Point3D project(final Point3D p)
  {
    Point3D temp=cameraTransform(p);
    temp=projectionTransform(temp);
    return viewportTransform(temp);
  }
 
  public Camera(double xmin_, double xmax_, double ymin_, double ymax_)
  {
	  this.xmin = xmin_;
	  this.xmax = xmax_;
	  this.ymin = ymin_;
	  this.ymax = ymax_;
  }
 
  public void setViewport(int width, int height)
  {/*calculate ax, bx, ay, by*/
	  double amin = 0;
	  double amax = width;
	  double bmin = 0;
	  double bmax = height;
	  
	  double da = amax - amin;
	  double db = bmax - bmin;
	  
	  double dx = this.xmax - this.xmin;
	  double dy = this.ymax - this.ymin;
	  
	  this.bx = da/dx; this.ax = amin - this.bx*this.xmin;
	  this.by = db/dy; this.ay = bmin - this.by*this.ymin;
	  
	  
  }
 /*
  public String toString()
  {
	
	  
  }*/
 
  private double xmin, xmax, ymin, ymax;
  private double fcp, bcp; 
  private double ax, bx, ay, by;
}

