//Family Tree Project created by Amaar Valliani

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

public class Tree
{
	private static ArrayList <Person> list;
	private static ArrayList <TreeNode> list2;
	Tree(String s)throws FileNotFoundException
	{
		list=populate(s);
		list2=populateTree(list);
	}

	private static ArrayList<Person> populate(String s)throws FileNotFoundException {
		Scanner in = new Scanner(new File(s));
		String s1;
		ArrayList<Person> list=new ArrayList();
		in.nextLine();
		while (in.hasNext()) {
			Person p1 = new Person(in.next(), in.next(), in.nextInt());
			s1 = in.next();
			if (!s1.equals("null")) {
				p1.setDad(list.get(Integer.parseInt(s1)));
				p1.setDadNode(Integer.parseInt(s1));
			} else
				p1.setDad(null);
			s1 = in.next();
			if (!s1.equals("null")) {
				p1.setMom(list.get(Integer.parseInt(s1)));
				p1.setMomNode(Integer.parseInt(s1));
			} else
				p1.setMom(null);
			p1.setEyecolor(in.next());
			p1.setHeight(in.nextInt());
			p1.setHaircolor(in.next());
			p1.setAlive(!in.next().equals("y"));
			Person pp = new Person(in.next(),in.next(),in.nextInt());
			if(p1.getDad() == null && !pp.getFirstName().equals("Unknown"))
			{
				p1.setDad(pp);
			}
			else if(p1.getMom() == null && !pp.getFirstName().equals("Unknown"))
			{
				p1.setMom(pp);
			}
			list.add(p1);
		}
		return list;
	}

	private static ArrayList<TreeNode> populateTree(ArrayList<Person> list)
	{
		ArrayList <TreeNode> list2 = new ArrayList<TreeNode>();
		for(int x=list.size()-1;x>=0;x--)
		{
			if(list.get(x).getMomNode() != -1)
				list.get(list.get(x).getMomNode()).setPartner(list.get(x).getDad());
			else if(list.get(x).getDadNode() != -1)
				list.get(list.get(x).getDadNode()).setPartner(list.get(x).getMom());
		}
		for(int x1=0;x1<list.size();x1++)
		{
			list2.add(new TreeNode(list.get(x1),null,null,null));
		}
		return list2;
	}

	public static TreeNode createFamilyTree()
	{
		TreeNode root=list2.get(0);
		TreeNode t1, t2;
		System.out.println(root.getValue() + "  ");
		t1=root;
		for(int a=1;a<list.size();a++)
		{
			t2=t1;
			t1=list2.get(a);
			System.out.println(t1.getValue() + "  ");
			if(t1.getValue().getDadNode() != -1) {
				t2 = list2.get(t1.getValue().getDadNode());
				if(t2.getLeft() == null)
				{
					t2.setLeft(t1);
					t1.setParent(t2);
				}
				else if(t2.getRight() == null)
				{
					t2.setRight(t1);
					t1.setParent(t2);
				}
				list2.set(t1.getValue().getDadNode(),t2);
				list2.set(a,t1);
			}
			else if(t1.getValue().getMomNode() != -1) {
				t2 = list2.get(t1.getValue().getMomNode());
				if(t2.getLeft() == null)
				{
					t2.setLeft(t1);
					t1.setParent(t2);
				}
				else if(t2.getRight() == null)
				{
					t2.setRight(t1);
					t1.setParent(t2);
				}
				list2.set(t1.getValue().getMomNode(),t2);
				list2.set(a,t1);
			}
		}
		return root;
	}

	public static void levelOrder(TreeNode p)
	{
		Queue temp = new LinkedList();
		if (p == null)
			System.out.println("THE TREE IS EMPTY");
		else
		{
			System.out.println("Tree Display By Levels");
			temp.add(p);
			while (!temp.isEmpty())
			{
				p = (TreeNode) temp.remove();
				System.out.println(p.getValue() + "  ");
				if (p.getLeft() != null)
					temp.add(p.getLeft());
				if (p.getRight() != null)
					temp.add(p.getRight());
			}
		}
	}

	public static void inOrder (TreeNode p)
	{
		if (p != null)
		{
			inOrder(p.getLeft());
			System.out.print(p.getValue() + "  ");
			inOrder(p.getRight());
		}
	}

	public static void preOrder (TreeNode p)
	{
		if (p != null)
		{
			System.out.print(p.getValue() + "  ");
			inOrder(p.getLeft());
			inOrder(p.getRight());
		}
	}

