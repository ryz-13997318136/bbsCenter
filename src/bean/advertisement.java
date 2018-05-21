package bean;

public class advertisement {
public String address,imageUrl,id;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public advertisement() {
	super();
	// TODO Auto-generated constructor stub
}

public advertisement(String id,String address, String imageUrl) {
	super();
	this.address = address;
	this.imageUrl = imageUrl;
	this.id=id;
}

}
