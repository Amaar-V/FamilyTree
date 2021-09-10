import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.System.out;

public class FamilyTree
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner in = new Scanner(System.in);
		Tree tree=new Tree("input.txt");
		TreeNode root=tree.createFamilyTree();
		out.println();
		out.println();
		out.println();
		tree.levelOrder(root);
		out.println();
		out.println();
		out.println();
		tree.print(root);
		out.println();
		out.println();
		out.println();
		do
		{
			tree.search();
			out.println("Would you like to search again? [y or n] > ");
		}while(in.next().toLowerCase().equals("y"));
		out.println();
		out.println();
		out.println();
		do
		{
			tree.filter(root);
			out.println("Would you like to filter again? [y or n] > ");
		}while(in.next().toLowerCase().equals("y"));
		out.println();
		out.println();
		out.println();
		do
		{
			tree.addNode();
			tree.print(root);
			out.println("Would you like to add again? [y or n] > ");
		}while(in.next().toLowerCase().equals("y"));
		out.println();
		out.println();
		out.println();
		do
		{
			tree.search();
			out.println("Would you like to search again? [y or n] > ");
		}while(in.next().toLowerCase().equals("y"));
		out.println();
	}
}