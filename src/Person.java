public class Person
{
	private String firstName;
	private String lastName;
	private int age;
	private Person dad;
	private Person mom;
	private int dadNode;
	private int momNode;
	private String eyecolor;
	private String haircolor;
	private int height;
	private boolean alive;
	private Person partner;
	public Person(String first,String last, int a)
	{
		setFirstName(first);
		setLastName(last);
		setAge(a);
		momNode=-1;
		dadNode=-1;
	}
	public void setFirstName(String name){firstName=name;}
	public void setLastName(String name){lastName=name;}
	public void setAge(int age2){age=age2;}
	public void setDad(Person dad2){dad=dad2;}
	public void setMom(Person mom2){mom=mom2;}
	public void setDadNode(int x){dadNode=x;}
	public void setMomNode(int x){momNode=x;}
	public void setEyecolor(String c){eyecolor=c;}
	public void setHaircolor(String c){haircolor=c;}
	public void setHeight(int h){height=h;}
	public void setAlive(boolean f){alive=f;}
	public void setPartner(Person partner){this.partner=partner;}
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}
	public String getName(){return firstName+" "+lastName;}
	public int getAge(){return age;}
	public Person getDad(){return dad;}
	public Person getMom(){return mom;}
	public int getDadNode(){return dadNode;}
	public int getMomNode(){return momNode;}
	public String getEyecolor(){return eyecolor;}
	public String getHaircolor(){return haircolor;}
	public int getHeight(){return height;}
	public boolean getAlive(){return alive;}
	public Person getPartner(){return partner;}
	public String toString()
	{
		if(dad != null && mom != null)
			return getName() +"\n Age: "+getAge()+"\n Dad: "+ getDad().getName()+"\n Mom: "+getMom().getName();
		else if(mom != null)
			return getName() +"\n Age: "+getAge()+"\n Dad: None"+"\n Mom: "+getMom().getName();
		else if(dad != null)
			return getName() +"\n Age: "+getAge()+"\n Dad: "+ getDad().getName()+"\n Mom: None";
		else
			return getName() +"\n Age: "+getAge()+"\n Dad: None"+"\n Mom: None";
	}
	public String traits()
	{
		String s;
		if(getEyecolor()!=null && getHeight()!=0 && getHaircolor()!= null && alive && partner != null)
		{
			s = " Living"+"\n Spouse: "+partner.getName()+"\n Eye Color: " + getEyecolor() + "\n Height: " + getHeight() / 12 + " feet";
			if (height % 12 != 0 && height % 12 > 1)
				s = s + " and " + height % 12 + " inches";
			else if (height % 12 != 0)
				s = s + " and " + height % 12 + " inch";
			s+="\n Hair Color: "+getHaircolor();
			return s;
		}
		else if(getEyecolor()!=null && getHeight()!=0 && getHaircolor()!= null && !alive && partner != null)
		{
			s = " Deceased"+"\n Spouse: "+partner.getName()+"\n Eye Color: " + getEyecolor() + "\n Height: " + getHeight() / 12 + " feet";
			if (height % 12 != 0 && height % 12 > 1)
				s = s + " and " + height % 12 + " inches";
			else if (height % 12 != 0)
				s = s + " and " + height % 12 + " inch";
			s+="\n Hair Color: "+getHaircolor();
			return s;
		}
		else if(getEyecolor()!=null && getHeight()!=0 && getHaircolor()!= null && !alive && partner == null)
		{
			s = " Deceased"+"\n Eye Color: " + getEyecolor() + "\n Height: " + getHeight() / 12 + " feet";
			if (height % 12 != 0 && height % 12 > 1)
				s = s + " and " + height % 12 + " inches";
			else if (height % 12 != 0)
				s = s + " and " + height % 12 + " inch";
			s+="\n Hair Color: "+getHaircolor();
			return s;
		}
		else if(getEyecolor()!=null && getHeight()!=0 && getHaircolor()!= null && alive && partner == null)
		{
			s = " Living"+"\n Eye Color: " + getEyecolor() + "\n Height: " + getHeight() / 12 + " feet";
			if (height % 12 != 0 && height % 12 > 1)
				s = s + " and " + height % 12 + " inches";
			else if (height % 12 != 0)
				s = s + " and " + height % 12 + " inch";
			s+="\n Hair Color: "+getHaircolor();
			return s;
		}
		else
			return null;
	}
}