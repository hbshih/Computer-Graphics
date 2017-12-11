import java.awt.Color;

public class Face
{
	
  public int[] index; /* list of indices of vertices of the face */
 
  public Color color;

  //for storing indices to vertices in a face and the colour of the face 
  public Face(int[] i, Color c)
  {
	  this.index = i;
	  this.color = c;
  }

  public String toString()
  {
	  for (int i = 0 ; i < index.length ; i++)
	  {
		  System.out.print(index[i] + " ");
	  }
	  System.out.println("");
	  System.out.println(color);
	  return "The face color is : " + this.color;
  }
}