	public static void postOrder (TreeNode p)
	{
		if (p != null)
		{
			inOrder(p.getLeft());
			inOrder(p.getRight());
			System.out.print(p.getValue() + "  ");
		}
	}

	public static void revOrder (TreeNode p)
	{
		if (p != null)
		{
			revOrder(p.getRight());
			System.out.print(p.getValue() + "  ");
			revOrder(p.getLeft());
		}
	}

	public static int nodeCount (TreeNode p)
	{
		if (p == null)
			return 0;
		else
			return 1 + nodeCount(p.getLeft()) + nodeCount(p.getRight());
	}

	public static int leafCount (TreeNode p)
	{
		if (p == null)
			return 0;
		else
		{
			if ((p.getLeft() == null) && (p.getRight() == null))
				return 1;
			else
				return leafCount(p.getLeft()) + leafCount(p.getRight());
		}
	}

	public static TreeNode copyTree (TreeNode p)
	{
		TreeNode temp;
		if (p == null)
			return null;
		else
		{
			temp = new TreeNode(p.getValue(), null, null, null);
			temp.setLeft(copyTree(p.getLeft()));
			temp.setRight(copyTree(p.getRight()));
			return temp;
		}
	}

	public static int getHeight (TreeNode p)
	{
		if (p == null)
			return 0;
		else
		{
			if (getHeight(p.getLeft()) > getHeight(p.getRight()))
				return 1 + getHeight(p.getLeft());
			else
				return 1 + getHeight(p.getRight());
		}
	}

	public static boolean isFull (TreeNode p)
	{
		if (p == null)
			return true;
		else
			return isFull(p.getLeft()) && isFull(p.getRight()) && (getHeight(p.getLeft()) == getHeight(p.getRight()) );
	}

