import java.io.FileNotFoundException;
import java.io.IOException;

public class PerspectiveAnimator extends ParallelAnimator
{
  public PerspectiveAnimator() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

protected void setupCamera()
  {
    camera= new PerspectiveCamera(-5,5,-5,5);
    ((PerspectiveCamera)camera).setupCOP(new Point3D(0,0,-3));
  }

  public static void main(String[] args)
  { 
    try {
		new PerspectiveAnimator().loop(true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}