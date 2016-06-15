package com.epam.pp.hasan.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Yosin_Hasan
 */
public class Product extends Entity {
	private String name;
	private String description;
	private BigDecimal price;
	private String image;
	private Long catId;
	private Long brandId;
	private Status available;
	private ArrayList<Size> size;
	private ArrayList<Color> color;
	private ArrayList<Images> images;

	public Product() {
	}

	public ArrayList<Images> getImages() {
		return images;
	}

	public void setImages(ArrayList<Images> images) {
		this.images = images;
	}

	public ArrayList<Color> getColor() {
		return color;
	}

	public void setColor(ArrayList<Color> color) {
		this.color = color;
	}

	public ArrayList<Size> getSize() {
		return size;
	}

	public void setSize(ArrayList<Size> size) {
		this.size = size;
	}

	public Status getAvailable() {
		return available;
	}

	public void setAvailable(Status available) {
		this.available = available;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final BigDecimal getPrice() {
		return price;
	}

	public final void setPrice(BigDecimal price) {
		this.price = price;
	}

	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = image;
	}

	public final Long getCatId() {
		return catId;
	}

	public final void setCatId(Long catId) {
		this.catId = catId;
	}

	public final Long getBrandId() {
		return brandId;
	}

	public final void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
		result = prime * result + ((catId == null) ? 0 : catId.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (available != other.available)
			return false;
		if (brandId == null) {
			if (other.brandId != null)
				return false;
		} else if (!brandId.equals(other.brandId))
			return false;
		if (catId == null) {
			if (other.catId != null)
				return false;
		} else if (!catId.equals(other.catId))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", price=" + price + ", image=" + image
				+ ", catId=" + catId + ", brandId=" + brandId + ", available=" + available + ", size=" + size
				+ ", color=" + color + ", images=" + images + "]";
	}

	

}
