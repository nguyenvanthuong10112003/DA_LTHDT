package model;

public class Authority {
    private User user;
    private Boolean view;
    private Boolean Edit;
    private Boolean Delete;
    public Authority()
    {
    	user = null;
    	view = false;
    	Edit = false;
    	Delete = false;
    }
    public Authority(User user)
    {
    	this.user = user;
    	view = false;
    	Edit = false;
    	Delete = false;
    }
    public Authority(User user, Boolean view, Boolean Edit, Boolean Delete)
    {
    	this.user = user;
    	this.view = view;
    	this.Edit = Edit;
    	this.Delete = Delete;
    }
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getView() {
		return view;
	}
	public void setView(Boolean view) {
		this.view = view;
	}
	public Boolean getEdit() {
		return Edit;
	}
	public void setEdit(Boolean edit) {
		Edit = edit;
	}
	public Boolean getDelete() {
		return Delete;
	}
	public void setDelete(Boolean delete) {
		Delete = delete;
	}

}
