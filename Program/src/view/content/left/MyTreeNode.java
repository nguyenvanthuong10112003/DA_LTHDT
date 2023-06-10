package view.content.left;

public class MyTreeNode
{
	private String id;
	private String value;
	private String icon;
	public MyTreeNode()
	{
		
	}
	public MyTreeNode(String id, String value, String icon)
	{
		super();
		this.id = id;
		this.value = value;
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}