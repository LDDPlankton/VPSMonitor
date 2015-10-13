package com.vpsmonitor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="settings")
public class SettingModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="setting_id")
    private int settingID;
	
    @Column(name="key")
    private String key;
	
    @Column(name="value")
    private String value;
    
    public int getID() { return this.settingID; }
	public String getKey() { return this.getKey(); }
	public String getValue() { return this.value; }
	
	public void setID(int id) { this.settingID = id; }
	public void setKey(String key) { this.key = key; }
	public void setValue(String val) { this.value = val; }
}
