public class PerspectiveCamera extends Camera
{
  public Vector3D getVPN()
  {
	  /*return the view plan normal vector*/
	  return new Vector3D(0.0,0.0,-1.0); 
  }
  
  public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_)
  {
	  super(xmin_,xmax_,ymin_,ymax_);  
  }

  protected Point3D projectionTransform(final Point3D p)
  {
	  double d = Math.abs(cop.z); // distance from COP to projection plane
	  
	  double x = d*(p.x) / (p.z+d);
	  double y = d*(p.y) / (p.z+d);  
	  double z= 0;

      return new Point3D(x,y,z);
  }

  public void setupCOP(Point3D cop_)
  {
	  this.cop = cop_;
  }     

  private Point3D cop=new Point3D(0,0,-4); //centre of projection
}
