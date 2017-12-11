import java.util.*;
import java.io.*;
import java.awt.*;

public class GObject
{
  public Point3D[] vertex; /* list of vertices of the model */
 
  public Face[] face; /* list of faces of the model */
  
  public GObject(Point3D[] v, Face[] f)
  {
	  this.vertex = v;
	  this.face = f;
  }

  /*Read data in the file*/
  public GObject(String fileName) throws FileNotFoundException, IOException
  {
	  BufferedReader reader = null;
	  try 
	  {
		    File file = new File(fileName); // Get file
		    reader = new BufferedReader(new FileReader(file));
		    
		    int vertexSize = Integer.parseInt(reader.readLine().trim()); 
		    vertex = new Point3D [vertexSize];
		    for(int i = 0 ; i < vertexSize ; i ++)
		    {
		    		String [] vertexArray = reader.readLine().split(" ");
		    		vertex[i] = new Point3D(Double.parseDouble(vertexArray[0]),Double.parseDouble(vertexArray[1]),Double.parseDouble(vertexArray[2]));
		    }
		    
		    reader.readLine(); // Skip a line
		    
		    int faceSize = Integer.parseInt(reader.readLine().trim());
		    face = new Face[faceSize];//Store the faces
		    for(int i = 0 ; i < faceSize ; i++)
		    {
		    		int number_of_faces = Integer.parseInt(reader.readLine().trim());
		    		System.out.println(number_of_faces);
		    		String [] index = (reader.readLine().split(" "));
		    		
		    		int [] index_v = new int [number_of_faces];
		    		
		    		for(int j = 0 ; j < index.length; j++)
		    		{
		    			index_v[j] = Integer.parseInt(index[j]);
		    		}
		    		String [] color = reader.readLine().split(" ");
		    		Color color_v = new Color(Float.parseFloat(color[0]),Float.parseFloat(color[1]),Float.parseFloat(color[2])); // Get the colours
		    		face[i] = new Face(index_v,color_v);
		    		face[i].toString();
		    } 
		} catch (IOException e) 
		{
		    e.printStackTrace();
		} finally {
		    try 
		    {
		        reader.close();
		    } catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		}
  }
   
  //Transform all the vertices
  public void transform(Matrix m)
  {  
	  	for(int i= 0 ; i < this.vertex.length ; i++)
	  	{
	  		this.vertex[i] = this.vertex[i].transform(m);
	  	}
	  
  }

  public String toString()
  {
	  return "";
  }
  
}