	public void search()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the First Name of the person to find >");
		String s1=in.next();
		Person find=null;
		int count=0;
		for(int x=0;x<list2.size();x++)
		{
			if(list.get(x).getFirstName().toLowerCase().equals(s1.toLowerCase()))
				count++;
		}
		if(count>1)
		{
			System.out.println("Enter the Last Name of the person to find >");
			String s2=in.next();
			for(int x=0;x<list2.size();x++)
			{
				if(list.get(x).getFirstName().toLowerCase().equals(s1.toLowerCase())&&list.get(x).getLastName().toLowerCase().equals(s2.toLowerCase()))
				{
					find = list.get(x);
					System.out.println(find);
					System.out.println(find.traits());
				}
			}
		}
		else if(count==1)
		{
			for(int x=0;x<list2.size();x++)
			{
				if(list.get(x).getFirstName().toLowerCase().equals(s1.toLowerCase()))
				{
					find = list.get(x);
					System.out.println(find);
					System.out.println(find.traits());
				}
			}
		}
		else {
			System.out.println("Person not found");
		}
	}

	public static void filter(TreeNode root)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the case to be filtered (int) \n(1=Eye Color, 2=Height, 3=Hair Color, 4=Living/Deceased, 5=Married/Single) > ");
		String ss=in.next();
		if(!"1 2 3 4 5".contains(ss))
		{
			do
			{
				System.out.println("Invalid case, Enter again");
				System.out.println("Enter the case to be filtered (int) \n(1=Eye Color, 2=Height, 3=Hair Color, 4=Living/Deceased, 5=Married/Single) > ");
				ss=in.next();
			}while(!"1 2 3 4 5".contains(ss));
		}
		int x=Integer.parseInt(ss);
		String s;
		int t;
		if(x==1)
		{
			System.out.println("Which Eye Color would you like to filter? \n [Brown, Blue, Green, or Black] > ");
			s=in.next();
			if(!"brown black blue green".contains(s.toLowerCase()))
			{
				do
				{
					System.out.println("Invalid Eye Color, Try again");
					System.out.println("Enter their eye color > ");
					s = in.next();
				} while (!"brown black blue green".contains(s.toLowerCase()));
			}
			s=s.substring(0,1).toUpperCase()+s.substring(1).toLowerCase();
			Queue temp = new LinkedList();
			TreeNode p=root;
			if (p == null)
				System.out.println("THE TREE IS EMPTY");
			else
			{
				System.out.println("Tree Display By Levels");
				temp.add(p);
				while (!temp.isEmpty())
				{
					p = (TreeNode) temp.remove();
					if(p.getValue().getEyecolor().equals(s))
					{
						System.out.println(p.getValue() + "  ");
						System.out.println(p.getValue().traits()+"  ");
					}
					if (p.getLeft() != null)
						temp.add(p.getLeft());
					if (p.getRight() != null)
						temp.add(p.getRight());
				}
			}
		}
		else if(x==2)
		{
			System.out.println("What is the minimum height? (Inches) > ");
			s=in.next();
			if(!"1 2 3 4 5 6 7 8 9 0".contains(s.substring(0,1)))
			{
				do
				{
					System.out.println("Invalid case, Enter again");
					System.out.println("What is the minimum height? (Inches) > ");
					s=in.next();
				}while(!"1 2 3 4 5 6 7 8 9 0".contains(s.substring(0,1)));
			}
			t=Integer.parseInt(s);
			Queue temp = new LinkedList();
			TreeNode p=root;
			if (p == null)
				System.out.println("THE TREE IS EMPTY");
			else
			{
				System.out.println("Tree Display By Levels");
				temp.add(p);
				while (!temp.isEmpty())
				{
					p = (TreeNode) temp.remove();
					if(p.getValue().getHeight()>=t)
					{
						System.out.println(p.getValue() + "  ");
						System.out.println(p.getValue().traits() + "  ");
					}
					if (p.getLeft() != null)
						temp.add(p.getLeft());
					if (p.getRight() != null)
						temp.add(p.getRight());
				}
			}
		}
		else if(x==3)
		{
			System.out.println("Which Hair Color would you like to filter? \n [Brown, Black, Red, or Blond] > ");
			s=in.next();
			if(!"brown black red blond".contains(s.toLowerCase()))
			{
				do
				{
					System.out.println("Invalid Hair Color, Try again");
					System.out.println("Enter their hair color > ");
					s = in.next();
				} while (!"brown black red blond".contains(s.toLowerCase()));
			}
			s=s.substring(0,1).toUpperCase()+s.substring(1).toLowerCase();
			Queue temp = new LinkedList();
			TreeNode p=root;
			if (p == null)
				System.out.println("THE TREE IS EMPTY");
			else
			{
				System.out.println("Tree Display By Levels");
				temp.add(p);
				while (!temp.isEmpty())
				{
					p = (TreeNode) temp.remove();
					if(p.getValue().getHaircolor().equals(s))
					{
						System.out.println(p.getValue() + "  ");
						System.out.println(p.getValue().traits()+"  ");
					}
					if (p.getLeft() != null)
						temp.add(p.getLeft());
					if (p.getRight() != null)
						temp.add(p.getRight());
				}
			}
		}
		else if(x==4)
		{
			System.out.println("Would you like to sort by [Living or Deceased]? > ");
			s=in.next();
			Queue temp = new LinkedList();
			TreeNode p=root;
			if(s.toLowerCase().equals("living"))
			{
				if (p == null)
					System.out.println("THE TREE IS EMPTY");
				else
				{
					System.out.println("Tree Display By Levels");
					temp.add(p);
					while (!temp.isEmpty())
					{
						p = (TreeNode) temp.remove();
						if (p.getValue().getAlive())
						{
							System.out.println(p.getValue() + "  ");
							System.out.println(p.getValue().traits() + "  ");
						}
						if (p.getLeft() != null)
							temp.add(p.getLeft());
						if (p.getRight() != null)
							temp.add(p.getRight());
					}
				}
			}
			else if(s.toLowerCase().equals("deceased"))
			{
				if (p == null)
					System.out.println("THE TREE IS EMPTY");
				else
				{
					System.out.println("Tree Display By Levels");
					temp.add(p);
					while (!temp.isEmpty())
					{
						p = (TreeNode) temp.remove();
						if(!p.getValue().getAlive())
						{
							System.out.println(p.getValue() + "  ");
							System.out.println(p.getValue().traits() + "  ");
						}
						if (p.getLeft() != null)
							temp.add(p.getLeft());
						if (p.getRight() != null)
							temp.add(p.getRight());
					}
				}
			}
			else
				System.out.println("Not valid filter");
		}
		else if(x==5)
		{
			System.out.println("Would you like to sort by [Married or Single]? > ");
			s=in.next();
			Queue temp = new LinkedList();
			TreeNode p=root;
			if(s.toLowerCase().equals("married"))
			{
				if (p == null)
					System.out.println("THE TREE IS EMPTY");
				else
				{
					System.out.println("Tree Display By Levels");
					temp.add(p);
					while (!temp.isEmpty())
					{
						p = (TreeNode) temp.remove();
						if (p.getValue().getPartner() != null)
						{
							System.out.println(p.getValue() + "  ");
							System.out.println(p.getValue().traits() + "  ");
						}
						if (p.getLeft() != null)
							temp.add(p.getLeft());
						if (p.getRight() != null)
							temp.add(p.getRight());
					}
				}
			}
			else if(s.toLowerCase().equals("single"))
			{
				if (p == null)
					System.out.println("THE TREE IS EMPTY");
				else
				{
					System.out.println("Tree Display By Levels");
					temp.add(p);
					while (!temp.isEmpty())
					{
						p = (TreeNode) temp.remove();
						if(p.getValue().getPartner() == null)
						{
							System.out.println(p.getValue() + "  ");
							System.out.println(p.getValue().traits() + "  ");
						}
						if (p.getLeft() != null)
							temp.add(p.getLeft());
						if (p.getRight() != null)
							temp.add(p.getRight());
					}
				}
			}
			else
				System.out.println("Not valid filter");
		}
		else
			System.out.println("Not valid filter");
	}

	public static void addNode()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first name of the person you want to enter > ");
		String first = in.next();
		first=first.substring(0,1).toUpperCase()+first.substring(1).toLowerCase();
		System.out.println("Enter their last name > ");
		String last = in.next();
		last=last.substring(0,1).toUpperCase()+last.substring(1).toLowerCase();
		System.out.println("Enter their age in years > ");
		String s=in.next();
		if(!"1 2 3 4 5 6 7 8 9 0".contains(s.substring(0,1)))
		{
			do
			{
				System.out.println("Invalid case, Enter again");
				System.out.println("Enter their age in years > ");
				s=in.next();
			}while(!"1 2 3 4 5 6 7 8 9 0".contains(s.substring(0,1)));
		}
		int age = Integer.parseInt(s);
		System.out.println("Enter their eye color > ");
		String eye = in.next();
		if(!"brown black blue green".contains(eye.toLowerCase()))
		{
			do
			{
				System.out.println("Invalid Eye Color, Try again");
				System.out.println("Enter their eye color > ");
				eye = in.next();
			} while (!"brown black blue green".contains(eye.toLowerCase()));
		}
		eye=eye.substring(0,1).toUpperCase()+eye.substring(1).toLowerCase();
		System.out.println("Enter their hair color > ");
		String hair = in.next();
		if(!"brown black red blond".contains(hair.toLowerCase()))
		{
			do
			{
				System.out.println("Invalid Hair Color, Try again");
				System.out.println("Enter their hair color > ");
				hair = in.next();
			} while (!"brown black red blond".contains(hair.toLowerCase()));
		}
		hair=hair.substring(0,1).toUpperCase()+hair.substring(1).toLowerCase();
		System.out.println("Enter their height in inches? > ");
		s=in.next();
		if(!"1 2 3 4 5 6 7 8 9 0".contains(s.substring(0,1)))
		{
			do
			{
				System.out.println("Invalid case, Enter again");
				System.out.println("Enter their height in inches? > ");
				s=in.next();
			}while(!"1 2 3 4 5 6 7 8 9 0".contains(s.substring(0,1)));
		}
		int height = Integer.parseInt(s);
		System.out.println("Enter the parent that is related to the family tree (Dad or Mom) > ");
		String parent = in.next();
		String parentfirst;
		String parentlast;
		String parent2first;
		String parent2last;
		boolean found= false;
		if(parent.toLowerCase().equals("mom"))
		{
			System.out.println("The parent has to exist in the family tree and have less than two children.");
			System.out.println("Enter the mother's first name > ");
			parentfirst = in.next();
			System.out.println("Enter the mother's last name > ");
			parentlast = in.next();
			for(int x=0;x<list2.size();x++)
			{
				if(list2.get(x).getValue().getFirstName().toLowerCase().equals(parentfirst.toLowerCase())&&list2.get(x).getValue().getLastName().toLowerCase().equals(parentlast.toLowerCase()))
				{
					if(list2.get(x).getLeft() == null || list2.get(x).getRight() == null)
					{
						found = true;
						Person n = new Person(first, last, age);
						n.setHaircolor(hair);
						n.setHeight(height);
						n.setEyecolor(eye);
						n.setMom(list.get(x));
						n.setMomNode(x);
						if(list2.get(x).getValue().getPartner() == null)
						{
							System.out.println("Enter the father's first name > ");
							parent2first = in.next();
							parent2first=parent2first.substring(0,1).toUpperCase()+parent2first.substring(1).toLowerCase();
							System.out.println("Enter the father's last name > ");
							parent2last = in.next();
							parent2last=parent2last.substring(0,1).toUpperCase()+parent2last.substring(1).toLowerCase();
							Person pp = new Person(parent2first, parent2last, -1);
							n.setDad(pp);
							list2.get(x).getValue().setPartner(pp);
						}
						list.add(n);
						TreeNode t = new TreeNode(n, null, null, list2.get(x));
						list2.add(t);
						if(list2.get(x).getLeft() == null)
							list2.get(x).setLeft(t);
						else
							list2.get(x).setRight(t);
					}
				}
			}
			if(!found)
				System.out.println("Not valid parent");
		}
		else if(parent.toLowerCase().equals("dad"))
		{
			System.out.println("The parent has to exist in the family tree and have less than two children.");
			System.out.println("Enter the father's first name > ");
			parentfirst = in.next();
			System.out.println("Enter the father's last name > ");
			parentlast = in.next();
			for(int x=0;x<list2.size();x++)
			{
				if(list2.get(x).getValue().getFirstName().toLowerCase().equals(parentfirst.toLowerCase())&&list2.get(x).getValue().getLastName().toLowerCase().equals(parentlast.toLowerCase()))
				{
					if(list2.get(x).getLeft() == null || list2.get(x).getRight() == null)
					{
						found = true;
						Person n = new Person(first, last, age);
						n.setHaircolor(hair);
						n.setHeight(height);
						n.setEyecolor(eye);
						n.setDad(list.get(x));
						n.setDadNode(x);
						if(list2.get(x).getValue().getPartner() == null)
						{
							System.out.println("Enter the mother's first name > ");
							parent2first = in.next();
							parent2first=parent2first.substring(0,1).toUpperCase()+parent2first.substring(1).toLowerCase();
							System.out.println("Enter the mother's last name > ");
							parent2last = in.next();
							parent2last=parent2last.substring(0,1).toUpperCase()+parent2last.substring(1).toLowerCase();
							Person pp = new Person(parent2first, parent2last, -1);
							n.setMom(pp);
							list2.get(x).getValue().setPartner(pp);
						}
						list.add(n);
						TreeNode t = new TreeNode(n, null, null, list2.get(x));
						list2.add(t);
						if (list2.get(x).getLeft() == null)
							list2.get(x).setLeft(t);
						else
							list2.get(x).setRight(t);
					}
				}
			}
			if(!found)
				System.out.println("Not valid parent");
		}
		else
		{
			System.out.println("Not valid parent");
		}
	}

	public static void print(TreeNode root)
	{
		List<List<String>> lines = new ArrayList<List<String>>();

		List<TreeNode> level = new ArrayList<TreeNode>();
		List<TreeNode> next = new ArrayList<TreeNode>();

		level.add(root);
		int nn = 1;

		int widest = 0;

		while (nn != 0) {
			List<String> line = new ArrayList<String>();

			nn = 0;

			for (TreeNode n : level) {
				if (n == null) {
					line.add(null);

					next.add(null);
					next.add(null);
				} else {
					String aa;
					if(n.getValue().getPartner()!=null)
						aa = n.getValue().getName() + ": "+n.getValue().getAge()+" years old, Spouse: "+n.getValue().getPartner().getName();
					else
						aa = n.getValue().getName() + ": "+n.getValue().getAge()+" years old";
					line.add(aa);
					if (aa.length() > widest)
						widest = aa.length();

					next.add(n.getLeft());
					next.add(n.getRight());

					if (n.getLeft() != null)
						nn++;
					if (n.getRight() != null)
						nn++;
				}
			}

			if (widest % 2 == 1) widest++;

			lines.add(line);

			List<TreeNode> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
		for (int i = 0; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			int hpw = (int) Math.floor(perpiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {

					// split node
					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = (line.get(j) != null) ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null) c = '└';
						}
					}
					System.out.print(c);

					// lines and spaces
					if (line.get(j) == null) {
						for (int k = 0; k < perpiece - 1; k++) {
							System.out.print(" ");
						}
					} else {

						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			// print line of numbers
			for (int j = 0; j < line.size(); j++) {

				String f = line.get(j);
				if (f == null) f = "";
				int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
				int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

				// a number
				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perpiece /= 2;
		}
	}

}