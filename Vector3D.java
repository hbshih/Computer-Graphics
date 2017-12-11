import static java.lang.Math.*;  /* Now you can use math functions without the Math. prefix */

public class Vector3D implements Cloneable     
{
  public double x,y,z;
  
  public Vector3D(Point3D p)
  {
      this.x = p.x;
      this.y = p.y;
      this.z = p.z;
  }
  
  public Vector3D(double X, double Y, double Z)
  {
	  this.x = X;
	  this.y = Y;
	  this.z = Z;
  }

  public String toString()
  {/* Make it look nice to save your debugging time! */
	  return "Vector3D  X: "+this.x+" Y: "+this.y+" Z: "+this.z;
  }

  //Copy a new object
  public Vector3D clone() throws CloneNotSupportedException
  {
	  return new Vector3D(this.x,this.y,this.z);
  }

  //Perform Euclidean norm 
  public double L2norm()
  {
	  return sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
  }
  
  // Dotproduct
  
  /* a = x1,x2,x3 b = y1,y2,y3
   *	 formula = a.b = x1y1 + x2y2 + x3y3
  */
  public double dotProduct(Vector3D v)
  {
	  return this.x * v.x + this.y * v.y + this.z * v.z;
  }
  //Cross Product
  /*
    cx = aybz − azby
	cy = azbx − axbz
	cz = axby − aybx
   */
  public Vector3D crossProduct(Vector3D v)
  {
	  double CPx = this.y * v.z - this.z * v.y;
	  double CPy = this.z * v.x - this.x * v.z;
	  double CPz = this.x * v.y - this.y * v.x;
	  
	  return new Vector3D(CPx, CPy, CPz);
  }

  /* to normalise a vector is to change the vector length to 1 while keeping the vector direction the same */
  
  public void normalize()
  {
	  double length = sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
	  this.x = this.x / length;
	  this.y = this.y / length;
	  this.z = this.z / length;
  } 
  
  
//Transform vector
  public Vector3D transform(Matrix m) 
  {
      double wx = this.x * m.m[0][0] + this.y * m.m[1][0] + this.z * m.m[2][0] + m.m[3][0];
      double wy = this.x * m.m[0][1] + this.y * m.m[1][1] + this.z * m.m[2][1] + m.m[3][1];
      double wz = this.x * m.m[0][2] + this.y * m.m[1][2] + this.z * m.m[2][2] + m.m[3][2];

      return new Vector3D(wx, wy, wz);
  }

  
}