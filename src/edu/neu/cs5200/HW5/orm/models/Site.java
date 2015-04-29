package edu.neu.cs5200.HW5.orm.models;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;



@Entity
@XmlRootElement
//����Ҫ��@XmlAttribute�����õ�ʱ��ǵò�Ҫ�ŵ�public��getter�ϣ�������
//@XmlAccessorType(value = XmlAccessType.FIELD)��ͻ������Ϊ���annotation�Ѿ�
//��֤������public�Ķ�������map���ظ���
//����Ҫ�ŵ�private��attribute����
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Site {
	@XmlAttribute
	@Id
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private double latitude;
	@XmlAttribute
	private double longitude;
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="tower")
	private List<Tower> towers;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public List<Tower> getTowers() {
		return towers;
	}
	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Site(int id, String name, double latitude, double longitude,
			List<Tower> towers) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.towers = towers;
	}
	
	
}
