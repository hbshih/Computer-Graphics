import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Scene
{
  private GObject[] obj;

  public Scene(String[] fileName) throws FileNotFoundException, IOException
  {
	  obj = new GObject[fileName.length];
      
	  // get objects
      for(int i = 0; i < fileName.length; i++)
      {
          obj[i] = new GObject(fileName[i]);
      }
  }

  public void transform(Matrix m)
  {
      for(int i = 0; i < obj.length; i++)
      {
          obj[i].transform(m);
      }
  }

  public void draw(Camera c, Graphics g)
  {
	  // Get GObject length
      int objectSize = obj.length;
      
      //Loop through each object and get the face and colour
      for (int i = 0 ; i < objectSize ; i++)
      { 
    	  	GObject Obj = obj[i]; 
    	  	int verticesLength = Obj.vertex.length;
    	  	// Handle Error
    	  	if (verticesLength > 0)
    	  	{
    	  		// project all vertices
    	  		Point3D [] vertices =  new Point3D[verticesLength];
    	        for (int j = 0; j < verticesLength; j++)
    	        {
    	            vertices[j] = c.project(Obj.vertex[j]);
    	        }
    	        // loop through all the faces in this object
    	    	  	for (int j = 0 ; j < Obj.face.length ; j++)
    	    	  	{
    	    	  		Face currFace = Obj.face[j];
    	    	  		int [] xArray = new int [currFace.index.length];
    	    	  		int [] yArray = new int [currFace.index.length];
  	    	  		
    		  		//surfaceNormal.dotProduct(vpn) > 0 -> make sure the face is front facing
    	            if (Point3D.isFrontFace(vertices[currFace.index[0]], vertices[currFace.index[1]],vertices[currFace.index[2]], c.getVPN())) 
    	            {
    	                //store the front facing points
    	                for (int n = 0; n < currFace.index.length; n++) 
    	                {
    	                    xArray[n] = (int) vertices[currFace.index[n]].x;
    	                    yArray[n] = (int) vertices[currFace.index[n]].y;
    	                }  	
           	        //Set up the color and draw the polygon
            	    	    g.setColor(currFace.color);  
    	                g.fillPolygon(xArray, yArray,  currFace.index.length);
    	            }
    	    	  	}	
    	  	}else
    	  	{
    	  		System.out.println("Couldn't get any vertices in the object");
    	  	}
      }
  }

  public String toString()
  {/* Make it look nice to save your debugging time! */
	  String str = "";
	  
	  for (int i = 0 ; i < obj.length ; i++)
	  {
		  str += obj[i].toString();
	  }
	  return str;
  }
}