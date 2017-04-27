package com.tjcu.examination.admin.entity;
import java.io.Serializable;
import java.util.List;

import com.tjcu.examination.core.entity.Menu;
/**
 * @Author hw【hw_yueying@163.com】
 * @Date 2016/12/29 13:15
 */
public class Admin implements Serializable {

    private String id;
    private String account;
    private String username;
    private String password;
    private List<Menu> menuList;


    public Admin(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
    
}
