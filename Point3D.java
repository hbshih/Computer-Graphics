public class Point3D
{
  public double x,y,z;
 
  // Constructor
  public Point3D(double X,double Y,double Z)
  {
    x = X;
    y = Y;
    z = Z;
  }
  
  /* return the vector that is between this point and p.*/
  public Vector3D vector(Point3D p)
  {
	  return new Vector3D(this.x - p.x, this.y - p.y, this.z - p.z);
  }
  
  /*
   *  Suppose we have 3 vertices of a polygon in counterclockwise order viewing from its front: p1, p2 and p3. 
   *  Then vectors v1=p2-p1 and v2=p3-p1 are two vectors in the plane of the polygon, and the vector n=v1*v2 (cross product) 
   *  is the surface normal of the plane and the polygon.
   */
  public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3)
  {
	  Vector3D v1 = p2.vector(p1);
	  Vector3D v2 = p3.vector(p1);
	  return v1.crossProduct(v2);
  }
  
  /*
   * Suppose vpn is the vector pointing toward the viewpoint. Then if n.vpn (dot product) is positive,
   * the two vectors form an angle in the range of (-PI/2, PI/2), and thus the polygon is a front face.
   */
  public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn)
  {
	  Vector3D surfaceNormal = Point3D.faceNormal(p1, p2, p3);
	
	  if(surfaceNormal.dotProduct(vpn) > 0)
	  {
		  return true;
	  }
	  
	  return false;
	
  }

  // Calculate the pointwise distance 
  /* Formula
   * d((0,0,0),(1,2,3)) = sqrt [ (1-0)^2 + (2-0)^2 + (3-0)^2 ] =  sqrt [14]
   */
  public double distance(Point3D p)
  {
    double dx = x - p.x;
    double dy = y - p.y;
    double dz = z - p.z;
    return Math.sqrt(dx*dx + dy*dy + dz*dz);

  }
  
  //Transform Point 3D
  /*
If column vector is used, for the point (x,y,z) the transform function must compute by using the homogeneous coordinate system:

 
       (xw)       | m00 m01 m02 m03 |   (x)
       (yw)       | m10 m11 m12 m13 |   (y)
       (zw)   =   | m20 m21 m22 m23 | * (z)
       ( w)       | m30 m31 m32 m33 |   (1)
 
= (x*m00+y*m01+z*m02+m03)
  (x*m10+y*m11+z*m12+m13)
  (x*m20+y*m21+z*m22+m23)
  (x*m30+y*m31+z*m32+m33)
 
Then calculate for the new point: 
 
(xw,yw,zw) = (xw/w, yw/w, zw/w)
   */
  public Point3D transform(Matrix m)
  {
	  double xw = this.x * m.m[0][0] + this.y * m.m[0][1] + z*m.m[0][2] + m.m[0][3];
	  double yw = this.x * m.m[1][0] + this.y * m.m[1][1] + z*m.m[1][2] + m.m[1][3];
	  double zw = this.x * m.m[2][0] + this.y * m.m[2][1] + z*m.m[2][2] + m.m[2][3];
	  double w = this.x * m.m[3][0] + this.y * m.m[3][1] + z*m.m[3][2] + m.m[3][3];
	  
	  xw = xw/w;
	  yw = yw/w;
	  zw = zw/w;
	  
	  return new Point3D(xw,yw,zw);
	  
  }

   
  public String toString()
  {
	  System.out.println("Point3D ( " + this.x + "," + this.y + "," + this.z + ")");
	  return "";
  }
  
  public static void main(String [] args)
  {
	  Point3D p1 = new Point3D(0,0,0);
	  Point3D p2 = new Point3D(1,2,3);
  }
 

}
